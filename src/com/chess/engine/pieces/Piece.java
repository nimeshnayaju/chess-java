package com.chess.engine.pieces;

import com.chess.engine.game.Color;

/**
 * Abstract Piece class
 */
public abstract class Piece {

    protected int row, col;
    protected final Color color;

    /**
     * Constructor for Piece
     * @param pieceRow
     * @param pieceCol
     * @param pieceColor
     */
    Piece(int pieceRow, int pieceCol, Color pieceColor) {
        this.row = pieceRow;
        this.col = pieceCol;
        this.color = pieceColor;
    }

}
