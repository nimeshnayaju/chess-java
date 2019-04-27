package com.chess.engine.pieces;

import com.chess.engine.game.Board;
import com.chess.engine.game.Color;

public class King extends Piece{
    /**
     * Constructor for Knight
     *
     * @param pieceRow
     * @param pieceCol
     * @param pieceColor
     */
    public King(int pieceRow, int pieceCol, Color pieceColor, Board board) {
        super(pieceRow, pieceCol, pieceColor, board);
        this.pieceType = "King";
    }

    /**
     * King specific implementation of abstract method
     *
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    @Override
    public boolean isValidSpecialMove(int nextRow, int nextCol) {
        int rowDisplacement = Math.abs(this.row - nextRow);
        int colDisplacement = Math.abs(this.col - nextCol);

        if((rowDisplacement == 1 && colDisplacement == 1) || (rowDisplacement == 1 && colDisplacement == 0) || (rowDisplacement == 0 && colDisplacement == 1)) {
            return true;
        }
        return false;
    }

}
