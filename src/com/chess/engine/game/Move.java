package com.chess.engine.game;

import com.chess.engine.pieces.Piece;

class Move {
    private Piece movingPiece;
    private Piece enemyPiece;
    private int originRow;
    private int originCol;

    private int finalRow;
    private int finalCol;

    Move(Piece movingPiece, Piece enemyPiece, int finalRow, int finalCol) {
        this.movingPiece = movingPiece;
        this.enemyPiece = enemyPiece;
        this.originRow = movingPiece.row;
        this.originCol = movingPiece.col;

        this.finalRow = finalRow;
        this.finalCol = finalCol;
    }

    /**
     * A method to execute a move on a Piece
     */
    void executeMove() {
        movingPiece.movePiece(finalRow, finalCol);
    }

    /**
     * A method to undo a move
     */
    void undo() {
        this.movingPiece.movePiece(originRow, originCol);
        if(this.enemyPiece != null) {
            this.enemyPiece.movePiece(finalRow, finalCol);
        }
    }
}
