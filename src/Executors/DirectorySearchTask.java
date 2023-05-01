package Executors;

import Utilities.FilePath;
import Utilities.Pair;

import java.io.File;
import java.util.*;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DirectorySearchTask extends RecursiveTask<List<Pair<FilePath, Long>>> {

    private final String directory;
    public DirectorySearchTask(String d) {
        super();
        directory = d;
    }

    private Set<String> getSubDirectory() {
        try {
            return Stream.of(Objects.requireNonNull(new File(directory).listFiles()))
                    .filter(File::isDirectory)
                    .map(File::getAbsolutePath)
                    .collect(Collectors.toSet());
        } catch (NullPointerException e) {
            return null;
        }
    }

    private static Set<String> getJavaSourceFiles(String dir) {
        try {
            return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                    .filter(file -> !file.isDirectory())
                    .map(File::getName)
                    .filter(name -> name.endsWith(".java"))
                    .collect(Collectors.toSet());
        } catch (NullPointerException e) {
            return null;
        }
    }


    @Override
    protected List<Pair<FilePath, Long>> compute() {
        List<Pair<FilePath, Long>> files = new ArrayList<>();
        List<RecursiveTask<List<Pair<FilePath, Long>>>> dirForks = new LinkedList<>();
        Set<String> subDirectories = getSubDirectory();
        if (subDirectories != null) {
            for (String subDirectory : subDirectories) {
                DirectorySearchTask task = new DirectorySearchTask(subDirectory);
                dirForks.add(task);
                task.fork();
            }
        }

        List<RecursiveTask<Pair<FilePath, Long>>> fileForks = new LinkedList<>();
        Set<String> dirFiles = getJavaSourceFiles(directory);
        if ( dirFiles != null) {
            for (String file: dirFiles) {
                FileLengthTask task = new FileLengthTask(new FilePath(directory, file));
                fileForks.add(task);
                task.fork();
            }
        }

        for (RecursiveTask<List<Pair<FilePath, Long>>> task : dirForks) {
            files.addAll(task.join());
        }
        for (RecursiveTask<Pair<FilePath, Long>> task : fileForks) {
            files.add(task.join());
        }
        return files;
    }
}
