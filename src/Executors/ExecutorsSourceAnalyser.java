package Executors;


import Utilities.ComputedFile;
import Utilities.LongRange;
import Utilities.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ExecutorsSourceAnalyser implements SourceAnalyser{
    @Override
    public Report getReport(String directory, int longestFiles, int numberOfRanges, int maxLines) {
        List<LongRange> ranges = new ArrayList<>();
        final int rangeSize = maxLines / (numberOfRanges - 1);
        for (long i = 0; i < numberOfRanges; i++) {
            ranges.add(new LongRange(
                    rangeSize * i, i != (numberOfRanges - 1) ? rangeSize * (i + 1) - 1 : Long.MAX_VALUE));
        }
        return new Report(new ForkJoinPool().invoke(new DirectorySearchTask(directory, ranges)), ranges, longestFiles);

    }

    @Override
    public void analyzeSources(String d) {
    }
}
