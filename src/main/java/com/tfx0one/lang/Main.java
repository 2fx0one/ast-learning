package com.tfx0one.lang;

import com.tfx0one.lang.calculate.Calculator;

/**
 * 描述
 * <p>
 * 源码 -词法分析-> 单词排列 -语法分析-> 抽象语法树  -解释器-> 执行
 * -编译器-> 代码生成
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-12-18 14:42
 * @projectName lang-learning
 */
public class Main {

    public static void test1(String expr) {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            double v = new Calculator().calculate(expr);
        }
        long duration = System.currentTimeMillis() - begin;
        System.out.println("duration = " + duration);
        System.out.println("new Calculator().calculate(expr) = " + new Calculator().calculate(expr));
    }


    public static void test2(String expr) {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            double v = new Calculator().calculate2(expr);
        }
        long duration = System.currentTimeMillis() - begin;
        System.out.println("duration = " + duration);
        System.out.println("new Calculator().calculate(expr) = " + new Calculator().calculate2(expr));
    }

    public static void main(String[] args) {
//        test2(" (1-9)/2 ");
//        test2("(1+2*3/4-6)+((2+5)+(2/5)*(1/4))");
//        System.out.println("v = " +v);
//        System.out.println("v2 = " + v2);
        new LexicalAnalysis("111 + 222 - 333 * 444 / 555 - 666   ");
    }
}
