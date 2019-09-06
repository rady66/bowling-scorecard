package com.rokt.exercise.bowling.scorecard.impl;

import com.rokt.exercise.bowling.scorecard.Frame;
import com.rokt.exercise.bowling.scorecard.IScoreCard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ScoreCardImpl implements IScoreCard {

    private final int pinSize;
    private final LinkedList<FrameImpl> frames = new LinkedList<>();

    public ScoreCardImpl(int pinSize) {
        if (pinSize < 1) {
            throw new IllegalArgumentException(pinSize + " should be positive.");
        }
        this.pinSize = pinSize;
    }

    public boolean isCompleted() {
        return 10 == frames.size();
    }

    public int getScore() {

        if (!isCompleted()) {
            throw new IllegalStateException("Cannot get score on incomplete game.");
        }

        return frames.stream()
                     .mapToInt(FrameImpl::getTotalScore)
                     .sum();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public void score(int ballOneScore, int ballTwoScore) {
        validateScoreArguments(ballOneScore, ballTwoScore);

        addFrame(ballOneScore, ballTwoScore);
    }

    public void scoreLast(int ballOneScore, int ballTwoScore, int ballThreeScore) {
        validateLastScoreArguments(ballOneScore, ballTwoScore, ballThreeScore);

        addFrame(ballOneScore, ballTwoScore, ballThreeScore);
    }

    // Private methods -----------------------------------------------------------------------

    private void validateNotNegativeUpToPinSize(int number, String argument) {
        if (number < 0) {
            throw new IllegalArgumentException(argument + " should be not negative.");
        }

        if (number > pinSize) {
            throw new IllegalArgumentException(argument + " should be larger than pinSize: " + pinSize);
        }
    }

    private void validateScoreArguments(int ballOneScore, int ballTwoScore) {
        validateNotNegativeUpToPinSize(ballOneScore, "ballOneScore");
        validateNotNegativeUpToPinSize(ballTwoScore, "ballTwoScore");

        if (ballOneScore + ballTwoScore > pinSize) {
            throw new IllegalArgumentException("The sum of the two ball scores should be positive number up to the pin size: " +
                    pinSize + ", for ballOneScore: " + ballOneScore + " and ballOneScore: " + ballTwoScore);
        }

        if (frames.size() == 9) {
            throw new IllegalStateException("Call scoreLast() on the last frame.");
        }

        if (isCompleted()) {
            throw new IllegalStateException("Can not score() to completed game");
        }
    }

    private void validateLastScoreArguments(int ballOneScore, int ballTwoScore, int ballThreeScore) {
        validateNotNegativeUpToPinSize(ballOneScore, "ballOneScore");
        validateNotNegativeUpToPinSize(ballTwoScore, "ballTwoScore");
        validateNotNegativeUpToPinSize(ballThreeScore, "ballThreeScore");

        if (ballOneScore + ballTwoScore + ballThreeScore > pinSize * 3) {
            throw new IllegalArgumentException("The sum of the three ball scores should be positive number up to the three time" +
                    " pin size: " + pinSize +
                    ", for ballOneScore: " + ballOneScore +
                    ", ballTwoScore: " + ballTwoScore +
                    ", ballThreeScore: " + ballThreeScore);
        }

        boolean isStrike = (pinSize == ballOneScore);
        boolean isSpare = (!isStrike && (pinSize == ballOneScore + ballTwoScore));
        if (!isStrike && !isSpare && (ballThreeScore != 0)) {
            throw new IllegalArgumentException("Can not have third ball score on last frame when there is no strike." +
                    " ballOneScore: " + ballOneScore +
                    ", ballTwoScore: " + ballTwoScore +
                    ", ballThreeScore: " + ballOneScore);
        }

        if (frames.size() != 9) {
            throw new IllegalStateException("Call scoreLast() only on the last frame.");
        }

        if (isCompleted()) {
            throw new IllegalStateException("Can not score to completed game");
        }
    }

    private void addFrame(int ballOneScore, int ballTwoScore) {
        addFrame(ballOneScore, ballTwoScore, 0);
    }

    private void addFrame(int ballOneScore, int ballTwoScore, int ballThreeScore) {

        boolean isStrike = (pinSize == ballOneScore);
        boolean isSpare = (!isStrike && (pinSize == ballOneScore + ballTwoScore));

        // add some score to the previous frame if it is strike or spare
        FrameImpl last = frames.peekLast();
        int lastIndex = frames.size() - 1;
        if (last != null) {
            if (last.isSpare) {
                last.add(ballOneScore);

            } else if (last.isStrike) {
                last.add(ballOneScore + ballTwoScore);
            }
        }

        // add some score to the frame before previous one if it is strike
        if (lastIndex - 1 >= 0) {
            FrameImpl beforeLast = frames.get(lastIndex - 1);
            if (beforeLast.isStrike) {
                beforeLast.add(ballOneScore);
            }
        }

        FrameImpl frame = new FrameImpl(ballOneScore, ballTwoScore + ballThreeScore, isStrike, isSpare);
        frames.add(frame);
    }

}
