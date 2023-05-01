package EventLoop;


import Utilities.Report;

public interface SourceAnalyser {

    Report getReport(String d, int longestFiles, int ranges, int maxLines);

    void analyzeSources(String d);
}
