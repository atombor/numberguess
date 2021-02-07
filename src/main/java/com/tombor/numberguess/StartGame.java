package com.tombor.numberguess;

import java.time.LocalDateTime;
import java.util.UUID;

public class StartGame {

    private final long playerId;
    private final String playerName;
    private final LocalDateTime startTime;
    private final String message;

    public StartGame(long playerId, String playerName, LocalDateTime startTime, String message) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.startTime = startTime;
        this.message = message;
    }

    public long getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getMessage() {
        return message;
    }
}
