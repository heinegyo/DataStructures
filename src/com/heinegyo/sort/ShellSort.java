package com.heinegyo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * ShellSort 本質也是一種插入排序法
 * 他是簡單插入排序法經過改進之後的一個更高效率的版本，也稱縮小增量排序
 */
public class ShellSort {

    public static void main(String[] args) {
        // int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        // shellSort(arr);

        int arr[] = new int[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = (int) (Math.random() * 8000000); //產生[0,8000000)數
        }
        Date start = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = simpleDateFormat.format(start);
        System.out.println("排序前的時間是 : " + startTime);
        shellSort(arr);
        Date end = new Date();

        String endTime = simpleDateFormat.format(end);
        System.out.println("排序後的時間是 : " + endTime);
    }

    //對交換式的shellSort進行改進

    public static void shellSort(int[] arr) {
        //增量gap，並逐步的縮小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2){
            //從第gap個元素，逐步對其所在的組進行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移動
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    //當退出while，就給temp找到插入的位置
                    arr[j] = temp;
                }
            }
            // System.out.println(String.format("shellSort 第 %d 輪 : ", count++));
            // System.out.println(Arrays.toString(arr));
        }
    }

    //使用逐步推導的方式來編寫ShellSort
    //希爾排序時，對有序序列在插入時採用交換法
    // public static void shellSort(int[] arr) {
    //
    //     int temp = 0;
    //     int count = 0;
    //     //根據前面的逐步分析，使用循環處理
    //     for (int gap = arr.length / 2; gap > 0; gap /= 2){
    //         for (int i = gap; i < arr.length; i++) {
    //             //遍歷各組中所有的元素(共gap組，每組有個元素)，步長gap
    //             for (int j = i - gap; j >= 0; j -= gap) {
    //                 //如果當前的元素大於加上步長後的那個元素，說明需要交換
    //                 if (arr[j] > arr[j + gap]) {
    //                     temp = arr[j];
    //                     arr[j] = arr[j + gap];
    //                     arr[j + gap] = temp;
    //                 }
    //             }
    //         }
    //         // System.out.println(String.format("shellSort 第 %d 輪 : ", count++));
    //         // System.out.println(Arrays.toString(arr));
    //     }
    //
    //
    //     // int temp = 0;
    //     // //希爾排序的第 1 輪排序
    //     // //因為第 1 輪排序，是將10個數據分成了5組
    //     // for (int i = 5; i < arr.length; i++) {
    //     //     //遍歷各組中所有的元素(共5組，每組有2個元素)，步長5
    //     //     for (int j = i - 5; j >= 0; j -= 5) {
    //     //         //如果當前的元素大於加上步長後的那個元素，說明需要交換
    //     //         if (arr[j] > arr[j + 5]) {
    //     //             temp = arr[j];
    //     //             arr[j] = arr[j + 5];
    //     //             arr[j + 5] = temp;
    //     //         }
    //     //     }
    //     // }
    //     //
    //     // System.out.println("Shell Sort 第 1 輪 後 排序結果");
    //     // System.out.println(Arrays.toString(arr));
    //     //
    //     // //希爾排序的第 2 輪排序
    //     // //因為第 2 輪排序，是將10個數據分成了5/2 = 2組
    //     // for (int i = 2; i < arr.length; i++) {
    //     //     //遍歷各組中所有的元素(共5組，每組有2的算)，步長5
    //     //     for (int j = i - 2; j >= 0; j -= 2) {
    //     //         //如果當前的元素大於加上步長後的那個元素，說明需要交換
    //     //         if (arr[j] > arr[j + 2]) {
    //     //             temp = arr[j];
    //     //             arr[j] = arr[j + 2];
    //     //             arr[j + 2] = temp;
    //     //         }
    //     //     }
    //     // }
    //     //
    //     // System.out.println("Shell Sort 第 2 輪 後 排序結果");
    //     // System.out.println(Arrays.toString(arr));
    //     //
    //     // //希爾排序的第 3 輪排序
    //     // //因為第 3 輪排序，是將10個數據分成了2/2 = 1組
    //     // for (int i = 1; i < arr.length; i++) {
    //     //     //遍歷各組中所有的元素(共5組，每組有2的算)，步長5
    //     //     for (int j = i - 1; j >= 0; j -= 1) {
    //     //         //如果當前的元素大於加上步長後的那個元素，說明需要交換
    //     //         if (arr[j] > arr[j + 1]) {
    //     //             temp = arr[j];
    //     //             arr[j] = arr[j + 1];
    //     //             arr[j + 1] = temp;
    //     //         }
    //     //     }
    //     // }
    //     //
    //     // System.out.println("Shell Sort 第 3 輪 後 排序結果");
    //     // System.out.println(Arrays.toString(arr));
    // }
}
