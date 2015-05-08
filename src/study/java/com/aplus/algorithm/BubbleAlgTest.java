package com.aplus.algorithm;

/**
 * 冒泡排序
 *
 * Created by lifang on 2015/5/8.
 */
public class BubbleAlgTest{


    public static void main(String[] args){
        Integer[] params = {20, 11, 3, 10, 22, 13, 34, 10, 14, 45, 42, 8, 7, 6, 15, 21,33,32,18,-2};
        sort(params);
    }

    public static void sort(Integer... params){
        for(int i = 0; i < params.length; i++){
            for(int j = i + 1; j < params.length; j++) {
                if (params[i] > params[j]) {
                    int temp = params[i];
                    params[i] = params[j];
                    params[j] = temp;
                }
            }
        }
        for(int i = 0; i < params.length; i++) {
            System.out.print(params[i] + ",");
        }
    }

}
