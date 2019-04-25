package com.chess.engine.pieces;

import com.chess.engine.game.Board;
import com.chess.engine.game.Color;
import com.chess.engine.game.PieceType;

public class Queen extends Piece{
    /**
     * Constructor for Queen
     *
     * @param pieceRow
     * @param pieceCol
     * @param pieceColor
     */
    public Queen(int pieceRow, int pieceCol, Color pieceColor, Board board) {
        super(pieceRow, pieceCol, pieceColor, board);
    }

    /**
     * Queen specific implementation of abstract method
     *
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    @Override
    public boolean isValidSpecialMove(int nextRow, int nextCol) {
        int rowDisplacement = Math.abs(nextRow - this.row);
        int colDisplacement = Math.abs(nextCol - this.col);

        if((rowDisplacement == colDisplacement) || (nextRow == this.row) || (nextCol == this.col)) {
            return true;
        }
        return false;
    }

    /**
     * A method that returns the type of a Piece
     *
     * @return PieceType
     */
    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
