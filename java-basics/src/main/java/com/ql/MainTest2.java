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
public class MainTest2 {
    public static void main(String[] args) {
        Pair<List<Integer>,Integer> result = txt2List(new File("/Users/guomeiling/work/learn/java-advanced/java-basics/src/main/java/com/ql/315532803.txt"));
        //Pair<List<Integer>,Integer> result = txt2List(new File("/Users/guomeiling/work/learn/java-advanced/java-basics/src/main/java/com/ql/283996.txt"));
        System.out.println("result:" + result);
    }

    public static Pair<List<Integer>,Integer> txt2List(File file){
        List<Integer> result = new ArrayList<>(3600);
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
                if (count >= 3600) {
                    int res = findKthLargest(result.toArray(new Integer[0]),361);
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

    public static void HeapAdjust(Integer[] nums,int s,int d){
        int k = s;

        for(int i=s*2+1;i<=d;i=i*2+1){
            if((i+1<=d)&&nums[i]<nums[i+1]){
                i++;
            }

            if(nums[i]>nums[k]){
                int a = nums[i];
                nums[i] = nums[k];
                nums[k] = a;
                k=i;
            }
            else{
                return;
            }
        }
    }

    public static int findKthLargest(Integer[] nums, int k) {
        int len = nums.length;

        for(int i=len/2-1;i>=0;i--){
            HeapAdjust(nums,i,len-1);
        }
        for(int i=0;i<k-1;i++){
            nums[0]=nums[len-1-i];
            HeapAdjust(nums,0,len-1-i);
        }
        return nums[0];
    }
}
