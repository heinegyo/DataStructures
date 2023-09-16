package com.heinegyo.sort;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 插入排序 -> 整理撲克牌
 * 說明
 * 把n個待排序的元素看成為一個有序表和一個無序表，
 * 開始時有序表中之包含一個元素，無序表中包含有n-1個元素
 * 排序過程中每次從無序表中取出第一個元素，把他的排序碼依次與有序表元素的排序碼進行比較
 * 將他插入到有序表中的適當位置，使之成為新的有序表
 */
public class InsertSort {

    public static void main(String[] args) {
        // int arr[] = {101, 34, 119, 1};
        //int arr[] = {101, 34, 119, 1 , -1 ,89};
        int arr[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //產生[0,8000000)數
        }
        Date start = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = simpleDateFormat.format(start);
        System.out.println("排序前的時間是 : " + startTime);
        insertSort(arr);
        Date end = new Date();

        String endTime = simpleDateFormat.format(end);
        System.out.println("排序後的時間是 : " + endTime);

    }

    //插入排序
    public static void insertSort(int[] arr) {
        //需要插入的數值
        int insertVal = 0;
        //即將插入的索引
        int insertIndex = 0;
        //使用for循環簡化
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            //即arr[1]的前面這個數的index
            insertIndex = i - 1;

            //每insertVal找到插入的位置
            //說明
            //1. insertIndex >= 0 ，保證在給insertVal 找插入位置，不越界
            //2. insertVal < arr[insertIndex] 待插入的數，還沒有找到插入的位置
            //3. 就需要將arr[insertIndex]的value 後移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex]; //arr[insertIndex]
                insertIndex--;
            }
            //當退出while循環時，說明插入的位置找到，insertIndex + 1
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
            // System.out.printf("第 %d 輪 \n ", i);
            // System.out.println(Arrays.toString(arr));
        }


        //使用逐步推導的方式來講解，便於理解

        //第 1 輪{101,34,119,1}; => {34,101,119,1}
        //{101,34,119,1} => {101,101,119,1}
        //定義待插入的數
        // int insertVal = arr[1];
        // //即arr[1]的前面這個數的index
        // int insertIndex = 1 - 1;
        //
        // //每insertVal找到插入的位置
        // //說明
        // //1. insertIndex >= 0 ，保證在給insertVal 找插入位置，不越界
        // //2. insertVal < arr[insertIndex] 待插入的數，還沒有找到插入的位置
        // //3. 就需要將arr[insertIndex] 後移
        // while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
        //     arr[insertIndex + 1] = arr[insertIndex]; //arr[insertIndex]
        //     insertIndex--;
        // }
        // //當退出while循環時，說明插入的位置找到，insertIndex + 1
        // arr[insertIndex + 1] = insertVal;
        //
        // System.out.println("第 1 輪插入");
        // System.out.println(Arrays.toString(arr));
        //
        // //第 2 輪{101,34,119,1}; => {34,101,119,1}
        // //{101,34,119,1} => {101,101,119,1}
        // //定義待插入的數
        // insertVal = arr[2];
        // //即arr[1]的前面這個數的index
        // insertIndex = 2 - 1;
        //
        // //每insertVal找到插入的位置
        // //說明
        // //1. insertIndex >= 0 ，保證在給insertVal 找插入位置，不越界
        // //2. insertVal < arr[insertIndex] 待插入的數，還沒有找到插入的位置
        // //3. 就需要將arr[insertIndex] 後移
        // while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
        //     arr[insertIndex + 1] = arr[insertIndex]; //arr[insertIndex]
        //     insertIndex--;
        // }
        // //當退出while循環時，說明插入的位置找到，insertIndex + 1
        // arr[insertIndex + 1] = insertVal;
        //
        // System.out.println("第 2 輪插入");
        // System.out.println(Arrays.toString(arr));
        //
        // //第 3 輪{101,34,119,1}; => {34,101,119,1}
        // //{101,34,119,1} => {101,101,119,1}
        // //定義待插入的數
        // insertVal = arr[3];
        // //即arr[1]的前面這個數的index
        // insertIndex = 3 - 1;
        //
        // //每insertVal找到插入的位置
        // //說明
        // //1. insertIndex >= 0 ，保證在給insertVal 找插入位置，不越界
        // //2. insertVal < arr[insertIndex] 待插入的數，還沒有找到插入的位置
        // //3. 就需要將arr[insertIndex] 後移
        // while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
        //     arr[insertIndex + 1] = arr[insertIndex]; //arr[insertIndex]
        //     insertIndex--;
        // }
        // //當退出while循環時，說明插入的位置找到，insertIndex + 1
        // arr[insertIndex + 1] = insertVal;
        //
        // System.out.println("第 3 輪插入");
        // System.out.println(Arrays.toString(arr));
    }
}
