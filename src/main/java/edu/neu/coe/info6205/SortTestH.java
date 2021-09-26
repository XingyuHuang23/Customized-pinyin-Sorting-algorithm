package edu.neu.coe.info6205;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;

public class SortTestH {

    public Integer[] getArrayByType(int n, String type) {
        Integer[] arr = null;
        switch(type){
            case "INVERSE" :
                arr = SortTestHelper.generateInversedArray(n);
                break; //可选
            case "ORDER" :
                arr = SortTestHelper.generateOrderedArray(n);
                break;
            case "PART_ORDER" :
                arr = SortTestHelper.generateNearlyOrderedArray(n,  n/2);
                break;
            case "RANDOM" :
                arr = SortTestHelper.generateRandomArray(n,0,n);
                break;
        }
        return arr;
    }
}
