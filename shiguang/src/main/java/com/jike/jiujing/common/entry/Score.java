package com.jike.jiujing.common.entry;

public class Score {
    private String name;
    private Integer score;

    public Score(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }
}
