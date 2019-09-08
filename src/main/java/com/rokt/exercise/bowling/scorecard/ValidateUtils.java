package com.rokt.exercise.bowling.scorecard;

/**
 * Validation utility.
 */
final class ValidateUtils {

    private ValidateUtils() {}

    static void positiveNumber(int number, String argument) {
        if (number < 1) {
            throw new IllegalArgumentException(argument + " must be positive number: " + number);
        }
    }

    static void notNegativeAndUpToPinSize(int number,
                                          int pinSize,
                                          String argument) {
        if (number < 0) {
            throw new IllegalArgumentException(argument + " must not be negetive: " + number);
        }

        if (number > pinSize) {
            throw new IllegalArgumentException(argument + " must not be larger than pinSize(" + pinSize + "): " + number);
        }
    }

    static void validFrameArguments(int ballOneScore,
                                    int ballTwoScore,
                                    int pinSize,
                                    int frameSize,
                                    boolean isCompleted) {
        notNegativeAndUpToPinSize(ballOneScore, pinSize, "ballOneScore");
        notNegativeAndUpToPinSize(ballTwoScore, pinSize, "ballTwoScore");

        if (ballOneScore + ballTwoScore > pinSize) {
            throw new IllegalArgumentException("The sum of the two ball scores should be positive number up to the pin size: " +
                    pinSize + ", for ballOneScore: " + ballOneScore + " and ballOneScore: " + ballTwoScore);
        }

        if (isCompleted) {
            throw new IllegalStateException("Can not score() to completed game.");
        }

        if (frameSize == 9) {
            throw new IllegalStateException("Call 'score(int ballOneScore, int ballTwoScore)' on the last frame.");
        }
    }

    static void validLastFrameArguments(int ballOneScore,
                                        int ballTwoScore,
                                        int ballThreeScore,
                                        int pinSize,
                                        int frameSize,
                                        boolean isCompleted) {
        notNegativeAndUpToPinSize(ballOneScore, pinSize, "ballOneScore");
        notNegativeAndUpToPinSize(ballTwoScore, pinSize,"ballTwoScore");
        notNegativeAndUpToPinSize(ballThreeScore, pinSize,"ballThreeScore");

        boolean isStrike = (pinSize == ballOneScore);
        boolean isSpare = (!isStrike && (pinSize == ballOneScore + ballTwoScore));
        if (!isStrike && !isSpare && (ballThreeScore != 0)) {
            throw new IllegalArgumentException("Can not have third ball score on last frame when there is no strike or spare. " +
                    "ballOneScore: " + ballOneScore +
                    ", ballTwoScore: " + ballTwoScore +
                    ", ballThreeScore: " + ballOneScore);
        }

        if (isCompleted) {
            throw new IllegalStateException("Can not score() to completed game.");
        }

        if (frameSize != 9) {
            throw new IllegalStateException("Call 'score(int ballOneScore, int ballTwoScore, int ballThreeScore)' only on the last frame.");
        }
    }
}
