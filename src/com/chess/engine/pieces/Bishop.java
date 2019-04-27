package com.chess.engine.pieces;

import com.chess.engine.game.Board;
import com.chess.engine.game.Color;
import com.chess.engine.game.Tile;

public class Bishop extends Piece{

    /**
     * Constructor for Bishop
     *
     * @param pieceRow
     * @param pieceCol
     * @param pieceColor
     */
    public Bishop(int pieceRow, int pieceCol, Color pieceColor, Board board) {
        super(pieceRow, pieceCol, pieceColor, board);
        this.pieceType = "Bishop";
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

        if(Math.abs(rowDisplacement) == Math.abs(colDisplacement)) {
            return this.isValidLeaping(rowDisplacement, colDisplacement);
        }
        return false;
    }

}
