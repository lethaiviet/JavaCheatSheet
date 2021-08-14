package scripts;

import data.ChartParam;
import org.apache.commons.lang3.time.StopWatch;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class CompareStreamWithFor {
    public static void main(String[] args) {
        int num = 10000000;
        List<Integer> sizes = List.of(num, num * 2, num * 3, num * 4, num * 5);
        Map<Integer, int[]> mapData = createMapWithListSizes(sizes);

        var series = new XYSeries("For loop");
        for (Map.Entry<Integer, int[]> e : mapData.entrySet()) {
            series.add((double) e.getKey(), getElapsedTimeOfFilterWithForLoopInMs(e.getValue()));
        }

        getElapsedTimeOfFilterWithStreamInMs(new int[]{1});
        var series2 = new XYSeries("Stream");
        for (Map.Entry<Integer, int[]> e : mapData.entrySet()) {
            series2.add((double) e.getKey(), getElapsedTimeOfFilterWithStreamInMs(e.getValue()));
        }

        // remove memory
        mapData = null;
        System.gc();

        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        dataset.addSeries(series2);

        ChartParam chartParam = ChartParam.builder()
                .titleWindow("Line Chart")
                .titleChart("Elapsed Time Of Filter By Loop FOR And STREAM")
                .xAxisLabel("Size data")
                .yAxisLabel("Time (ms)")
                .dataset(dataset)
                .build();

        var ex = new LineChart(chartParam);
        ex.setVisible(true);
    }

    public static Map<Integer, int[]> createMapWithListSizes(List<Integer> sizes) {
        return sizes.stream().collect(Collectors.toMap(x -> x, CompareStreamWithFor::createArrayInt));
    }

    public static int[] createArrayInt(int size) {
        int[] result = new int[size];
        Random rand = new Random();
        int upperBound = 101;
        for (int i = 0; i < size; i++) {
            result[i] = rand.nextInt(upperBound);
        }
        return result;
    }

    public static double getElapsedTimeOfFilterWithForLoopInMs(int[] arrayInt) {
        List<Integer> numberFiltered1 = new ArrayList<>();

        StopWatch stopWatch = StopWatch.createStarted();
        for (int i = 0; i < arrayInt.length; i++) {
            if (arrayInt[i] > 50) {
                numberFiltered1.add(arrayInt[i]);
            }
        }
        stopWatch.split();
        return stopWatch.getSplitTime();
    }

    public static long getElapsedTimeOfFilterWithStreamInMs(int[] arrayInt) {
        StopWatch stopWatch = StopWatch.createStarted();
        List<Integer> numberFiltered2 = Arrays.stream(arrayInt).parallel()
                .boxed()
                .filter(x -> x > 50).collect(Collectors.toList());
        stopWatch.split();
        return stopWatch.getSplitTime();
    }
}
