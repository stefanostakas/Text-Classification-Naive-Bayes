package com.stefanostakas;

public class WordLikelihood {
    private final String word;
    private final double positiveLikelihood;
    private final double negativeLikelihood;

    public WordLikelihood(String word, double positiveLikelihood, double negativeLikelihood) {
        this.word = word;
        this.positiveLikelihood = positiveLikelihood;
        this.negativeLikelihood = negativeLikelihood;
    }

    public String getWord() {
        return word;
    }

    public double getPositiveLikelihood() {
        return positiveLikelihood;
    }

    public double getNegativeLikelihood() {
        return negativeLikelihood;
    }

    public String toString() {
        return "Word: " + word + ", Positive Likelihood: " + positiveLikelihood + ", Negative Likelihood: " + negativeLikelihood;
    }

}

