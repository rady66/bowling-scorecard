package com.rokt.exercise.bowling.scorecard;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.stream.IntStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ScoreCardTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void givenScoreCard_scoreZerosOnly_assertMinResult() {

        final ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        IntStream.range(0, 9)
                .forEach(i -> scoreCard.score(0, 0));

        scoreCard.score(0, 0, 0);

        // asserts
        assertTrue("Score card should be complete.", scoreCard.isComplete);
        assertEquals("Frame size should be 10.",10, scoreCard.frames.size());
        assertEquals("When scoring only strikes on ten pined bolwing we should have max score of 300",
                0, scoreCard.totalScore);

        scoreCard.frames.subList(0, 10)
                .forEach(f -> {
                    assertEquals("Frame ballOneScore should be 0.",0, f.ballOneScore);
                    assertEquals("Frame ballTwoScore should be 0.",0, f.ballTwoScore);
                    assertFalse("Frame isStrike should be true.", f.isStrike);
                    assertFalse("Frame isSpare should be false.", f.isSpare);
                });
    }

    @Test
    public void givenScoreCard_scoreStrikesOnly_assertMaxResult() {

        final ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        IntStream.range(0, 9)
                 .forEach(i -> scoreCard.score(10, 0));

        scoreCard.score(10, 10, 10);

        // asserts
        assertTrue("Score card should be complete.", scoreCard.isComplete);
        assertEquals("Frame size should be 10.",10, scoreCard.frames.size());
        assertEquals("When scoring only strikes on ten pined bolwing we should have max score of 300",
                300, scoreCard.totalScore);

        scoreCard.frames.subList(0, 9)
                 .forEach(f -> {
                     assertEquals("Frame ballOneScore should be 10.",10, f.ballOneScore);
                     assertEquals("Frame ballTwoScore should be 0.",0, f.ballTwoScore);
                     assertTrue("Frame isStrike should be true.", f.isStrike);
                     assertFalse("Frame isSpare should be false.", f.isSpare);
                 });
    }

    @Test
    public void givenScoreCard_scoreSparesOnly_assertResult() {

        ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        IntStream.range(0, 9)
                .forEach(i -> scoreCard.score(5, 5));

        scoreCard.score(5, 5, 5);

        // asserts
        assertTrue("Score card should be complete.", scoreCard.isComplete);
        assertEquals("Frame size should be 10.",10, scoreCard.frames.size());
        assertEquals("When scoring only spares on ten pined bolwing we should have max score of 150",
                150, scoreCard.totalScore);

        scoreCard.frames
                .forEach(f -> {
                    assertEquals("Frame ballOneScore should be 10.",5, f.ballOneScore);
                    assertEquals("Frame ballTwoScore should be 0.",5, f.ballTwoScore);
                    assertFalse("Frame isStrike should be false.", f.isStrike);
                    assertTrue("Frame isSpare should be true.", f.isSpare);
                });
    }

    @Test
    public void givenSomeScoreCard1_scoreAssertResult() {

        ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        scoreCard.score(10, 0);
        scoreCard.score(7, 3);
        scoreCard.score(7, 2);
        scoreCard.score(9, 1);
        scoreCard.score(10, 0);
        scoreCard.score(10, 0);
        scoreCard.score(10, 0);
        scoreCard.score(2, 3);
        scoreCard.score(6, 4);
        scoreCard.score(7, 3, 3);

        // asserts
        assertTrue("Score card should be complete.", scoreCard.isComplete);
        assertEquals("Frame size should be 10.",10, scoreCard.frames.size());
        assertEquals("Result should be 168",168, scoreCard.totalScore);
    }

    @Test
    public void givenSomeScoreCard2_scoreAssertResult() {

        ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        scoreCard.score(8, 0);
        scoreCard.score(5, 5);
        scoreCard.score(3, 5);
        scoreCard.score(8, 1);
        scoreCard.score(7, 1);
        scoreCard.score(10, 0);
        scoreCard.score(9, 1);
        scoreCard.score(10, 0);
        scoreCard.score(10, 0);
        scoreCard.score(8, 2, 6);

        // asserts
        assertTrue("Score card should be complete.", scoreCard.isComplete);
        assertEquals("Frame size should be 10.",10, scoreCard.frames.size());
        assertEquals("Result should be 150",150, scoreCard.totalScore);
    }

    @Test
    public void givenSomeScoreCard3_scoreAssertResult() {

        ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        scoreCard.score(10, 0);
        scoreCard.score(9, 1);
        scoreCard.score(5, 5);
        scoreCard.score(7, 2);
        scoreCard.score(10, 0);
        scoreCard.score(10, 0);
        scoreCard.score(10, 0);
        scoreCard.score(9, 0);
        scoreCard.score(8, 2);
        scoreCard.score(9, 1, 10);

        // asserts
        assertTrue("Score card should be complete.", scoreCard.isComplete);
        assertEquals("Frame size should be 10.",10, scoreCard.frames.size());
        assertEquals("Result should be 187",187, scoreCard.totalScore);
    }

    @Test
    public void createZeroPin_throwIllegalArguemtnException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("pinSize must be positive number: 0");

        new ScoreCard(0);
    }

    @Test
    public void createNegativePin_throwIllegalArguemtnException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("pinSize must be positive number: -1");

        new ScoreCard(-1);
    }

    @Test
    public void givenScoreCard_scoreNegativeBallOne_throwIllegalArguemtnException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ballOneScore must not be negetive: -2");

        ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        scoreCard.score(-2, 0);
    }

    @Test
    public void givenScoreCard_scoreNegativeBallTwo_throwIllegalArguemtnException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ballTwoScore must not be negetive: -1");

        ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        scoreCard.score(0, -1);
    }

    @Test
    public void givenScoreCard_scoreOutOfPinSizeRangeBallOne_throwIllegalArguemtnException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ballOneScore must not be larger than pinSize(10): 11");

        ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        scoreCard.score(11, 0);
    }

    @Test
    public void givenScoreCard_scoreOutOfPinSizeRangeBallTwo_throwIllegalArguemtnException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("ballTwoScore must not be larger than pinSize(10): 11");

        ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        scoreCard.score(0, 11);
    }

    @Test
    public void givenScoreCard_scoreOutOfPinSizeRange_throwIllegalArguemtnException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("The sum of the two ball scores should be positive number up to " +
                "the pin size: 10, for ballOneScore: 5 and ballOneScore: 7");

        ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        scoreCard.score(5, 7);
    }

    @Test
    public void givenScoreCard_scoreOnCompletedGame_throwIllegalArguemtnException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Can not score() to completed game.");

        ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        IntStream.range(0, 9)
                .forEach(i -> scoreCard.score(5, 2));

        scoreCard.score(5, 3, 0);

        scoreCard.score(5, 2, 0);
    }

    @Test
    public void givenScoreCard_scoreOnCompletedGame2_throwIllegalArguemtnException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Can not score() to completed game.");

        ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        IntStream.range(0, 9)
                .forEach(i -> scoreCard.score(5, 2));

        scoreCard.score(5, 3, 0);

        scoreCard.score(5, 2);
    }

    @Test
    public void givenScoreCard_scoreWithTwoBallsOnLastFrame_throwIllegalArguemtnException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Call 'score(int ballOneScore, int ballTwoScore)' on the last frame.");

        ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        IntStream.range(0, 9)
                .forEach(i -> scoreCard.score(5, 2));

        scoreCard.score(5, 3);
    }

    @Test
    public void givenScoreCard_scoreWithThreeBallsOnFirstFrame_throwIllegalArguemtnException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Call 'score(int ballOneScore, int ballTwoScore, int ballThreeScore)' " +
                "only on the last frame.");

        ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        scoreCard.score(5, 5, 5);
    }

    @Test
    public void givenScoreCard_scoreWithThreeBallsOnLastFrameNoStrikeOrSpare_throwIllegalArguemtnException() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Can not have third ball score on last frame when there is no strike or spare. " +
                "ballOneScore: 9, " +
                "ballTwoScore: 0, " +
                "ballThreeScore: 9");

        final ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        IntStream.range(0, 9)
                .forEach(i -> scoreCard.score(10, 0));

        scoreCard.score(9, 0, 9);
    }
}
