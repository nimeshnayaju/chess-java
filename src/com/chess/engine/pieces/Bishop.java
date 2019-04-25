package com.chess.engine.pieces;

import com.chess.engine.game.Board;
import com.chess.engine.game.Color;
import com.chess.engine.game.PieceType;

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
        int rowDisplacement = Math.abs(nextRow - this.row);
        int colDisplacement = Math.abs(nextCol - this.col);

        if(rowDisplacement == colDisplacement) {
            int steps = Math.abs(rowDisplacement);
            int rowDirection = rowDisplacement/steps;
            int colDirection = colDisplacement/steps;

            for(int i = 1; i < steps; i++) {
                if(this.chessBoard.board[this.row + i*rowDirection][colDirection + i*colDirection] != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Abstract method that returns the type of a Piece
     *
     * @return PieceType
     */
    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
