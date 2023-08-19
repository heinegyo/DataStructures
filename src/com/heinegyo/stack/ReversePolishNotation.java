package com.heinegyo.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolishNotation {

    public static void main(String[] args) {

        //完成將一個中綴表示法轉乘後綴表示法的功能
        //說明
        //1. 1+((2+3)x4)-5 => 轉成 1 2 3 + 4 x + 5 -
        //2. 因為直接對str 進行操作，不方便，因此先將"1+((2+3)x4)-5" 裝進list
        //   即"1+((2+3)x4)-5" => ArrayList[1,+,(,(,2,+,3,),x,4,),-,5]
        //3. 將得到的中綴表達式對應的list -> 後綴表示法對應的list
        //   即ArrayList[1,+,(,(,2,+,3,),x,4,),-,5] => ArrayList[1,2,3,+,4,*,+,5,-]
        String expression = "1+((2+3)*4)-5";

        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中綴表達式對應的list");
        System.out.println(infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("後綴表達式對應的list");
        System.out.println(suffixExpressionList);

        System.out.printf("expression = %d ", calculate(suffixExpressionList));
    }

    // public static void main(String[] args) {
    //     //先定義逆波蘭表達式
    //     //(30+4)x5-6 => 30 4 + 5 x 6 -
    //     // 4 * 5 - 8 + 60 + 8 / 2 = 4 5 * 8 - 60 + 8 2 / +
    //     //為了說明方便，逆波蘭表達式 的數字和符號使用空格隔開
    //     //String suffixExpression = "30 4 + 5 * 6 - ";
    //     String suffixExpression = "4 5 * 8 - 60 + 8 2 / + ";
    //     //思路
    //     //1. 先將 "3 4 + 5 x 6 - " => 放到ArrayList中
    //     //2. 將ArrayList 傳遞給一個方法，配合stack 完成計算
    //
    //     List<String> list = getListString(suffixExpression);
    //     System.out.printf("list : %s \n", list);
    //
    //     int res = calculate(list);
    //     System.out.printf("res : %d \n", res);
    // }

    //即ArrayList[1,+,(,(,2,+,3,),x,4,),-,5] => ArrayList[1,2,3,+,4,*,+,5,-]
    //方法:將得到的中綴表達式對應的List => 後綴表達式對應的List
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定義兩個stack
        Stack<String> s1 = new Stack<String>(); //operator stack
        //說明：因為s2這個stack，在整個轉換過程中，沒有pop，而且後面還需要逆序輸出
        //因此，這裡不用Stack<String> 改用List<Sting> s2
        //Stack<String> s2 = new Stack<String>();//儲存中間結果的 stack 2
        List<String> s2 = new ArrayList<String>(); //儲存中間結果的List s2

        //遍歷
        for (String item : ls) {
            //如果是一個數，就加入到s2
            if(item.matches("\\d+")){
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括號")"，則依次彈出s1棧頂的運算子，並push到s2，直到遇到左括號為止，此時將這一括號丟棄
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//!!!! 將 ( 彈出 s1 ，消除括號
            } else {
                //當item的優先級小於等於s1棧頂運算子，
                //將s1棧頂的運算子彈出並加入到s2中，再次轉到(4.1)與s1中新的棧頂運算符相比
                //問題：缺少一個比較優先級Method
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //還需要將item push 到stack中
                s1.push(item);
            }
        }

        //將s1中剩餘的運算子依次彈出並加入s2
        while (s1.size() != 0 ){
            s2.add(s1.pop());
        }

        return s2;//注意因為是存放到List，因此按順序輸出就是對應的後綴表達式對應的List
    }


    //方法：將中綴表示法轉換成對應的List
    public static List<String> toInfixExpressionList(String expression) {
        //定義一個List，存放中綴表達式，對應的內容
        List<String> ls = new ArrayList<>();
        int i = 0; //這相當於是指針，用於遍歷中綴表示法字串
        String str; //對多位數的拼接
        char c; //每遍歷到一個字元，就放入c
        do {
            //如果c是一個非數字，就需要加入ls
            if ((c = expression.charAt(i)) < 48 || (c = expression.charAt(i)) > 57) {
                ls.add("" + c);
                i++; //i需要後移
            } else { //如果是一個數，需要考慮多位數
                str = "";//先將str，置成"" '0'[48] -> '9'[57]
                while (i < expression.length() && (c = expression.charAt(i)) >= 48 && (c = expression.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < expression.length());
        return ls;
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

//編寫一個類Operation 可以回傳一個運算子，對應的優先級
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int SUL= 2;
    private static int DIV= 2;

    //寫一個方法，回傳對應的優先級數字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = SUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在該運算子");
                break;
        }
        return result;
    }
}
/**
 * 中綴表達式轉換為後綴表達式
 * 具体步骤如下：
 * 1. 初始化兩個stack：operator stack s1 和 儲存中間結果的stack s2;
 * 2. 從左至右掃描中綴表達式；
 * 3. 遇到數字時，將其push到s2;
 * 4. 遇到運算子時，比較其與s1 stack 頂部運算子的優先級
 *      4-1. 如果 s1 為空，或 stack頂部 運算子 為左括號"("，則直接將此運算子入棧；
 *      4-2. 若優先級比stack 頂部運算子的高，也將運算子 push 到 s1
 *      4-3. 否則，將s1 頂部的運算子彈出並push到s2中，
 *           再次轉到(4-1)與s1中新的stack頂部運算子進行比較
 * 5. 遇到括號時：
 *      5-1. 如果是左括號"(" 直接push到s1
 *      5-2. 如果是右括號")",則依次彈出s1 stack 頂部的運算子，
 *           並push 到 s2，直到遇到左括號為止，此時將這一對括號丟棄
 * 6. 重複步驟2至5，直到表達式的最右邊
 * 7. 將s1中剩餘的運算子依次彈出並push 到 s2
 * 8. 依次彈出s2中的元素並輸出，結果的逆序即為中綴表達式對應的後綴表達式
 *
 */