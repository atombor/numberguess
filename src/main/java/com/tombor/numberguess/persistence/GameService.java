package com.tombor.numberguess.persistence;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GameService {

    final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game updateGame(long playerId, boolean isCaught) {
        Optional<Game> gameById = gameRepository.findById(playerId);
        if (gameById.isPresent()) {
            Game game = gameById.get();
            game.setGuessCount(game.getGuessCount() + 1);
            if (isCaught) {
                game.setFinished(LocalDateTime.now());
            }
            return gameRepository.save(game);
        } else {
            throw new RuntimeException("Game not found, with player id: " + playerId);
        }
    }

    public Iterable<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public short getSolution(long playerId) {
        Optional<Game> gameByID = gameRepository.findById(playerId);
        if (gameByID.isPresent()) {
            return gameByID.get().getSolution();
        } else {
            throw new RuntimeException("Data not found by ID!");
        }
    }

    public Game saveNewGame(String name, short solution, LocalDateTime startTime) {
        Game newGame = new Game(name, solution, startTime);
        return gameRepository.save(newGame);
    }

    public PlayerStatus validatePlayerId(long playerId) {
        Optional<Game> gameById = gameRepository.findById(playerId);
        if (gameById.isPresent()) {
            if (gameById.get().getFinished() == null) {
                return PlayerStatus.GAME_STARTED;
            } else {
                return PlayerStatus.GAME_FINISHED;
            }
        } else {
            return PlayerStatus.UNKNOWN_PLAYER;
        }
    }

    public enum PlayerStatus {GAME_STARTED, GAME_FINISHED, UNKNOWN_PLAYER}
}
