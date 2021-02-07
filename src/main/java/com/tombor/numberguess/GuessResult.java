package com.tombor.numberguess;

public class GuessResult {

    private final GuessTheNumberController.GuessResultStatus guessResultStatus;
    private final int guessCount;
    private final String message;
    private final long secondsUntilSuccess;

    public GuessResult(GuessTheNumberController.GuessResultStatus guessResultStatus, int guessCount, String messageForGuess, long secondsUntilSuccess) {
        this.guessResultStatus = guessResultStatus;
        this.message = messageForGuess;
        this.guessCount = guessCount;
        this.secondsUntilSuccess = secondsUntilSuccess;
    }

    public GuessTheNumberController.GuessResultStatus getGuessResultStatus() {
        return guessResultStatus;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public String getMessage() {
        return message;
    }

    public long getSecondsUntilSuccess() {
        return secondsUntilSuccess;
    }
}
