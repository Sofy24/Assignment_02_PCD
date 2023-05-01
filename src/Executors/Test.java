package Executors;

import Utilities.Report;

public class Test {

    public static void main(String[] args) {
        String directory = args[0];
        int longestFiles = Integer.parseInt(args[1]);
        int numberOfRanges = Integer.parseInt(args[2]);
        int maxLines = Integer.parseInt(args[3]);
        Report report = new ExecutorsSourceAnalyser().getReport(directory, longestFiles, numberOfRanges, maxLines);
        //Report report = s.getReport("C:\\Users\\seraf\\OneDrive\\Desktop\\SSS\\ASSIGNMENT1\\f1 (2)\\f1", 5, 5, 200);
        report.getResults();
    }
}
