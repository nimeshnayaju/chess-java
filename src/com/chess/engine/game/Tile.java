package com.chess.engine.game;

import com.chess.engine.pieces.Piece;

public class Tile {
    public boolean isOccupied;
    public Color color;
    public Piece occupyingPiece;

    /**
     * Constructor for Tile
     * @param isOccupied
     * @param color
     */
    public Tile(boolean isOccupied, Color color) {
        this.isOccupied = isOccupied;
        this.color = color;
        this.occupyingPiece = null;
    }
}
