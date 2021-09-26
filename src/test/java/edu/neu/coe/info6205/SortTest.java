package edu.neu.coe.info6205;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class SortTest {
    int[] N;
    SortTestH sortTest = new SortTestH();

    @Before
    public void setup() {
        N = new int[]{10000,15000,20000,25000,30000};
    }

    public void sort(Integer[] arr,int n){
        double start = System.nanoTime()/1000000;
        InsertionSort.sort(arr);
        double end = System.nanoTime()/1000000;
        double total = end - start;
        System.out.println(n+" was ordered in "+total+" ms");
    }



    @Test
    public void inverse() {
        for(Integer n:N){
            Integer[] arr =  sortTest.getArrayByType(n,"INVERSE");
             sort(arr,n);
        }
    }

    @Test
    public void random() {
        for(Integer n:N){
            Integer[] arr =  sortTest.getArrayByType(n,"RANDOM");
            sort(arr,n);;
        }
    }
    @Test
    public void order() {
        for(Integer n:N){
            Integer[] arr =  sortTest.getArrayByType(n,"ORDER");
            sort(arr,n);
        }
    }

    @Test
    public void partRandom() {
        for(Integer n:N){
            Integer[] arr =  sortTest.getArrayByType(n,"PART_ORDER");
            sort(arr,n);
        }
    }
}
