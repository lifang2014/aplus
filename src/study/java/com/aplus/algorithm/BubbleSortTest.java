package com.aplus.algorithm;

/**
 * 冒泡排序
 *  1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 *  2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
 *  3.针对所有的元素重复以上的步骤，除了最后一个。
 *  4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。[1]
 * Created by lifang on 2015/5/8.
 */
public class BubbleSortTest{

    public static void main(String[] args){
        Integer[] params = {20, 11, 3, 10, 22, 13, 34, 10, 14, 45, 42, 8, 7, 6, 15, 21,33,32,18,-2};
        new BubbleSortTest().sort(params);
        new BubbleSortTest().print(params);
    }

    public void sort(Integer[] params) {
        for(int i = 0; i < params.length; i++){
            for(int j = 0; j < params.length - 1; j++){
                if(params[j] > params[j + 1]){
                    int temp = params[j];
                    params[j] = params[j + 1];
                    params[j + 1] = temp;
                }
            }
        }
    }

    public void print(Integer[] params) {
        for(Integer p : params){
            System.out.print(p + ",");
        }
    }
}
