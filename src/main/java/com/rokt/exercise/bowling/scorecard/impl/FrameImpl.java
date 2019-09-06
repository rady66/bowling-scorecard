package com.rokt.exercise.bowling.scorecard.impl;

import com.rokt.exercise.bowling.scorecard.Frame;

class FrameImpl extends Frame {

    private int totalScore;

    FrameImpl(int ballOneScore,
              int ballTwoScore,
              boolean isStrike,
              boolean isSpare) {
        super(ballOneScore,ballTwoScore,isStrike, isSpare);
        this.totalScore = ballOneScore + ballTwoScore;
    }

    // Package methods -------------------------------------------

    int getTotalScore() {
        return this.totalScore;
    }

    void add(int score) {
        this.totalScore += score;
    }

}
