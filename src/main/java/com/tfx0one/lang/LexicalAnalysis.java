package com.tfx0one.lang;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-12-18 15:25
 * @projectName lang-learning
 */
public class LexicalAnalysis {

    private LinkedList<Token> tokenList = new LinkedList<>();

    private boolean findOperator(char ch) {
        return "+-*/".indexOf(ch) != -1;
    }

    private int findOperatorPosition(String expr) {

//        expr.chars()

//        boolean b = expr.chars().anyMatch(ch -> "+-*/".indexOf(ch) != -1);
//        if (!b) {
//            return -1;
//        }

        for (int i = 0; i < expr.length(); i++) {
            if ("+-*/".indexOf(expr.charAt(i)) != -1) {
                return i;
            }
        }
        return -1;

//        int position = -1;
//        for (char c : "+-*/".toCharArray()) {
//            if ((position = expr.indexOf(c)) != -1) {
//                return position;
//            }
//        }
//        return position;
    }

    public LexicalAnalysis(String expr) {

//        int lastCuror = 0; //游标

//        int length = expr.length();
        String target = expr.trim();
//        char[] chars = target.toCharArray();
//
//        for (int i = 0; i < chars.length; i++) {
//            if (findOperator(chars[i]))  {
//                this.createToken(expr.substring(lastCuror, i));
//                lastCuror = i + 1;
//            }
//            if (i == length - 1) { //最后一个
//
//
//                this.createToken(expr.substring(lastCuror));
//            }
//        }

        int position;
        while ((position = this.findOperatorPosition(target)) != -1) {
            this.createToken(target.substring(0, position));
            this.createToken(String.valueOf(expr.charAt(position)));
            target = target.substring(position+1);
            System.out.println("position = " + position);
            System.out.println("target = " + target);
        }

//        for (int i = 0; i < length; i++) {
//            if (findOperator(expr.charAt(i))) { //找到了符号创建符号
//                this.createToken(expr.substring(lastCuror, i));
//                this.createToken(String.valueOf(expr.charAt(i)));
//                lastCuror = i + 1;
//            }
//        }
        //最后一个
        this.createToken(target);

//        while("+-*/".indexOf(expr) != -1) {
//        }

        this.tokenList.forEach(System.out::println);
    }

    void createToken(String e) {
        this.tokenList.add(new Token(e));
    }

    Token read() {
        return tokenList.removeFirst();
    }
}
