package com.heinegyo.search;

public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int resIndex = binarySearch(arr, 0, arr.length - 1, 899);

        System.out.println("resIndex = " + resIndex);

    }

    /**
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return 如果找到就回傳索引，如果沒有找到就回傳-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //當left > right 時，說明遞迴整個陣列，但是沒有找到
        if (left > right){
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            //向右遞迴
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
