package com.ql.algorithm;

import java.util.Arrays;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/8/26 1:25 下午
 **/
public class Main {

//    public static void main(String[] args) {
//        String s1 = "abc";
//        String s2 = "abc";
//        String s3 = new String("abc");
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s3);
//        System.out.println(s1 == s3.intern());
//        System.out.println(s1 == s3);
//    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,5,6,1,3,2};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int l, int r) {
        if(l >= r){
            return;
        }
        int left = l;
        int less = l - 1;
        int more = r + 1;
        int index = arr[r];
        while(l < more) {
            if(arr[l] < index) {
                swap(arr, ++less, l++);
            } else if(arr[l] > index) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        quickSort(arr, left, less);
        quickSort(arr, more, r);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
