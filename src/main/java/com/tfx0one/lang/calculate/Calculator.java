package com.tfx0one.lang.calculate;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-12-19 15:32
 * @projectName lang-learning
 */
public class Calculator {

    private boolean isOperator(String expr, int index) {
        return isOperator(expr.charAt(index));
    }

    private boolean isOperator(char c) {
        return "+-*/".indexOf(c) != -1;
    }

    private boolean hasBrackets(String expr) {
        return expr.indexOf('(') != -1 || expr.indexOf(')') != -1;
    }


    private int findNextOperatorPosition(StringBuilder expr, int formIndex) {
        int position = -1;
        for (char c : "+-*/".toCharArray()) {
            if ((position = expr.indexOf(String.valueOf(c), formIndex)) != -1) {
                return position;
            }
        }
        return position;
    }

    //从后向前找
    private int findLastOperatorPosition(String expr) {
        int position = -1;
        for (char c : "+-*/".toCharArray()) {
            if ((position = expr.lastIndexOf(c)) != -1) {
                return position;
            }
        }
        return position;
    }


    public double _cal(String exp) {
//        if("".equals(exp)) {
//            return .0;
//        }

        int position = findLastOperatorPosition(exp);

        if (position == -1) {
            return Double.parseDouble(exp.trim());
        } else {
//            System.out.println(exp);
//            System.out.println(position);
            String left = exp.substring(0, position);
            char op = exp.charAt(position);
            String right = exp.substring(position + 1);

            switch (op) {
                case '+':
                    return _cal(left) + _cal(right);
                case '-':
                    return _cal(left) - _cal(right);
                case '*':
                    return _cal(left) * _cal(right);
                case '/':
                    return _cal(left) / _cal(right);
                default:
                    throw new RuntimeException("运算符有误！");

            }
        }
    }

    public double calculate(String expr) {

        expr = prepareExpress(expr);

        while (expr.indexOf(')') != -1) {
            int end = expr.indexOf(')');
            int begin = expr.lastIndexOf('(', end);

            String left = expr.substring(0, begin);
            String middle = String.valueOf(calculate(expr.substring(begin + 1, end)));
            String right = expr.substring(end + 1);

            expr = left + middle + right;
            System.out.println("middle = " + middle);
            System.out.println("expr = " + expr);

        }

        return _cal(expr);

    }

    public double calculate2(String expr) {

        expr = prepareExpress(expr);

        //没有括号
        if (!hasBrackets(expr)) {
            return _cal(expr.trim());
        } else {
            //有括号。把括号作为整体看待。以括号为最小逻辑单元。找到括号外侧的运算符位置，  找到运算符的位置。用以分割字符串。
            int operatorBracketsPosition;

            //括号外存在运算符
            if ((operatorBracketsPosition = findLastBracketsOperator(expr)) == -1) {
                //在存在括号的情况下。没有找到运算符, 只能是 (1+1) 或者 ((2)
                // ，去括号, 再次递归，存在多括号情况
                String noBracketsStr = expr.trim().substring(1, expr.trim().length() - 1);
                return calculate2(noBracketsStr);
            } else {
                //找到 运算符位置 以运算符为为分割点
                String left = expr.substring(0, operatorBracketsPosition);
                char op = expr.charAt(operatorBracketsPosition);
                String right = expr.substring(operatorBracketsPosition + 1);
                switch (op) {
                    case '+':
                        return calculate2(left) + calculate2(right);
                    case '-':
                        return calculate2(left) - calculate2(right);
                    case '*':
                        return calculate2(left) * calculate2(right);
                    case '/':
                        return calculate2(left) / calculate2(right);
                    default:
                        throw new RuntimeException("运算符有误！");
                }
            }

        }


    }

    //把空括号作为整体。找到括号外侧的运算符位置
    private int findLastBracketsOperator(String expr) {

        int end = expr.lastIndexOf(')');

        if (end == -1) {
            return -1;
        }

        int leftBracketAmount = 0;
        int rightBracketAmount = 1;

        int begin = end;

        while (--begin >= 0 && leftBracketAmount != rightBracketAmount) {
            leftBracketAmount += expr.charAt(begin) == '(' ? 1 : 0;
            rightBracketAmount += expr.charAt(begin) == ')' ? 1 : 0;
        }

//        String subExpr = expr.substring(begin + 1, end + 1);
//        System.out.println("subExpr: " + subExpr);

        //查找括号左右两边的运算符
        int findLeftIndex = begin + 1;
        while (--findLeftIndex > 0) {
            if (isOperator(expr, findLeftIndex)) {
                return findLeftIndex;
            }
        }

        int findRightIndex = end;
        while (++findRightIndex < expr.length()) {
            if (isOperator(expr, findRightIndex)) {
                return findRightIndex;
            }
        }

        return -1;
    }


    private String prepareExpress(String expr) {
        expr = expr.replace(" ", "");
        //数字 前面 存在 + - 号 必须加上  括号 和 0   如 -1 => (0-1)
//        StringBuilder stringBuffer = new StringBuilder(expr);
//        int length = expr.length();
//        for (int i = 0; i < length; i++) {
//            char c = expr.charAt(i);
//            char after = expr.charAt(i + 1);
//
//            //运算符后面还是运算符
//            if (isOperator(c) && isOperator(after)) {
//                if (after == '-' || after == '+') {
//                    int endPos = findNextOperatorPosition(stringBuffer, i + 2);
//                    stringBuffer.insert(endPos - 1, ')');
//                }
//            }
//        }
//
////        stringBuffer.insert(1, '0');
//        System.out.println("stringBuffer = " + stringBuffer);
        return expr;
    }

    public static void main(String[] args) {
        new Calculator().prepareExpress("-1 * 2 + -3 / -2");
//        double v = new Calculator()._cal("-1 * 2 + -3 / -2");
//        System.out.println("v = " + v);
    }


}
