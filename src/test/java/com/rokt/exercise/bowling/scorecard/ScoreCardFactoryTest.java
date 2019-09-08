package com.rokt.exercise.bowling.scorecard;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ScoreCardFactoryTest {

    @Test
    public void givenScoreCardFactory_assertCreatesTenPinScoreCard_returnFineEmpty() {
        final ScoreCard scoreCard = ScoreCardFactory.createTenPinScoreCard();

        assertNotNull("New Score card should not be null.", scoreCard);
        assertNotNull("New Score card frames should not be null.", scoreCard.frames);
        assertEquals("New Score card frames should be empty.", 0, scoreCard.frames.size());
        assertEquals("New Score card frames should not be complete.", false, scoreCard.isComplete);
        assertEquals("New Score card total score should be 0: " + scoreCard.totalScore,
                0, scoreCard.totalScore);
    }
}
