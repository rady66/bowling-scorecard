package com.rokt.exercise.bowling.scorecard;

import java.util.List;

/**
 * Score Card object representation in Ten-pin Bowling Game.
 */
public abstract class ScoreCard {

    /* List of score card frames. */
    public List<Frame> frames;

    /* Flag which denotes if the card complete (has results of all 10 frames). */
    public boolean isComplete;

    /* Total score of the card when complete. */
    public int totalScore;

    /**
     * Score a frame on the card (1-9 frame/roll)
     * @param ballOneScore ball one roll score
     * @param ballTwoScore ball two roll score
     */
    public abstract void score(int ballOneScore, int ballTwoScore);

    /**
     * Score the last frame on the card
     * @param ballOneScore ball one roll score
     * @param ballTwoScore ball two roll score
     * @param ballThreeScore ball three roll score
     */
    public abstract void score(int ballOneScore, int ballTwoScore, int ballThreeScore);
}
