package com.chess.engine.game;

import com.chess.engine.pieces.*;

public class Board {

    public static final int BOARD_SIZE = 8; // constant variable that sets the size of Chess board
    public Tile[][] board;

    /**
     * Constructor for Board
     */
    public Board() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * Method to populate board with black and white tiles
     */
    protected void populateBoardWithTiles() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                if(i % 2 == 0) {
                    if(j % 2 == 0) {
                        board[i][j] = new Tile(false, Color.BLACK);
                    } else {
                        board[i][j] = new Tile(false, Color.WHITE);
                    }
                } else {
                    if(j % 2 == 0) {
                        board[i][j] = new Tile(false, Color.WHITE);
                    } else {
                        board[i][j] = new Tile(false, Color.WHITE);
                    }
                }
            }
        }
    }

    /**
     * Method to populate board with standard pieces
     */
    public void populateBoardWithPieces() {
        setUpKnights();
        setUpBishops();
        setUpPawns();
        setUpRooks();
        setUpQueens();
        setUpKings();
    }

    /**
     * Method to set up Knights into their initial position
     */
    public void setUpKnights() {
        Knight whiteKnightLeft = new Knight(7, 1, Color.WHITE, this);
        Knight whiteKnightRight = new Knight(7, 6, Color.WHITE, this);

        Knight blackKnightLeft = new Knight(0, 1, Color.BLACK, this);
        Knight blackKnightRight = new Knight(0, 6, Color.BLACK, this);

        this.board[7][1].isOccupied = true;
        this.board[7][6].isOccupied = true;
        this.board[7][1].occupyingPiece = whiteKnightLeft;
        this.board[7][6].occupyingPiece = whiteKnightRight;

        this.board[0][1].isOccupied = true;
        this.board[0][6].isOccupied = true;
        this.board[7][1].occupyingPiece = blackKnightLeft;
        this.board[7][6].occupyingPiece = blackKnightRight;
    }

    /**
     * Method to set up Rooks into their initial position
     */
    public void setUpRooks() {
        Rook whiteRookLeft = new Rook(7, 2, Color.WHITE, this);
        Rook whiteRookRight = new Rook(7, 5, Color.WHITE, this);

        Rook blackRookLeft = new Rook(0, 2, Color.BLACK, this);
        Rook blackRookRight = new Rook(0, 5, Color.BLACK, this);

        this.board[7][2].isOccupied = true;
        this.board[7][5].isOccupied = true;
        this.board[7][2].occupyingPiece = whiteRookLeft;
        this.board[7][5].occupyingPiece = whiteRookRight;

        this.board[0][1].isOccupied = true;
        this.board[0][6].isOccupied = true;
        this.board[7][1].occupyingPiece = blackRookLeft;
        this.board[7][6].occupyingPiece = blackRookRight;
    }

    /**
     * Method to set up Bishops into their initial position
     */
    public void setUpBishops() {
        Bishop whiteBishopLeft = new Bishop(7, 0, Color.WHITE, this);
        Bishop whiteBishopRight = new Bishop(7, 7, Color.WHITE, this);

        Bishop blackBishopLeft = new Bishop(0, 0, Color.BLACK, this);
        Bishop blackBishopRight = new Bishop(0, 7, Color.BLACK, this);

        this.board[7][0].isOccupied = true;
        this.board[7][7].isOccupied = true;
        this.board[7][0].occupyingPiece = whiteBishopLeft;
        this.board[7][7].occupyingPiece = whiteBishopRight;

        this.board[0][0].isOccupied = true;
        this.board[0][7].isOccupied = true;
        this.board[7][0].occupyingPiece = blackBishopLeft;
        this.board[7][7].occupyingPiece = blackBishopRight;
    }

    /**
     * Method to set up Pawns into their initial position
     */
    public void setUpPawns() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            Pawn blackPawn = new Pawn(1, i, Color.BLACK, this);
            Pawn whitePawn = new Pawn(6, i, Color.WHITE, this);

            this.board[1][i].isOccupied = true;
            this.board[6][i].isOccupied = true;

            this.board[1][i].occupyingPiece = blackPawn;
            this.board[6][i].occupyingPiece = whitePawn;
        }
    }

    /**
     * Method to set up Queens into their initial position
     */
    public void setUpQueens() {
        Queen whiteQueen = new Queen(7, 3, Color.WHITE, this);

        Queen blackQueen = new Queen(0, 3, Color.BLACK, this);

        this.board[7][3].isOccupied = true;
        this.board[7][3].occupyingPiece = whiteQueen;

        this.board[0][3].isOccupied = true;
        this.board[7][3].occupyingPiece = blackQueen;
    }

    /**
     * Method to set up Kings into their initial position
     */
    public void setUpKings() {
        King whiteKing = new King(7, 4, Color.WHITE, this);

        King blackKing = new King(0, 4, Color.BLACK, this);

        this.board[7][4].isOccupied = true;
        this.board[7][4].occupyingPiece = whiteKing;

        this.board[0][4].isOccupied = true;
        this.board[7][4].occupyingPiece = blackKing;
    }
}
