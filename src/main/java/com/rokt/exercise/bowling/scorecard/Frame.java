package com.rokt.exercise.bowling.scorecard;

/**
 * Frame object representation in Ten-pin Bowling Game.
 */
public class Frame {

    /* Ball one score in 1-9 frame */
    public final int ballOneScore;
    /* Ball two score in 1-9 frame */
    public final int ballTwoScore;
    /* Ball two score in the last frame */
    public final int ballThreeScore;
    /* Flag if the frame's score is Spare */
    public final boolean isSpare;
    /* Flag if the frame's score is Strike */
    public final boolean isStrike;

    int score;

    Frame(int ballOneScore,
          int ballTwoScore,
          int ballThreeScore,
          boolean isStrike,
          boolean isSpare) {

        this.ballOneScore = ballOneScore;
        this.ballTwoScore = ballTwoScore;
        this.ballThreeScore = ballThreeScore;
        this.isStrike = isStrike;
        this.isSpare = isSpare;

        this.score = ballOneScore + ballTwoScore + ballThreeScore;
    }
}
