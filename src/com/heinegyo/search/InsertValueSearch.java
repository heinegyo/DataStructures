package com.heinegyo.search;


/**
 * 1.對於資料量較大，分布較均勻的搜尋來說，速度較快
 * 2.分布不均勻的情況下，該方法不一定比BinarySearch好
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        //System.out.println(Arrays.toString(arr));
        int i = insertValueSearch(arr, 0, arr.length - 1, 1);
        System.out.println(i);

        int i2 = BinarySearch.binarySearch(arr, 0, arr.length - 1, 1);
        System.out.println(i2);
    }

    /**
     * insertValue 要求陣列是有序的
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("Insert Value Search 次數");
        //注意：findVal < arr[0] 和 findVal > arr[arr.length - 1] 判斷是必要的
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        //求出mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal) { // 說明應該相右邊遞迴
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

}
