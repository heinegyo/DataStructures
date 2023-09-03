package com.heinegyo.recursion;

/**
 * 遞迴-八皇后問題(回溯算法)
 * 問題:
 * 在8×8格的西洋棋盤上擺放八個皇后，都不能相互攻擊，
 * 即：兩個皇后都不能有同一行、同一列或同一斜線，問有多少種擺法(92)。
 *
 * 分析:
 * 1. 第一个皇后先放row 1 column 1
 * 2. 第二個皇后放置 row2 column 1、然後判斷是否OK，
 *    如果不OK，繼續放置column 2、column 3、依次把所有column都放完，找到一個合適的
 * 3. 繼續第三個皇后，還是column1、column2⋯⋯直到第8個皇后也能放在一個不衝突的位置，找到了一個正確的解
 * 4. 當得到一個正確的解時，在stack回退到上一個stack時，就會開始回溯，
 *    即將第一個皇后，擱置column1的所有正確解，全部得到。
 * 5. 然後回頭繼續第一個皇后放column2，繼續後面循環執行1,2,3,4的步驟
 *
 * 說明：理論上應該建立一個二維陣列來表示棋盤，但實際上可以通過算法，
 *      用一個一維陣列就可以解決問題 arr[8]= {0,4,7,5,2,6,1,3}
 *      對應arr下標表示對應的row，即第幾個皇后，arr[i] = val,
 *      val 表示第 i+1 個皇后，放在row i+1 、column val+1
 */
public class EightQueens {

    //定義一個max表示共有多少個皇后
    int max = 8;
    //定義Array 保存皇后放置位置的結果,比如arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;


    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.check(0);
        System.out.printf("一共有 %d 個解法 ", count);
        System.out.printf("一共判斷 %d 次 ", judgeCount);
    }

    //編寫一個method，放置第n個皇后
    //特別注意: check 是每一次遞迴時，進入到check方法，
    //         都走一遍for(int i = 0 ; i < max ; i++)，因此會有回溯
    private void check(int n){
        if (n == max) { //n=8時,等於8個Queen已經擺放好了
            print();
            return;
        }

        //依次放入皇后，並判斷是否衝突
        for (int i = 0; i < max; i++) {
            //先把當前這個Queen ，放到該row的第一個column
            //n -> row  : i -> column
            array[n] = i;
            //當放置第n個皇后到第 i column時，是否衝突
            if (judge(n)){
                //不衝突的情況下，繼續放第n+1個皇后，開始遞迴
                check(n+1);
            }
            //如果衝突，就繼續執行array[n] = i;即將第n個皇后，放置在column後移的一個位置
        }
    }

    /**
     * 查看當我們放置第n個皇后，就是檢測皇后是否和前面已經擺放的皇后衝突
     * @param n
     * @return
     */
    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //說明
            //1. array[i]  == array[n] 表示判斷第n個皇后是否和前面的n-1個皇后在同一column
            //2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判斷第n個皇后是否與第i個皇后在同一斜線
            //   n=1 放置在column 2 1 n = 1 array[1] = 1
            //   Math.abs(1-0) == 1 Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
            //   你最好畫個圖，比較好懂
            //3. 判斷是否在同一行，沒有必要，n每次都在同一行
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }


    //寫一個方法，可以將皇后擺放的位置輸出
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
