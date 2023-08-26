package com.heinegyo.recursion;

public class Maze {

    public static void main(String[] args) {
        //先建立二維陣列，模擬迷宮
        //地圖
        int[][] map = new int[8][7];
        //使用1表示牆
        //上下全部為1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //左右全部為一
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //設置擋版，1表示
        map[3][1] = 1;
        map[3][2] = 1;
        // map[1][2] = 1;
        // map[2][2] = 1;

        //輸出地圖
        System.out.println("原始地圖");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用遞迴找出迷宮出口
        boolean result = setWay(map, 1, 1);
        System.out.println("是否找到出口： " + result);

        //輸出新的地圖，小球走過，並且標示過的地圖
        System.out.println("走過的地圖");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }


    /**
     * 使用遞迴回溯 recursive backtracker 給小球找路
     * 說明
     * 1. map 表示地圖
     * 2. i,j 表示從地圖的哪個位置開始出發(1,1)
     * 3. 如果小球能到map[6][5]位置，則說明通路找到
     * 4. 約定：當map[i][j] 0:表示該節點沒有走過 1: 表示牆  2:表示通路可以走 3:表示該點已經走過，但是走不通
     * 5. 在走迷宮時，需要一個確定的策略(方法) 下->右->上->左，如果該點走不通再回溯
     *
     * @param map 表示地圖
     * @param i   從哪個位置開始找
     * @param j
     * @return 找到出口 return true，否則return false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        //說明找到出口
        if (map[6][5] == 2) {
            return true;
        }

        //如果目前該節點還沒有走過
        if (map[i][j] == 0) {
            //按照策略 下->右->上->左 走
            map[i][j] = 2; //假定該點是可以走通
            if (setWay(map, i + 1, j)) { //向下走
                return true;
            } else if (setWay(map, i, j + 1)) { //向右走
                return true;
            } else if (setWay(map, i - 1, j)) { //向上
                return true;
            } else if (setWay(map, i, j - 1)) {
                return true;
            } else {
                //說明該點是走不同的(死路)
                map[i][j] = 3;
                return false;
            }
        }else { // 如果map[i][j] != 0 , 可能是1,2,3
            return false;
        }
    }
}
