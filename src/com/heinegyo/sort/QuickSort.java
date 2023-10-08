package com.heinegyo.sort;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序(Quicksort)是對氣泡排序的一種改進。
 * 基本概念是:透過一趟排序將要排序的資料分割成獨立的兩部分，
 * 其中一部分的所有資料都比另外一部分的所有資料都要小，
 * 然後再按此方法對這兩部分資料分別進行快速排序，
 * 整個排序過程可以遞迴進行，以此達到整個資料變成有序序列
 */
public class QuickSort {
    public static void main(String[] args) {
        // int[] arr = {-9, 78, 0, 23, -567, 70 , -1 ,900 ,4561};
        // int arr[] = new int[8000000];
        // for (int i = 0; i < 8000000; i++) {
        //     arr[i] = (int) (Math.random() * 8000000); //產生[0,8000000)數
        // }
        int[] arr = {1,2,0,1,2};

        Date start = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = simpleDateFormat.format(start);
        System.out.println("排序前的時間是 : " + startTime);

        quickSort(arr, 0, arr.length - 1);

        Date end = new Date();
        String endTime = simpleDateFormat.format(end);
        System.out.println("排序後的時間是 : " + endTime);

        // System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;

        int pivot = arr[(left + right) / 2];

        //臨時變數，作為交換時使用
        int temp = 0;
        //while循環的目的是讓 比pivot值小的 放到左邊 比pivot值大的 放到右邊
        while (l < r) {
            //在pivot的左邊一直找，找到大於pivot值得才退出
            while (arr[l] < pivot) {
                l += 1;
            }

            //在pivot的右邊一直找，找到小於pivot值得才退出
            while (arr[r] > pivot) {
                r -= 1;
            }

            //如果l>=r 說明pivot的左右兩側的值，已經按照pivot 分別放至左邊與右邊
            if (l >= r) {
                break;
            }

            //交換
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交換完後，發現這個值arr[l] == pivot值 相等 r-- ，前移
            if (arr[l] == pivot) {
                r--;
            }

            //如果交換完後，發現這個值arr[r] == pivot值 相等 l++ ，後移
            if (arr[r] == pivot) {
                l++;
            }
        }

        //如果l == r ，必須l++ , r-- ，否則會出現stackoverflow
        if (l == r) {
            l++;
            r--;
        }

        //向左遞迴
        if (left < r) {
            quickSort(arr, left, r);
        }

        //向右遞迴
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
