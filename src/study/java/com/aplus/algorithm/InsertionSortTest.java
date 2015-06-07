package com.aplus.algorithm;

/**
 * 直接插入排序
 * 原理: 每一步都将一个待排序字段插入到已经排序的数据的适当位置，直到全部插入完毕。
 * Created by lifang on 2015/5/22.
 */
public class InsertionSortTest {

    public static void main(String[] args){
        int[] arr = {2, 4, 5, 6, 1, 4, 3, 9};
        sort(arr);
        print(arr);
    }

    public static void sort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            if(arr[i -1] > arr[i]){
                int temp = arr[i];
                int j = i;
                while(j > 0 && arr[j - 1] > temp){
                    arr[j] = arr[j - 1];
                    j--;
                }
                arr[j] = temp;
            }
        }
    }

    public static void print(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
    }

}
