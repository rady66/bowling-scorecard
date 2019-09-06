package com.rokt.exercise.bowling.scorecard;

public class Frame {

    public final int ballOneScore;
    public final int ballTwoScore;
    public final boolean isSpare;
    public final boolean isStrike;

    public Frame(int ballOneScore,
                 int ballTwoScore,
                 boolean isStrike,
                 boolean isSpare) {

        this.ballOneScore = ballOneScore;
        this.ballTwoScore = ballTwoScore;
        this.isStrike = isStrike;
        this.isSpare = isSpare;
    }
}
