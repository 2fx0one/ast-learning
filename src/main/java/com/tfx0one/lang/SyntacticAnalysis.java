package com.tfx0one.lang;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-12-18 16:19
 * @projectName lang-learning
 */
public class SyntacticAnalysis {

    private final LexicalAnalysis lexicalAnalysis;
    public SyntacticAnalysis(LexicalAnalysis lexicalAnalysis) {
        this.lexicalAnalysis = lexicalAnalysis;
    }

    public AnalysisNode analyze() {
        return null;
    }
}
