package com.rokt.exercise.bowling.scorecard;

import java.util.List;

public interface IScoreCard {

    boolean isCompleted();

    int getScore();

    List<Frame> getFrames();

    void score(int ballOneScore, int ballTwoScore);

    void scoreLast(int ballOneScore, int ballTwoScore, int ballThreeScore);
}
