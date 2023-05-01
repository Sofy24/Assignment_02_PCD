package Executors;

import Utilities.Report;

public class Test {

    public static void main(String[] args) {
        SourceAnalyser s = new ExecutorsSourceAnalyser();
        Report report = s.getReport("C:\\Users\\seraf\\OneDrive\\Desktop\\SSS\\ASSIGNMENT1\\f1 (2)\\f1", 5, 5, 200);
        report.getResults();
    }
}
