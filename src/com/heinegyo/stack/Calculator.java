package com.heinegyo.stack;

/**
 * 題目: 使用stack 完成一個表達式的計算結果
 * 7*2*2-5+1-5+3-4 = ?
 * <p>
 * 解題思維
 * 1. 通過一個index值(索引)，來遍歷表達式
 * 2. 如果我們發現是一個數字，就直接push
 * 3. 如果發現掃描到是一個符號，就區分以下情形
 * 3-1. 如果發現目前的operator stack 為空、就直接push
 * 3-2. 如果符號有operator，就進行比較，
 * 3-2-1. 如果目前的運算子的優先級小於或者等於stack中的運算子
 * 就需要從num stack中pop出兩個數，
 * 再從operator stack中pop出一個符號，進行運算
 * 將得到的結果，入num stack，然後將目前的運算子push
 * 3-2-2. 如果目前的運算子的優先級大於stack中的運算子，直接push
 * 4. 當表達式掃描完畢、就順序的從num stack和 operator stack 中pop 出相應的數字和符號
 * 並進行計算
 * 5. 最後在stack中的數字就是表達式的結果
 */
public class Calculator {

    public static void main(String[] args) {
        //String expression = "70+20*6-4"; //如何處理多位數的問題？
        String expression = "7*2*2-5+1-5+3-4";
        //建立兩個stack
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operatorStack = new ArrayStack2(10);
        //定義需要的相關變數
        int index = 0; //用於掃描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//將每次掃瞄得到的char保存到ch
        String keepNum = "";//用於拼接多位數
        //開始while循環掃描expression
        while (true) {
            //依次得到expression 的每一個字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判斷ch是數字還是運算子，做相應的處理
            if (operatorStack.isOperator(ch)) {
                //判斷目前的operatorStack是否為空
                if (!operatorStack.isEmpty()) {
                    //如果符號有operator，就進行比較，
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                        //如果目前的運算子的優先級小於或者等於stack中的運算子
                        //就需要從num stack中pop出兩個數，
                        //再從operator stack中pop出一個符號，進行運算
                        //將得到的結果，入num stack，然後將目前的運算子push
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operatorStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把運算結果push到numStack
                        numStack.push(res);
                        //如後將目前的運算子push到operatorStack
                        operatorStack.push(ch);
                    } else {
                        //如果目前的運算子的優先級大於stack中的運算子，直接push
                        operatorStack.push(ch);
                    }
                } else {
                    //為空直接push
                    operatorStack.push(ch);
                }
            } else { //如果是num，則直接入numStack
                //注意1，char字元與整數值相差48! 字元'1'在ASCII碼中對應的是49
                //注意2，多位數問題
                // 2-1 當處理多位數時，不能發現一個數就立即push，因為他可能是多位數
                // 2-2 在處理數，需要向expression的表達式的index 後再看一位，如果是operator才push
                // 2-3 因此我們需要訂一個變數，字串，用於拼屆

                //處理多位數
                keepNum += ch;

                //如果ch已經是expression的最後一位，就直接push
                if (index == expression.length()-1){
                    numStack.push(Integer.valueOf(keepNum));
                }else {
                    //判斷下一個字元是不是數字，如果是數字就繼續掃描，如果是運算子，就push
                    //注意是往後看一位，不要變動index
                    if (operatorStack.isOperator(expression.substring(index+1,index+2).charAt(0))){
                        //如果後一位是運算子，則push
                        numStack.push(Integer.valueOf(keepNum));
                        //重要！！！！，keepNum清空
                        keepNum = "";
                    }
                }
            }

            //讓index+1 並判斷是否掃描到expression最後
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //當表達式掃描完畢、就順序的從num stack和 operator stack 中pop 出相應的數字和符號並進行計算
        while (true) {
            //如果numStack為空，則計算到最後的結果，numStack中只有一個數字{結果}
            if (operatorStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operatorStack.pop();
            res = numStack.cal(num1, num2, oper);
            //把運算結果push到numStack
            numStack.push(res);
        }
        //將numStack最後的結果 ,pop出 就是結果
        System.out.printf("表達式 %s = %d", expression, numStack.pop());

    }


}

//基於原有的stack、擴充功能
class ArrayStack2 {
    //stack的大小
    private int maxSize;
    private int[] stack;
    //top 表示頂部，預設為-1
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("stack is full!");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        //先判斷stack是否空
        if (isEmpty()) {
            throw new RuntimeException("stack is empty!!");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("stack is empty!!");
        }
        return stack[top];
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("stack is empty");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //回傳運算子的優先級，優先級是人員決定，優先級使用數字表示
    //數字越大、擇優先級越高
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1; //假設目前的表達式只有+,-,*,/
        }
    }

    //判斷是不是一個運算子
    public boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //計算方法
    public int cal(int num1, int num2, int operator) {
        //用於儲存計算結果
        int res = 0;
        switch (operator) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意順序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num2;
                break;
            default:
                break;
        }
        return res;
    }
}
