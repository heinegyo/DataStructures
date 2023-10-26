package com.heinegyo.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 將所有待比較數值統一為相同的數字長度，數位較短的數前面補零。然後，從最低位元開始，依序進行一次排序。
 * 這樣從最低位排序一直到最高位元排序完成以後, 數列就變成一個有序序列。
 * 1. 基數排序(radix sort) 屬於 分配是排序(distribution sort) ,
 *    又稱桶子法(bucket sort)
 *    它是透過鍵值的各個位的值，將要排序的元素分配到某些「桶」中，達到排序的作用
 * 2. 基數排序是經典的空間換時間的方式，佔用記憶體很大,
 *    當對海量資料排序時，容易造成 OutOfMemoryError 。
 * 3. 基數排序時穩定的。假定在待排序的記錄序列中，存在多個具有相同的關鍵字的記錄，若經過排序，這些
 *    記錄的相對次序保持不變，即在原序列中，
 *    r[i]=r[j]，且r[i]在r[j]之前，而在排序後的序列中，r[i]仍在r [j]之前，r[i]仍在 r[j]之前，
 *    則稱這種排序演算法是穩定的;否則稱為不穩定的
 * 4. 有負數的陣列，我們不用基數排序來進行排序, 如果要支持負數，參考: https://code.i-harness.com/zh-CN/q/e98fa9
 *
 */
public class RadixSort {

    public static void main(String[] args) {
        //int arr[] = {53, 3, 542, 748, 14, 214};
        int length = 80000000;
        int arr[] = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * 80000); //產生[0,8000000)數
        }

        Date start = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = simpleDateFormat.format(start);
        System.out.println("排序前的時間是 : " + startTime);

        radixSort(arr);

        Date end = new Date();
        String endTime = simpleDateFormat.format(end);
        System.out.println("排序後的時間是 : " + endTime);
    }

    public static void radixSort(int[] arr) {

        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];

        //根據前面的推導過程，可以得到最終的基數排序
        //1. 得到陣列中最大的數的個位數
        int max = arr[0]; //假設第一數就是最大數
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最後大數是幾位數
        int maxLength = (max + "").length();

        //這裡使用迴圈處理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //(針對每個元素的對應進行排序處理)，第一次是個位，第二次是十位，第三次是百位...
            for (int j = 0; j < arr.length; j++) {

                //取出每個元素的 個 位數值
                int digitOfElement = arr[j] / n % 10;
                //第幾個桶中 有多少元素 -> 例如:取餘為3 bucketElementCounts[3] => 個位數為3的桶中 有多少個元素
                //放入到對應的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照這個桶的順序(一維陣列的下標一次取出數據，放入原來陣列)
            int index = 0;
            //遍歷每一個桶，並將桶中的數據 放入原陣列
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果 桶中有數據，才放入到原陣列
                if (bucketElementCounts[k] != 0) {
                    //循環第k個桶(即第k個一維陣列)，放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                //第 i+1 輪處理後，需要將bucketElementCounts[k] = 0!!!
                bucketElementCounts[k] = 0;
            }

            // System.out.println("第" + (i + 1) + "輪排序後 : " + Arrays.toString(arr));
        }


    }


    /**
     * 基數排序方法 推導版本
     *
     * @param arr
     */
    public static void radixSortV1(int[] arr) {

        //第一輪排序(針對每個元素的個位進行排序處理)
        //定義一個二為陣列，表示10個桶，每個桶就是一個一維陣列
        //說明
        //1. 二維陣列包含10個一維陣列
        //2. 為了防止在放入數的時候，元素溢出，每一個一維陣列，大小定為arr.length
        //3. 名明確，基數排序是使用空間換時間的經典算法
        int[][] bucket = new int[10][arr.length];

        //為了紀錄每個桶中，實際存放了多少個數據，
        //定義一個一維陣列來紀錄各個桶的每次放入的數據個數
        int[] bucketElementCounts = new int[10];

        //第 1 輪(針對每個元素的個位進行排序處理)
        for (int j = 0; j < arr.length; j++) {
            //取出每個元素的 個 位數值
            int digitOfElement = arr[j] % 10;
            //第幾個桶中 有多少元素 -> 例如:取餘為3 bucketElementCounts[3] => 個位數為3的桶中 有多少個元素
            //放入到對應的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照這個桶的順序(一維陣列的下標一次取出數據，放入原來陣列)
        int index = 0;
        //遍歷每一個桶，並將桶中的數據 放入原陣列
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果 桶中有數據，才放入到原陣列
            if (bucketElementCounts[k] != 0) {
                //循環第k個桶(即第k個一維陣列)，放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            //第 1 輪處理後，需要將bucketElementCounts[k] = 0!!!
            bucketElementCounts[k] = 0;
        }

        System.out.println("第一輪排序後 : " + Arrays.toString(arr));

        //第 2 輪(針對每個元素的個位進行排序處理)
        for (int j = 0; j < arr.length; j++) {
            //取出每個元素的 十 位數值
            int digitOfElement = arr[j] / 10 % 10;
            //第幾個桶中 有多少元素 -> 例如:取餘為3 bucketElementCounts[3] => 個位數為3的桶中 有多少個元素
            //放入到對應的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照這個桶的順序(一維陣列的下標一次取出數據，放入原來陣列)
        index = 0;
        //遍歷每一個桶，並將桶中的數據 放入原陣列
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果 桶中有數據，才放入到原陣列
            if (bucketElementCounts[k] != 0) {
                //循環第k個桶(即第k個一維陣列)，放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            //第 2 輪處理後，需要將bucketElementCounts[k] = 0!!!
            bucketElementCounts[k] = 0;
        }
        System.out.println("第2輪排序後 : " + Arrays.toString(arr));

        //第 3 輪(針對每個元素的個位進行排序處理)
        for (int j = 0; j < arr.length; j++) {
            //取出每個元素的 百 位數值
            int digitOfElement = arr[j] / 100 % 10;
            //第幾個桶中 有多少元素 -> 例如:取餘為3 bucketElementCounts[3] => 個位數為3的桶中 有多少個元素
            //放入到對應的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照這個桶的順序(一維陣列的下標一次取出數據，放入原來陣列)
        index = 0;
        //遍歷每一個桶，並將桶中的數據 放入原陣列
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果 桶中有數據，才放入到原陣列
            if (bucketElementCounts[k] != 0) {
                //循環第k個桶(即第k個一維陣列)，放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            bucketElementCounts[k] = 0;
        }
        System.out.println("第3輪排序後 : " + Arrays.toString(arr));

    }
}
