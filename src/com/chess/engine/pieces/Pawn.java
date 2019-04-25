package com.chess.engine.pieces;

import com.chess.engine.game.Board;
import com.chess.engine.game.Color;
import com.chess.engine.game.PieceType;

public class Pawn extends Piece{
    /**
     * Constructor for Pawn
     *
     * @param pieceRow
     * @param pieceCol
     * @param pieceColor
     */
    Pawn(int pieceRow, int pieceCol, Color pieceColor, Board board) {
        super(pieceRow, pieceCol, pieceColor, board);
    }

    /**
     * Pawn specific implementation of abstract method
     *
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    @Override
    public boolean isValidSpecialMove(int nextRow, int nextCol) {
        return false;
    }

    /**
     * A method that returns the type of a Piece
     *
     * @return PieceType
     */
    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
