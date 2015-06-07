package com.aplus.algorithm;

/**
 * 快速排序
 *
 * Created by lifang on 2015/5/11.
 */
public class FastSortTest{

    public static void main(String[] args){
        Integer[] params = {20, 11, 3, 10, 22, 13, 34, 10, 14, 45, 42, 8, 7, 6, 15, 21,33,32,18,-2};
        new FastSortTest().sort(params, 0, params.length-1);
        new FastSortTest().print(params);
    }

    public void print(Integer[] params) {
        for(Integer param : params){
            System.out.print(param + ",");
        }
    }

    public void sort(Integer[] params, int sp, int ep) {
        if(sp < ep){
            int r = partition(params, sp, ep);
            sort(params, sp, r - 1);
            sort(params, r + 1, ep);
        }
    }

    private int partition(Integer[] params, int sp, int ep){
        int pivot = params[sp];
        while (sp < ep)
        {
            while (sp < ep && params[ep] > pivot) ep--;
            swap(params, ep, sp);

            while (sp < ep && params[sp] <= pivot) sp++;
            swap(params, ep, sp);
        }
        params[sp] = pivot;
        return sp;
    }

    private void swap(Integer[] params, int sp, int ep){
        int temp = params[sp];
        params[sp] = params[ep];
        params[ep] = temp;
    }

}
