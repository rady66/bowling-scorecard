package com.rokt.exercise.bowling.scorecard;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FrameTest {

    @Test
    public void givenStrikeScoreFrame_assertPropertiesFine() {
        final Frame frame = new Frame(10, 9, 0, true, false);

        assertEquals("Expected 'ballOneScore' is 10.", 10, frame.ballOneScore);
        assertEquals("Expected 'ballOneScore' is 9.", 9, frame.ballTwoScore);
        assertEquals("Expected 'ballThreeScore' is 0.", 0, frame.ballThreeScore);
        assertEquals("Expected 'isStrike' is true.", true, frame.isStrike);
        assertEquals("Expected 'isSpare' is false.", false, frame.isSpare);
    }

    @Test
    public void givenSpareScoreFrame_assertPropertiesFine() {
        final Frame frame = new Frame(2, 8, 0, false, true);

        assertEquals("Expected 'ballOneScore' is 2.", 2, frame.ballOneScore);
        assertEquals("Expected 'ballOneScore' is 8.", 8, frame.ballTwoScore);
        assertEquals("Expected 'ballThreeScore' is 0.", 0, frame.ballThreeScore);
        assertEquals("Expected 'isStrike' is false.", false, frame.isStrike);
        assertEquals("Expected 'isSpare' is true.", true, frame.isSpare);
    }
}
