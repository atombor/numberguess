package com.tombor.numberguess;

import com.tombor.numberguess.persistence.Game;
import com.tombor.numberguess.persistence.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

import static com.tombor.numberguess.GuessTheNumberController.MAXIMUM;
import static com.tombor.numberguess.GuessTheNumberController.MINIMUM;

@RestController
public class StartGameController {

    private static final String PLAYER_NAME = "PLAYER_NAME";
    private static final String messageTemplate = "Welcome to the Number Guess Game, " + PLAYER_NAME + "!" +
            "This game is about to guess a number between " + MINIMUM + " and " + MAXIMUM +
            "Your number is ready for guessing! Good Luck!";

    final GameService gameService;

    public StartGameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("startGame")
    public StartGame startGame(String name) {
        final short solution = (short) new Random().nextInt(100);
        final LocalDateTime startTime = LocalDateTime.now();

        Game game = gameService.saveNewGame(name, solution, startTime);

        return new StartGame(game.getPlayerid(), name, startTime, messageTemplate.replaceFirst(PLAYER_NAME, name));
    }
}