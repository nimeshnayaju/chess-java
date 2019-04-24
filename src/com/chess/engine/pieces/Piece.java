package com.chess.engine.pieces;

import com.chess.engine.game.Color;
import com.chess.engine.game.PieceType;

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

    /**
     * Abstract method that checks if the specific Piece to move to a location specified by (nextRow, nextCol)
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    public abstract boolean isValidSpecialMove(int nextRow, int nextCol);

    /**
     * Abstract method that returns the type of a Piece
     * @return PieceType
     */
    public abstract PieceType getType();


    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public Color getPieceColor() {
        return this.color;
    }

    public void setRow(int nextRow) {
        this.row = nextRow;
    }

    public void setCol(int nextCol) {
        this.col = nextCol;
    }
}
