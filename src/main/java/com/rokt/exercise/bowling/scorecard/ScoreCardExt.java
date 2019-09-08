package com.rokt.exercise.bowling.scorecard;

import java.util.Collections;
import java.util.LinkedList;

import static com.rokt.exercise.bowling.scorecard.Validate.*;

class ScoreCardExt extends ScoreCard {

    private static final int FRAMES_MAX_SIZE = 10;

    private final int pinSize;
    private final LinkedList<Frame> internalFrameList = new LinkedList<>();

    ScoreCardExt(int pinSize) {
        positiveNumber(pinSize, "pinSize");

        this.pinSize = pinSize;
        this.frames = Collections.unmodifiableList(internalFrameList);
    }

    public void score(int ballOneScore, int ballTwoScore) {
        validFrameArguments(ballOneScore,
                ballTwoScore,
                pinSize,
                frames.size(),
                isComplete());

        addFrame(ballOneScore, ballTwoScore);
    }

    public void score(int ballOneScore, int ballTwoScore, int ballThreeScore) {
        validLastFrameArguments(ballOneScore,
                ballTwoScore,
                ballThreeScore,
                pinSize,
                frames.size(),
                isComplete());

        addFrame(ballOneScore, ballTwoScore, ballThreeScore);
    }

    // Private methods -----------------------------------------------------------------------

    private int calculateTotalScore() {
        return internalFrameList.stream()
                           .mapToInt(frame -> frame.score)
                           .sum();
    }

    private boolean isComplete() {
        return FRAMES_MAX_SIZE == frames.size();
    }

    private void addFrame(int ballOneScore, int ballTwoScore) {
        addFrame(ballOneScore, ballTwoScore, 0);
    }

    private void addFrame(int ballOneScore, int ballTwoScore, int ballThreeScore) {

        boolean isStrike = (pinSize == ballOneScore);
        boolean isSpare = (!isStrike && (pinSize == ballOneScore + ballTwoScore));

        // add some score to the previous frame if it is strike or spare
        Frame last = internalFrameList.peekLast();
        int lastIndex = internalFrameList.size() - 1;
        if (last != null) {
            if (last.isSpare) {
                last.score += ballOneScore;

            } else if (last.isStrike) {
                last.score += (ballOneScore + ballTwoScore);
            }
        }

        // add some score to the frame before previous one if it is strike
        if (lastIndex - 1 >= 0) {
            Frame beforeLast = internalFrameList.get(lastIndex - 1);
            if (beforeLast.isStrike) {
                beforeLast.score += ballOneScore;
            }
        }

        //add frame
        Frame frame = new Frame(ballOneScore, ballTwoScore, ballThreeScore, isStrike, isSpare);
        internalFrameList.add(frame);

        //update the score and completion
        totalScore = calculateTotalScore();
        isComplete = isComplete();
    }

}