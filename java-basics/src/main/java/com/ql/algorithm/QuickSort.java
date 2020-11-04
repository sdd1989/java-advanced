package com.ql.algorithm;

import java.util.Arrays;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/11/3 3:47 下午
 **/
public class QuickSort {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{34,56,11,90,20,200,1};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(Integer[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }

    private static int partition(Integer[] arr, int lo, int hi) {
        int i = lo, j = hi + 1;
        int s = arr[lo];
        while (true) {
            while (arr[++i] < s) {
                if (i == hi)
                    break;
            }
            while (arr[--j] > s) {
                if (j == lo)
                    break;
            }
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, lo, j);
        return j;
    }

    private static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}