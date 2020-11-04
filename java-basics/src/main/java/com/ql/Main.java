package com.ql;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/10/23 5:06 下午
 **/
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
//        for (int i=0;i<10;i++) {
//            list.add(i);
//        }
        list.add(1);
        list.add(4);
        list.add(2);
        list.add(7);
        list.add(9);
        list.add(8);
        list.add(3);
        System.out.println(findKth(list.toArray(new Integer[0]), list.size(), 3));
    }

    public static int findKth (Integer[] array, Integer n, int k)
    {
        return findKth(0, n-1, array, k);
    }

    public static int findKth (Integer left, Integer right, Integer[] array, int k)
    {
        int m = partation(left, right, array);

        if ((m-left) > (k-1) && left<right)
        {
            return findKth(left, m-1, array, k);
        }
        else if ((m-left) < (k-1) && left<right)
        {
            return findKth(m+1, right, array, k-m+left-1);
        }
        else
        {
            return array[m];
        }
    }
    public static int partation (int left, int right, Integer[] array)
    {
        int key = array[left];
        while (left < right)
        {
            while (left<right && array[right]<=key)
            {
                right--;
            }
            array[left] = array[right];
            while (left<right && array[left]>=key)
            {
                left++;
            }
            array[right] = array[left];
        }
        array[left] = key;
        return left;
    }
}
