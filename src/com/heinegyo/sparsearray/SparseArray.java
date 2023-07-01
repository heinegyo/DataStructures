package com.heinegyo.sparsearray;

public class SparseArray {
    public SparseArray() {
    }

    public static void main(String[] args) {
        /**
         * 二維陣列 轉 稀疏陣列
         * 1.遍歷原始的二維陣列，得到有效數據的個數總和sum
         * 2.根據sum就可以建立稀疏陣列sparseArr int[sum+ 1][3]
         * 3.將二維陣列的有效數據存入到稀疏陣列
         */

        //建立一個原始的二維陣列
        //0: 表示沒有棋子 1:表示黑子 2:表示白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[6][7] = 2;

        //輸出原始的二維陣列
        System.out.println("原始的二維陣列");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //1.遍歷原始的二維陣列，得到有效數據的個數總和sum
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2.根據sum就可以建立稀疏陣列sparseArr int[sum+ 1][3]
        int sparseArr[][] = new int[sum + 1][3];
        //給稀疏陣列賦值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍歷二維陣列，將非0的值存放到sparseArr 中
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //輸出稀疏陣列的形式
        System.out.println();
        System.out.println("得到稀疏陣列");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();

        /**
         * 將稀疏陣列 恢復成 原始二維陣列
         * 1. 先讀取稀疏陣列的第一列，根據第一列的數據，建立原始的稀疏陣列，
         *    比如上面的 chessArr2 = int[11][11]
         * 2. 在讀取稀疏陣列後幾列的資料，並賦值給 原始二維陣列
         */

        //1. 先讀取稀疏陣列的第一列，根據第一列的數據，建立原始的稀疏陣列
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2. 在讀取稀疏陣列後幾列的資料，並賦值給 原始二維陣列
        for (int i = 1; i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //輸出還原的二維陣列
        System.out.println("還原的二維陣列");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }


}