package com.chess.engine.game;

import com.chess.engine.pieces.Piece;

import javax.swing.*;
import javax.swing.border.Border;

public class Tile extends JPanel{
    public boolean isOccupied;
    public Color color;
    public Piece occupyingPiece;

    /**
     * Constructor for Tile
     * @param isOccupied
     * @param color
     */
    Tile(boolean isOccupied, Color color) {
        this.isOccupied = isOccupied;
        this.color = color;
        this.occupyingPiece = null;
    }
}

