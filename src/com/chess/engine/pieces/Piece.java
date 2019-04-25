package com.chess.engine.pieces;

import com.chess.engine.game.Board;
import com.chess.engine.game.Color;
import com.chess.engine.game.PieceType;
import com.chess.engine.game.Tile;

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
     * A method that checks if a Piece can be moved to a location
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    public boolean canMove(int nextRow, int nextCol) {
        if(inBoardBounds(nextRow, nextCol) && isValidSpecialMove(nextRow, nextCol) && hasEnemyPieceAtDestination(nextRow, nextCol) && !moveWouldCauseCheck(nextRow, nextCol)) {
            return true;
        }
        return false;
    }

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

    /**
     * Helper method to check if the destination location is occupied by enemy piece
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    public boolean hasEnemyPieceAtDestination(int nextRow, int nextCol) {
        Tile destinationTile = chessBoard.board[nextRow][nextCol];
        if(destinationTile.isOccupied) {
            if(this.color.equals(destinationTile.color)) {
                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * Helper method to check if the move would put the player's king into check
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    public boolean moveWouldCauseCheck(int nextRow, int nextCol) {
        return false;
    }

    /**
     * Helper method to move a Piece into the given location
     * @param nextRow
     * @param nextCol
     */
    public void setPieceToLocation(int nextRow, int nextCol) {
        Tile currentTile = chessBoard.board[this.row][this.col];
        Tile destinationTile = chessBoard.board[nextRow][nextCol];

        currentTile.isOccupied = false;
        currentTile.occupyingPiece = null;

        destinationTile.isOccupied = true;
        destinationTile.occupyingPiece = this;

        this.row = nextRow;
        this.col = nextCol;
    }

}
