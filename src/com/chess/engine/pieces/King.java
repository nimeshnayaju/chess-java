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
        return false;
    }

}
