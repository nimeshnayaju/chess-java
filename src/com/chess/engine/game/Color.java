package com.chess.engine.game;

/**
 * Player colors stored as constants
 */
public enum Color {
    BLACK,
    WHITE;

    public Color opposite() {
        if(this == WHITE) {
            return BLACK;
        } else {
            return WHITE;
        }
    }

}
