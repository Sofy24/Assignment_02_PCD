package Executors;


import Utilities.Report;

import java.util.concurrent.ForkJoinPool;

public class ExecutorsSourceAnalyser implements SourceAnalyser{
    @Override
    public Report getReport(String directory, int longestFiles, int ranges, int maxLines) {
        var files = new ForkJoinPool().invoke(new DirectorySearchTask(directory));
        files.forEach(f -> System.out.println(f.getX().getCompleteFilePath() +"  "+ f.getY()));
        return null;
    }

    @Override
    public void analyzeSources(String d) {

    }
}
