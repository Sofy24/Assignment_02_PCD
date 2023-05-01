package Executors;

import Utilities.FilePath;
import Utilities.Pair;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.RecursiveTask;

public class FileLengthTask extends RecursiveTask<Pair<FilePath, Long>> {

    private final FilePath filePath;

    public FileLengthTask(FilePath filePath) {
        this.filePath = filePath;
    }

    @Override
    protected Pair<FilePath, Long> compute() {
        long fileLen = 0L;
        try {
            fileLen = Files.lines(Paths.get(filePath.getCompleteFilePath()), StandardCharsets.UTF_8).count();
        } catch (Exception e){
            e.printStackTrace();
        }
        return new Pair<>(filePath, fileLen);
    }
}
