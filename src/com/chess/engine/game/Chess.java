package com.chess.engine.game;

public class Chess {

    public static void main(String[] args) {
        Board chessBoard = new Board();
        chessBoard.populateBoardWithTiles();
        chessBoard.populateBoardWithPieces();
    }

}
