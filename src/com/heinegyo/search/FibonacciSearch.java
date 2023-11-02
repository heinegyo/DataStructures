package com.heinegyo.search;

import java.util.Arrays;

public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        //int[] arr = {1, 8, 10, 89, 1000, 1234};
        int[] arr = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
        System.out.println(fibonacciSearch(arr, 1));
    }

    //因為我們mid = low + F(k-1)-1，需要用到Fibonacci陣列，
    public static int[] fibonacci() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * @param arr
     * @param key
     * @return 回傳對應的索引值，查無該值回傳-1
     */
    public static int fibonacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;//表示 Fibonacci 分割值的索引
        int mid = 0; //存放mid值
        int f[] = fibonacci(); //獲取Fibonacci array
        //獲取到Fibonacci array 分割值的索引
        while (high > f[k] - 1) {
            k++;
        }
        //因為f[k]值 可能大於arr的長度，因此我們需要使用Arrays類，建構一個新的陣列，並指向arr[]
        //不足的部分會使用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        //實際上需求使用arr[] 最後的數填充temp
        //舉例:
        //temp = {1, 8, 10, 89, 1000, 1234 , 0 , 0, 0} =>
        //temp = {1, 8, 10, 89, 1000, 1234 , 1234 ,1234,1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        //使用while來循環處理，找到我們的數key
        while (low <= high) { //只要這個條件滿足，就可以找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { //我們應該繼續向陣列的前面搜尋(左邊)
                high = mid - 1;
                //為什麼是k--
                //說明
                //1. 全部元素 = 前面的元素 + 後面元素
                //2. f[k] = f[k-1] + f[k-2]
                //因為前面有f[k-1]個元素，所以可以繼續拆分 f[k-1] = f[k-2] + f[k-3]
                //即在 f[k-1] 的前面繼續查詢
                //即下次循環mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                //為什麼是 k-=2?
                //說明
                //1. 全部元素= 前面的元素 + 後面的元素
                //2. f[k] = f[k-1] + f[k-2]
                //3. 因為我們後面有f[k-2] 所以可以繼續拆分 f[k-1] = f[k-3] + f[k-4]
                //4. 即在f[k-2] 的前面進行查詢 k-=2
                //5. 即下次循環 mid = f[k -1 -2] -1
                k -= 2;
            } else { //找到
                //需要確定，回傳的是哪個索引
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}