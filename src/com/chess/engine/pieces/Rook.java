package com.chess.engine.pieces;

import com.chess.engine.game.Board;
import com.chess.engine.game.Color;

public class Rook extends Piece{

    /**
     * Constructor for Rook
     *
     * @param pieceRow
     * @param pieceCol
     * @param pieceColor
     */
    public Rook(int pieceRow, int pieceCol, Color pieceColor, Board board) {
        super(pieceRow, pieceCol, pieceColor, board);
        this.pieceType = "Rook";
    }

    /**
     * Bishop specific implementation of abstract method
     *
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    @Override
    public boolean isValidSpecialMove(int nextRow, int nextCol) {
        int rowDisplacement = nextRow - this.row;
        int colDisplacement = nextCol - this.col;
        if((rowDisplacement == 0) || (colDisplacement == 0)) {
            return this.isValidLeaping(rowDisplacement, colDisplacement);
        }
        return false;
    }

}
