package com.heinegyo.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int resIndex = binarySearch(arr, 0, arr.length - 1, 899);

        System.out.println("resIndex = " + resIndex);
        int arr2[] = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> resIndexList = binarySearchForResList(arr2, 0, arr.length - 1, 89);
        System.out.println(resIndexList);

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
        if (left > right) {
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

    /**
     * 如果陣列中有多個相同的值？
     * {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};
     * 1. 在找到mid 索引值，不直接回傳
     * 2. 掃描左邊，將所有滿足1000的數值索引加入到ArrayList中
     * 3. 掃描右邊，將所有滿足1000的數值索引加入到ArrayList中
     * 4. 將ArrayList回傳
     */
    public static List<Integer> binarySearchForResList(int[] arr, int left, int right, int findVal) {
        //當left > right 時，說明遞迴整個陣列，但是沒有找到
        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            //向右遞迴
            return binarySearchForResList(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearchForResList(arr, left, mid - 1, findVal);
        } else {
            List<Integer> resIndexList = new ArrayList<>();
            resIndexList.add(mid);
            int temp = mid - 1;
            while (arr[temp] == findVal && temp > 0) {
                resIndexList.add(temp);
                temp--;
            }
            temp = mid + 1;
            while (arr[temp] == findVal && temp < arr.length - 1) {
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }
    }
}
