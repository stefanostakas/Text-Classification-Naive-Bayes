package com.stefanostakas;

import java.util.List;
import java.util.Map;

public class TrainingData {
    private List<Map.Entry<String, int[]>> topWords;
    private int totalPosRev;
    private int totalNegRev;

    public TrainingData(List<Map.Entry<String, int[]>> topWords, int totalPosRev, int totalNegRev) {
        this.topWords = topWords;
        this.totalPosRev = totalPosRev;
        this.totalNegRev = totalNegRev;
    }

    public TrainingData(String recordType, String categoryNames, String programCapabilities) {
    }

    // getters and setters
    public List<Map.Entry<String, int[]>> getTopWords() {
        return topWords;
    }

    public void setTopWords(List<Map.Entry<String, int[]>> topWords) {
        this.topWords = topWords;
    }

    public int getTotalPosRev() {
        return totalPosRev;
    }

    public void setTotalPosRev(int totalPosRev) {
        this.totalPosRev = totalPosRev;
    }

    public int getTotalNegRev() {
        return totalNegRev;
    }

    public void setTotalNegRev(int totalNegRev) {
        this.totalNegRev = totalNegRev;
    }
}
