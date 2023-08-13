package com.heinegyo.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolishNotation {

    public static void main(String[] args) {
        //先定義逆波蘭表達式
        //(30+4)x5-6 => 30 4 + 5 x 6 -
        // 4 * 5 - 8 + 60 + 8 / 2 = 4 5 * 8 - 60 + 8 2 / +
        //為了說明方便，逆波蘭表達式 的數字和符號使用空格隔開
        //String suffixExpression = "30 4 + 5 * 6 - ";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / + ";
        //思路
        //1. 先將 "3 4 + 5 x 6 - " => 放到ArrayList中
        //2. 將ArrayList 傳遞給一個方法，配合stack 完成計算

        List<String> list = getListString(suffixExpression);
        System.out.printf("list : %s \n", list);

        int res = calculate(list);
        System.out.printf("res : %d \n", res);

    }

    //將一個逆波蘭表達式，依次將數據和運算子放到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //將suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成對逆波蘭表達式的運算

    /**
     * 1. 從左至右掃描，將3和4push
     * 2. 遇到+運算子，因此彈出4和3(4為stack頂部元素，3為次頂元素)，計算3+4的值，得7 再push
     * 3. 將5入push
     * 4. 接下來是x運算子，因此彈出5和7，計算出7x5=35，Push 35
     * 5. 將6push
     * 6. 最後是一運算子，計算出35-6的值，即29由此得出最終結果
     */
    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            //這裡使用正則表達式來取出來數
            if (item.matches("\\d+")) { //匹配的是多位數
                //push
                stack.push(item);
            } else {
                //pop兩個數並運算，再push
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("運算子有誤！");
                }
                //把res push
                stack.push(Integer.toString(res));
            }
        }
        //最後stack中的數據就是結果
        return Integer.parseInt(stack.pop());
    }
}
