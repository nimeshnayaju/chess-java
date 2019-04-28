package com.chess.engine.game;

public class Player {
    public String playerName;
    public Color playerColor;
    public int playerScore;

    /**
     * Constructor for Player
     * @param playerName
     * @param playerColor
     */
    public Player(String playerName, Color playerColor) {
        this.playerName = playerName;
        this.playerColor = playerColor;
        this.playerScore = 0;
    }
}
