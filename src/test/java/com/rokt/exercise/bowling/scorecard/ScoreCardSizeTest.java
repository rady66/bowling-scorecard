package com.rokt.exercise.bowling.scorecard;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class ScoreCardSizeTest {

    @Test
    public void givenScoreCard_scoreStrikesOnly_assertMaxResult() {

        IScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        IntStream.range(0, 9)
                .forEach(i -> scoreCard.score(10, 0));

        scoreCard.scoreLast(10, 10, 10);

        assertEquals("When scoring only strikes on ten pined bolwing we should have max score of 300",
                300, scoreCard.getScore());

    }

    @Test
    public void givenScoreCard_scoreSparesOnly_assertResult() {

        IScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        IntStream.range(0, 9)
                .forEach(i -> scoreCard.score(5, 5));

        scoreCard.scoreLast(5, 5, 5);

        assertEquals("When scoring only spares on ten pined bolwing we should have max score of 150",
                150, scoreCard.getScore());

    }
}
