package com.tfx0one.lang;

import lombok.Data;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-12-18 15:04
 * @projectName lang-learning
 */
@Data
public class Token {

    public Token(String value) {
        this.value = value.trim();
        this.type = "+-*/".indexOf(this.value) != -1 ? Type.BinaryExpr : Type.NumberLiteral;
    }

    private static enum Type {
        NumberLiteral, BinaryExpr
    }

    private Type type;

    private String value;

    private Token left;
    private String operator;
    private Token right;

    public Double calculate() {
        if (this.type == Type.BinaryExpr) {
            switch (this.operator) {
                case "+":
                    return left.calculate() + right.calculate();
                case "-":
                    return left.calculate() - right.calculate();
                case "*":
                    return left.calculate() * right.calculate();
                case "/":
                    return left.calculate() / right.calculate();
                default:
                    return .0;
            }
        } else {
            return Double.parseDouble(value);
        }
    }
}
