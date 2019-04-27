package com.chess.engine.pieces;

import com.chess.engine.game.Board;
import com.chess.engine.game.Color;
import com.chess.engine.game.Tile;

public class Pawn extends Piece{
    /**
     * Constructor for Pawn
     *
     * @param pieceRow
     * @param pieceCol
     * @param pieceColor
     */
    public Pawn(int pieceRow, int pieceCol, Color pieceColor, Board board) {
        super(pieceRow, pieceCol, pieceColor, board);
        this.pieceType = "Pawn";
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
        int rowDisplacement = nextRow - this.row;
        int colDisplacement = nextCol - this.col;

        if(validPawnMove(rowDisplacement, colDisplacement)) {
            Tile destinationTile = chessBoard.board[nextRow][nextCol];
            if(rowDisplacement == 0) {
                if(destinationTile.isOccupied) {
                    return false;
                }
                return true;
            } else {
                if(!destinationTile.isOccupied) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private boolean validPawnMove(int rowDisplacement, int colDisplacement) {
        if((this.col == 6 && this.color.equals(Color.BLACK)) || (this.col == 1 && this.color.equals(Color.WHITE))) {
            return firstPawnMove(rowDisplacement, colDisplacement);
        } else {
            return regularMoveOrCapture(rowDisplacement, colDisplacement);
        }
    }

    /**
     * A helper method to check if the pawn can perform a regular move or capture
     * @param rowDisplacement
     * @param colDisplacement
     * @return
     */
    public boolean regularMoveOrCapture(int rowDisplacement, int colDisplacement) {
        if(this.color.equals(Color.WHITE)) {
            if(colDisplacement == 1 && (rowDisplacement == 0 || (Math.abs(rowDisplacement) == 1))) {
                return true;
            }
            return false;
        } else {
            if(colDisplacement == -1 && (rowDisplacement == 0 || Math.abs(rowDisplacement) == 1)) {
                return true;
            }
            return false;
        }
    }

    /**
     * A helper method to check if the pawn can perform first move or first move capture
     * @param rowDisplacement
     * @param colDisplacement
     * @return
     */
    private boolean firstPawnMove(int rowDisplacement, int colDisplacement) {
        if(this.color.equals(Color.WHITE)) {
            if((colDisplacement == 1 || colDisplacement == 2) && (rowDisplacement == 0 || Math.abs(rowDisplacement) == 1)) {
                return true;
            }
            return false;
        } else {
            if((colDisplacement == -1 || colDisplacement == -2) && (rowDisplacement == 0 || Math.abs(rowDisplacement) == 1)) {
                return true;
            }
            return false;
        }
    }

}
