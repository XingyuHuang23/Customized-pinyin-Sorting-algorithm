package edu.neu.coe.info6205.union_find;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestUf {

    public static void main(String args[]) {

            int[] sites = new int[]{5000,10000,30000,60000,120000,240000,500000};

            for(Integer site:sites){
                System.out.println(site + " sites: "+count(site));
            }

    }

    private static int count(int site) {
        HashMap<String,Integer> res = new HashMap<>();
        UF_HWQUPC uf = new UF_HWQUPC(site);
        int count = 0;
        while(uf.components()>1){
            int i =  new Random().nextInt(site);
            int j =  new Random().nextInt(site);
            count++;
            if(uf.connected(i,j)) continue;
            res.put(i+"-"+j,j);
            uf.connect(i,j);
        }
       // return res.size();
        return  count;
    }
}
