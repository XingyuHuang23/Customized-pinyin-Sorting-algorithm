package edu.neu.coe.info6205.sort.par;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
public class Main {
    static  int arraySize = 1600000;
    static int threadCount = 10;
   static ForkJoinPool myPool = new ForkJoinPool(threadCount);

    public static void main(String[] args) {
        processArgs(args);

        System.out.println("Degree of parallelism: " + myPool.getParallelism());
        System.out.println("ArraySize: " + arraySize);
        Random random = new Random();
        int[] array = new int[arraySize];
        ArrayList<Long> timeList = new ArrayList<>();

        //pre-warm
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < array.length; j++) {
                array[i] = random.nextInt(100);
            }

            ParSort.sort(array, 0, array.length);
        }
        int k =2;
        for (int j = 0; j < 8; j++) {
            ParSort.cutoff = arraySize  *  k / 10;
            //ParSort.cutoff = arraySize / 2000 * j; ParSort.cutoff单调递减，排序应该会越来越快，因为越多的部分使用了线程排序，更少部分系统排序

            long startTime = System.currentTimeMillis();
            for (int t = 0; t < 10; t++) {
                for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                ParSort.sort(array, 0, array.length);
            }
            long endTime = System.currentTimeMillis();

            long time = (endTime - startTime);
            timeList.add(time);


            System.out.println("cutoff：" + (ParSort.cutoff) + "\t\t10times Time:" + time + "ms"  + "\t\t percent: "+ Double.valueOf(ParSort.cutoff/arraySize));
            k+=2;
        }
        try {
            FileOutputStream fis = new FileOutputStream("./src/result.csv");
            OutputStreamWriter isr = new OutputStreamWriter(fis);
            BufferedWriter bw = new BufferedWriter(isr);
            int j = 0;
            for (long i : timeList) {
                String content = (double) 10000 * (j + 1) / 2000000 + "," + (double) i / 10 + "\n";
                j++;
                bw.write(content);
                bw.flush();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processArgs(String[] args) {
        String[] xs = args;
        while (xs.length > 0)
            if (xs[0].startsWith("-")) xs = processArg(xs);
    }

    private static String[] processArg(String[] xs) {
        String[] result = new String[0];
        System.arraycopy(xs, 2, result, 0, xs.length - 2);
        processCommand(xs[0], xs[1]);
        return result;
    }

    private static void processCommand(String x, String y) {
        if (x.equalsIgnoreCase("N")) setConfig(x, Integer.parseInt(y));
        else
        // TODO sort this out
            if (x.equalsIgnoreCase("P")) //noinspection ResultOfMethodCallIgnored
                ForkJoinPool.getCommonPoolParallelism();
    }

    private static void setConfig(String x, int i) {
        configuration.put(x, i);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String, Integer> configuration = new HashMap<>();


}
