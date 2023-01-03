package com.bixingyu.demo1;

import sun.plugin.com.Utils;

/**
 * @author BiXingyu
 * @create 2022-12-29 11:14
 */
public class JvmTest1 {
    public static void main(String[] args) {
        int i = 2;
        int j = 4;
        int m = i + j;

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
