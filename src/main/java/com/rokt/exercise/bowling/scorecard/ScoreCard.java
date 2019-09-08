package com.rokt.exercise.bowling.scorecard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.rokt.exercise.bowling.scorecard.Validate.positiveNumber;
import static com.rokt.exercise.bowling.scorecard.Validate.validFrameArguments;
import static com.rokt.exercise.bowling.scorecard.Validate.validLastFrameArguments;

/**
 * Score Card object representation in Ten-pin Bowling Game.
 */
public class ScoreCard {

    /* List of score card frames. */
    public final List<Frame> frames;

    /* Flag which denotes if the card complete (has results of all 10 frames). */
    public boolean isComplete;

    /* Total score of the card when complete. */
    public int totalScore;

    private static final int FRAMES_MAX_SIZE = 10;

    private final int pinSize;
    private final LinkedList<Frame> internalFrameList = new LinkedList<>();

    ScoreCard(int pinSize) {
        positiveNumber(pinSize, "pinSize");

        this.pinSize = pinSize;
        this.frames = Collections.unmodifiableList(internalFrameList);
    }

    /**
     * Score a frame on the card (1-9 frame/roll)
     * @param ballOneScore ball one roll score
     * @param ballTwoScore ball two roll score
     */
    public void score(int ballOneScore, int ballTwoScore) {
        validFrameArguments(ballOneScore,
                ballTwoScore,
                pinSize,
                frames.size(),
                isComplete());

        addFrame(ballOneScore, ballTwoScore);
    }

    /**
     * Score the last frame on the card
     * @param ballOneScore ball one roll score
     * @param ballTwoScore ball two roll score
     * @param ballThreeScore ball three roll score
     */
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

            // add some score to the frame before previous one if it was a strike
            if (lastIndex - 1 >= 0) {
                Frame beforeLast = internalFrameList.get(lastIndex - 1);
                if (beforeLast.isStrike && last.isStrike) {
                    beforeLast.score += ballOneScore;
                }
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
