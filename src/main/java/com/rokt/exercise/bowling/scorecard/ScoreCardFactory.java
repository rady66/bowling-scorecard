package com.rokt.exercise.bowling.scorecard;

import com.rokt.exercise.bowling.scorecard.impl.ScoreCardImpl;

public class ScoreCardFactory {

    private ScoreCardFactory() {
    }

    public static IScoreCard createTenPinScoreCard() {
        return new ScoreCardImpl(10);
    }
}
