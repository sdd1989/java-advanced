package com.ql;

import javafx.util.Pair;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/10/23 3:08 下午
 **/
public class MainTest {
    public static void main(String[] args) {
        //Pair<List<Integer>,Integer> result = txt2List(new File("/Users/guomeiling/work/learn/java-advanced/java-basics/src/main/java/com/ql/315532803.txt"));
        Pair<List<Integer>,Integer> result = txt2List(new File("/Users/guomeiling/work/learn/java-advanced/java-basics/src/main/java/com/ql/283996.txt"));
        System.out.println("result:" + result);
    }

    public static Pair<List<Integer>,Integer> txt2List(File file){
        List<Integer> result = new ArrayList<>(8640);
        BufferedReader br = null;
        int count = 0;
        int lastMax = 0;
        List<Integer> lastResult = new ArrayList<>();
        try{
            br = new BufferedReader(new FileReader(file)); //构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){ //使用readLine方法，一次读一行
                result.add(NumberUtils.toInt(s));
                count++;
                if (count >= 8640) {
                    int res = findKth(result.toArray(new Integer[0]), 8640, 865);
                    if (res > lastMax) {
                        lastMax = res;
                        lastResult.clear();
                        lastResult.addAll(result);
                    }
                    result.remove(0);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new Pair<>(lastResult, lastMax);
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
