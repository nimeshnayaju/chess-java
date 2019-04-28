package com.chess.engine.pieces;

import com.chess.engine.game.Board;
import com.chess.engine.game.Color;
import com.chess.engine.game.Tile;

import static com.chess.engine.game.Board.BOARD_SIZE;

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

    protected boolean isInCheck() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                Tile tileToCheck = chessBoard.board[i][j];
                if(tileToCheck.isOccupied) {
                    if(this.hasEnemyPieceAtDestination(i, j)) {
                        Piece enemyPiece = tileToCheck.occupyingPiece;
                        if(enemyPiece.isValidSpecialMove(this.row, this.col)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    protected boolean isInCheckmate() {
        if(!this.isInCheck()) {
            return false;
        }
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                Tile tileToCheck = chessBoard.board[i][j];
                if(tileToCheck.isOccupied) {
                    if(!hasEnemyPieceAtDestination(i, j)) {
                        Piece allyPiece = tileToCheck.occupyingPiece;
                        if(!this.checkmateHelper(allyPiece)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean checkmateHelper(Piece allyPiece) {
        int allyOriginRow = allyPiece.row;
        int allyOriginCol = allyPiece.col;

        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                Tile tileToCheck = chessBoard.board[i][j];
                if(hasEnemyPieceAtDestination(i, j)) {
                    if(allyPiece.isValidSpecialMove(i, j)) {
                        if(tileToCheck.isOccupied) {
                            Piece enemyPiece = tileToCheck.occupyingPiece;
                            allyPiece.movePiece(i, j);
                            if(!this.isInCheck()) {
                                allyPiece.movePiece(allyOriginRow, allyOriginCol);
                                enemyPiece.movePiece(i, j);
                                return false;
                            }
                        } else {
                            allyPiece.movePiece(i, j);
                            if(!this.isInCheck()) {
                                allyPiece.movePiece(allyOriginRow, allyOriginCol);
                                return false;
                            }
                            allyPiece.movePiece(allyOriginRow, allyOriginCol);
                        }
                    }
                }
            }
        }
        return true;
    }

}
