package com.chess.engine.game;

import com.chess.engine.pieces.Piece;

public class Move {
    Piece movingPiece;
    Piece enemyPiece;
    int originRow;
    int originCol;

    int finalRow;
    int finalCol;

    public Move(Piece movingPiece, Piece enemyPiece, int finalRow, int finalCol) {
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
    public void executeMove() {
        movingPiece.movePiece(finalRow, finalCol);
    }

    /**
     * A method to undo a move
     */
    public void undo() {
        this.movingPiece.movePiece(originRow, originCol);
        if(this.enemyPiece != null) {
            this.enemyPiece.movePiece(finalRow, finalCol);
        }
    }
}
