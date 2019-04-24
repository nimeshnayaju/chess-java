package com.chess.engine.game;

import com.chess.engine.pieces.Piece;

public class Board {

    protected static final int BOARD_SIZE = 8; // constant variable that sets the size of Chess board
    private Piece[][] board;

    /**
     * Constructor for Board
     */
    public Board() {
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
    }

}
