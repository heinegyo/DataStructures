package com.heinegyo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * MergeSort 合併排序是利用合併的思維實現的排序方法，
 * 該算法採用經典的分治(divide-and-conquer)策略，
 * 分治法將問題分(divide)成一些小的問題然後遞迴求解，
 * 而治(conquer)的階段則將分的階段得到的答案"修補"在一起，即分而治之
 */
public class MergeSort {

    public static void main(String[] args) {
        //int arr[] = {8, 4, 5, 7, 1, 3, 6, 2}; //8個元素， merge 次

        int length = 8000000;
        int arr[] = new int[length];
        int temp[] = new int[arr.length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * 80000); //產生[0,8000000)數
        }
        Date start = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = simpleDateFormat.format(start);
        System.out.println("排序前的時間是 : " + startTime);

        mergeSort(arr, 0, arr.length - 1, temp);
        Date end = new Date();

        String endTime = simpleDateFormat.format(end);
        System.out.println("排序後的時間是 : " + endTime);

        // System.out.println(String.format("MergeSort result : %s ", Arrays.toString(arr)));
    }

    //分+合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            //中間索引
            int mid = (left + right) / 2;
            //向左遞迴
            mergeSort(arr, left, mid, temp);
            //向右遞迴
            mergeSort(arr, mid + 1, right, temp);
            //每分解一次就合併一次
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合併的方法
     *
     * @param arr   排序的原始陣列
     * @param left  左邊有序序列的初始索引
     * @param mid   中間索引
     * @param right 右邊索引
     * @param temp  暫存
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //初始化i，左邊有序序列的初始索引
        int i = left;
        //初始化j，右邊有序序列的初始索引
        int j = mid + 1;
        //指向temp陣列的當前索引
        int t = 0;

        //(一)
        //先把左右兩邊(有序)的數據按照規則填充到temp陣列
        //直到左右兩邊的有序序列，有一邊處理完畢為止
        while (i <= mid && j <= right) {
            //如果左邊的有序序列目前的元素，小於等於右邊有序序列的目前元素
            //即將左邊目前的元素，複製到temp陣列
            //然後t++ ,i++;
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else { //反之，將右邊的有序序列的元素，填充到temp
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //(二)
        //把有剩餘數據的一邊的剩餘數據，依次全部填充到temp
        while (i <= mid) { //左邊序列還有剩餘元素，全部填充到temp
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) { //左邊序列還有剩餘元素，全部填充到temp
            temp[t] = arr[j];
            t++;
            j++;
        }

        //(三)
        //將temp陣列的元素複製到arr
        //注意，並不是每次都複製所有
        //第一次合併 tempLeft = 0, right = 1;
        //第二次合併 tempLeft = 2, right = 3;
        //第三次合併 tempLeft = 0, right = 3;
        //最後一次 tempLeft  0 ,right = 7;
        t = 0;
        int tempLeft = left;
        //System.out.println(String.format("tempLeft = %s , right = %s ", tempLeft, right));
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

    }

}
