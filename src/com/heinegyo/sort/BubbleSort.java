package com.heinegyo.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Bubble Sort
 * 1. 一共進行Array長度-1的循環
 * 2. 每一趟排序的次數在逐漸減少
 * 3. 如果我們發現在某趟排序中，沒有發生一次交換，可以提前結束排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        //int arr[] = {-2, -1, 3, 9, 10};
        //int arr[] = {3, 9, -1, 10, -2};

        //測試一下氣泡排序的速度O(n^2)，以80000個元素測試
        int arr[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //產生[0,8000000)數
        }
        //System.out.println(Arrays.toString(arr));

        Date start = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = simpleDateFormat.format(start);
        System.out.println("排序前的時間是 : " + startTime);

        BubbleSort.bubbleSort(arr);
        Date end = new Date();

        String endTime = simpleDateFormat.format(end);
        System.out.println("排序後的時間是 : " + endTime);



        /*
        //為了容易理解，寫出氣泡排序的演變過程
        //第一趟排序，就是將最大的數排在最後
        //第二趟排序，就是把第二大的數排在倒數第二位
        for (int j = 0; j < arr.length - 1 - 1; j++) {
            //如果前面的數比後面的數大，則交換
            if (arr[j] > arr[j + 1]){
                change(arr,j,j+1);
            }
        }

        System.out.println("第二趟排序後的Array");
        System.out.println(Arrays.toString(arr));

        //第三趟排序，就是把第三大的數排在倒數第三位
        for (int j = 0; j < arr.length - 1 - 1 -1; j++) {
            //如果前面的數比後面的數大，則交換
            if (arr[j] > arr[j + 1]){
                change(arr,j,j+1);
            }
        }

        System.out.println("第三趟排序後的Array");
        System.out.println(Arrays.toString(arr));

        //第四趟排序，就是把第四大的數排在倒數第四位
        for (int j = 0; j < arr.length - 1 - 1 - 1 - 1; j++) {
            //如果前面的數比後面的數大，則交換
            if (arr[j] > arr[j + 1]){
                change(arr,j,j+1);
            }
        }

        System.out.println("第四趟排序後的Array");
        System.out.println(Arrays.toString(arr));

         */

    }

    public static void change(int[] arr, int element1, int element2){
        int temp = 0;
        temp = arr[element1];
        arr[element1] = arr[element2];
        arr[element2] = temp;
    }

    //將前面的Bubble Sort 進行封裝
    public static void bubbleSort(int[] arr) {
        //BubbleSort 時間複雜度 O(n^2)
        boolean flag = false; //表示是否進行交換
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                flag = false; //重置標示
                //如果前面的數比後面的數大，則交換
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    change(arr, j, j + 1);
                }
            }
            // System.out.println("第" + (i + 1) + "趟排序後的Array");
            // System.out.println(Arrays.toString(arr));

            if (!flag) { //說明一次交換都沒發生
                break;
            }
        }
    }
}
