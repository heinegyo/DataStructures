package com.heinegyo.recursion;

/**
 * 遞迴需要遵守的重要規則
 * 1. 執行一個方法時，就建立一個新的受保護的獨立空間(stack)
 * 2. 方法的區域變數是獨立的，不會相互影響
 * 3. 如果方法中使用的是引用類型的變數，就會共享該類型的數據，比如Array
 * 3. 遞迴必須向退出遞迴的條件逼近，否則就StackOverFlowError
 * 4. 當一個方法執行完畢，或著遇到return，
 *    就會return，遵守誰呼叫，就將結果return給誰
 *    同時當方法執行完畢或著return時，該方法也就執行完畢
 */
public class RecursionTest {

    public static void main(String[] args) {
        //通過print，回顧遞迴
        //test(4);

        int res = factorial(3);
        System.out.println("res = " + res);

    }

    /**
     * print
     * @param n
     */
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }// else {
            System.out.println("n= " + n);
        //}
    }

    /**
     * factorial
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
