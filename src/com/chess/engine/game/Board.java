package com.chess.engine.game;

import com.chess.engine.pieces.Piece;

public class Board {

    protected static final int BOARD_SIZE = 8; // constant variable that sets the size of Chess board
    public Tile[][] board;

    /**
     * Constructor for Board
     */
    public Board() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * Method to populate board with tiles
     */
    protected void populateBoardWithTiles() {

    }

    /**
     * Method to populate board with standard pieces
     */
    public void populateBoardWithPieces() {

    }

}
