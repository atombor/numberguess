package com.tombor.numberguess.persistence;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue
    private long playerid;

    @Column(name = "playername", nullable = false, length = 100)
    private String playername;

    @Column(name = "solution", nullable = false)
    private short solution;

    @Column(name = "guessCount", nullable = false)
    private int guessCount;

    @Column(name = "started", nullable = false)
    private LocalDateTime started;

    @Column(name = "finished")
    private LocalDateTime finished;

    public Game() {

    }

    public Game(String playername, short solution, LocalDateTime started) {
        this.playername = playername;
        this.solution = solution;
        this.guessCount = 0;
        this.started = started;
    }

    public long getPlayerid() {
        return playerid;
    }

    public String getPlayername() {
        return playername;
    }

    public short getSolution() {
        return solution;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public void setGuessCount(int guessCount) {
        this.guessCount = guessCount;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public LocalDateTime getFinished() {
        return finished;
    }

    public void setFinished(LocalDateTime finished) {
        this.finished = finished;
    }
}
