package Executors;


import Utilities.Report;

public interface SourceAnalyser {

    Report getReport(String directory, int longestFiles, int ranges, int maxLines);

    void analyzeSources(String d);
}
