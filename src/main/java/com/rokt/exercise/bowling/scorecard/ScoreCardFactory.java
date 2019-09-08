package com.rokt.exercise.bowling.scorecard;

/**
 * Factory class for Ten-pin Bowling Score Card.
 */
public class ScoreCardFactory {

    private ScoreCardFactory() {}

    /**
     * Creates TenPin Score Card object.
     * @return TenPin Score Card object.
     */
    public static ScoreCard createTenPinScoreCard() {
        return new ScoreCard(10);
    }
}
