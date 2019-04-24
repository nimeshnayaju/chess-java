package com.chess.engine.game;

public class Player {
    public String playerName;
    public Color playerColor;

    /**
     * Constructor for Player
     * @param playerName
     * @param playerColor
     */
    public Player(String playerName, Color playerColor) {
        this.playerName = playerName;
        this.playerColor = playerColor;
    }
}
