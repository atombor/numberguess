package com.tombor.numberguess;

import com.tombor.numberguess.persistence.Game;
import com.tombor.numberguess.persistence.GameService;
import com.tombor.numberguess.persistence.GameService.PlayerStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

import static com.tombor.numberguess.GuessTheNumberController.GuessResultStatus.*;
import static com.tombor.numberguess.persistence.GameService.PlayerStatus.*;

@RestController
public class GuessTheNumberController {

    static final short MINIMUM = 1;
    static final short MAXIMUM = 100;
    private static final String GUESS_PLACEHOLDER = "GUESS_PLACEHOLDER";

    private final GameService gameService;

    public GuessTheNumberController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("guessTheNumber")
    public GuessResult guessTheNumber(long playerId, short guessedNumber) {
        final GuessResultStatus guessResultStatus;
        final PlayerStatus playerStatus = gameService.validatePlayerId(playerId);
        int guessCount = -1;
        long secondsUntilSuccess = -1;

        if (playerStatus.equals(GAME_STARTED)) {
            final short expected = gameService.getSolution(playerId);
            guessResultStatus = guessResultFromNumbers(expected, guessedNumber);
            Game updated = gameService.updateGame(playerId, CATCH.equals(guessResultStatus));
            guessCount = updated.getGuessCount();
            if (updated.getFinished() != null) {
                secondsUntilSuccess = Duration.between(updated.getStarted(), updated.getFinished()).getSeconds();
            }
        } else if (playerStatus.equals(UNKNOWN_PLAYER)) {
            guessResultStatus = USER_ERROR;
        } else if (playerStatus.equals(GAME_FINISHED)) {
            guessResultStatus = GAME_ENDED_ERROR;
        } else {
            guessResultStatus = UNKNOW_ERROR;
        }
        return new GuessResult(guessResultStatus, guessCount, guessResultStatus.messageForGuess(guessedNumber), secondsUntilSuccess);
    }

    private GuessResultStatus guessResultFromNumbers(short expected, short guessedNumber) {
        GuessResultStatus guessResultStatus;
        if (guessedNumber < MINIMUM || guessedNumber > MAXIMUM) {
            guessResultStatus = GuessResultStatus.INPUT_ERROR;
        } else if (guessedNumber < expected) {
            guessResultStatus = GuessResultStatus.LOWER;
        } else if (guessedNumber > expected) {
            guessResultStatus = GuessResultStatus.HIGHER;
        } else {
            guessResultStatus = GuessResultStatus.CATCH;
        }
        return guessResultStatus;
    }

    public enum GuessResultStatus {
        LOWER("Your guess: " + GUESS_PLACEHOLDER + ", is lower, than the expected one.", false),
        HIGHER("Your guess: " + GUESS_PLACEHOLDER + " is higher than the expected one.", false),
        CATCH("Your guess: " + GUESS_PLACEHOLDER + " is correct!", false),
        USER_ERROR("Game is not started with the provided playerId!", true),
        GAME_ENDED_ERROR("Game is already ended for the given playerId!", true),
        INPUT_ERROR("Your guess is not in the expected range: " + MINIMUM + "-" + MAXIMUM, true),
        UNKNOW_ERROR("Unknown error!", true);

        private final String messageOfResult;
        private final boolean error;

        GuessResultStatus(String messageOfResult, boolean error) {
            this.messageOfResult = messageOfResult;
            this.error = error;
        }

        String messageForGuess(short guess) {
            return messageOfResult.replaceFirst(GUESS_PLACEHOLDER, String.valueOf(guess));
        }

        public boolean isError() {
            return error;
        }
    }

}
