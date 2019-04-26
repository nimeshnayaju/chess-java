package com.chess.engine.pieces;

import com.chess.engine.game.Board;
import com.chess.engine.game.Color;
import com.chess.engine.game.PieceType;

public class Knight extends Piece{
    /**
     * Constructor for Knight
     *
     * @param pieceRow
     * @param pieceCol
     * @param pieceColor
     */
    public Knight(int pieceRow, int pieceCol, Color pieceColor, Board board) {
        super(pieceRow, pieceCol, pieceColor, board);
        this.pieceType = "Knight";
    }

    /**
     * Knight specific implementation of abstract method
     *
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    @Override
    public boolean isValidSpecialMove(int nextRow, int nextCol) {
        int rowDiff = Math.abs(nextRow - this.row);
        int colDiff = Math.abs(nextCol - this.col);

        if((rowDiff == 1 && colDiff == 2) || (rowDiff == 2 && colDiff == 1)) {
            return true;
        }
        return false;
    }
}
