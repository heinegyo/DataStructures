package com.heinegyo.search;

public class SeqSearch {


    public static void main(String[] args) {
        int arr[] = {1, 9, 11, -1, 34, 89};
        int index = seqSearch(arr, 11);
        if (index == -1){
            System.out.println("404 not found");
        }else {
            System.out.println("該數值的索引為 : " + index);
        }
    }

    /**
     * 實現線性查詢是找到一個滿足條件的值，就回傳
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        //線性查詢是逐一比對，發現相同值，回傳索引值
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }

        return -1;
    }
}
