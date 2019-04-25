package com.chess.engine.pieces;

import com.chess.engine.game.Board;
import com.chess.engine.game.Color;
import com.chess.engine.game.PieceType;

/**
 * Abstract Piece class
 */
public abstract class Piece {

    protected int row, col;
    public Board chessBoard;
    protected final Color color;

    /**
     * Constructor for Piece
     * @param pieceRow
     * @param pieceCol
     * @param pieceColor
     */
    Piece(int pieceRow, int pieceCol, Color pieceColor, Board board) {
        this.row = pieceRow;
        this.col = pieceCol;
        this.color = pieceColor;
        this.chessBoard = board;
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

    /**
     * Helper method to check if Piece is within board boundary
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    public boolean inBoardBounds(int nextRow, int nextCol) {
        if (nextRow < 0 || nextCol < 0 || nextRow >= 8 || nextCol >= 8) {
            return false;
        }
        return true;
    }

}
