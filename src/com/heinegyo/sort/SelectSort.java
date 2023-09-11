package com.heinegyo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 選擇排序
 * 說明：
 * 1. 選擇排序一共有Array大小-1輪排序
 * 2. 每一輪排序，又是一個循環
 *    2-1. 先假設目前這個數是最小數
 *    2-2. 然後和後面的每個數進行比較，如果有比目前數更小的數，就重新確定最小數，並得到index
 *    2-3. 當遍歷到陣列的最後時，就可以得到本輪最小數和最小數index
 *    2-4. 交換
 * 第一次從arr[0]~arr[n-1]中選取最小值與arr[0]交換
 * 第二次從arr[1]~arr[n-1]中選取最小值與arr[1]交換
 * 第三次從arr[2]~arr[n-1]中選取最小值與arr[2]交換
 * 第i次從arr[i-1]~arr[n-1]中選取最小值與arr[i-1]交換
 * 第n-1次從arr[n-2]~arr[n-1]中選取最小值與arr[n-2]交換
 * 總共通過n-1次，得到一個按碼排序從小到大排列的有序序列
 */
public class SelectSort {

    public static void main(String[] args) {

        //int arr[] = {101,34,119,1};
        int arr[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //產生[0,8000000)數
        }

        // System.out.println(Arrays.toString(arr));
        Date start = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = simpleDateFormat.format(start);
        System.out.println("排序前的時間是 : " + startTime);
        selectSort(arr);
        // System.out.println(Arrays.toString(arr));
        BubbleSort.bubbleSort(arr);
        Date end = new Date();
        String endTime = simpleDateFormat.format(end);
        System.out.println("排序後的時間是 : " + endTime);
    }

    //選擇排序
    public static void selectSort(int[] arr) {

        //在推導的過程，我們發現了規律，因此，可以使用for來解決
        //選擇排序時間複雜度是O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { //說明假定的最小值，並不是最小
                    min = arr[j]; //重置min
                    minIndex = j; //重置minIndex
                }
            }
            //將最小值，放在arr[0]，即交換
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }


        //使用逐步推導的方式，講解選擇排序
        //第一輪
        //原始的Array : 101,34,119,1
        //第一輪排序:    1,34,119,101
        //算法 先簡單 --> 再複雜 可以把一個複雜的算法，拆成簡單的問題，逐步解決

        // //第一輪
        // int minIndex = 0;
        // int min = arr[0];
        // for (int j = 0 + 1; j < arr.length; j++) {
        //     if (min > arr[j]) { //說明假定的最小值，並不是最小
        //         min = arr[j]; //重置min
        //         minIndex = j; //重置minIndex
        //     }
        // }
        //
        // //將最小值，放在arr[0]，進行交換
        // if (minIndex != 0){
        //     arr[minIndex] = arr[0];
        //     arr[0] = min;
        // }
        //
        //
        // System.out.println("第1輪推導");
        // System.out.println(Arrays.toString(arr));
        //
        // //第二輪
        // minIndex = 1;
        // min = arr[1];
        // for (int j = 1 + 1; j < arr.length; j++) {
        //     if (min > arr[j]) { //說明假定的最小值，並不是最小
        //         min = arr[j]; //重置min
        //         minIndex = j; //重置minIndex
        //     }
        // }
        //
        // //將最小值，放在arr[0]，進行交換
        // if (minIndex != 1) {
        //     arr[minIndex] = arr[1];
        //     arr[1] = min;
        // }
        // System.out.println("第2輪推導");
        // System.out.println(Arrays.toString(arr));
        //
        //
        // //第三輪
        // minIndex = 2;
        // min = arr[2];
        // for (int j = 2 + 1; j < arr.length; j++) {
        //     if (min > arr[j]) { //說明假定的最小值，並不是最小
        //         min = arr[j]; //重置min
        //         minIndex = j; //重置minIndex
        //     }
        // }
        //
        // //將最小值，放在arr[0]，進行交換
        // if (minIndex != 2) {
        //     arr[minIndex] = arr[2];
        //     arr[2] = min;
        // }
        //
        // System.out.println("第3輪推導");
        // System.out.println(Arrays.toString(arr));
    }


}
