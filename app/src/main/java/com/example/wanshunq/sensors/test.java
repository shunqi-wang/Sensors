package com.example.wanshunq.sensors;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int[] intArray=new int[]{1,2,3,4};
        int[] dest=new int[2];
        System.arraycopy(intArray,0,dest,0,2);
        System.out.println(Arrays.toString(dest));
    }
}
