package com.chess.engine.game;

import com.chess.engine.pieces.*;

public class Board {

    public static final int BOARD_SIZE = 8; // constant variable that sets the size of Chess board
    public Tile[][] board;

    public King whiteKing;
    public King blackKing;

    /**
     * Constructor for Board
     */
    public Board() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * Method to populate board with black and white tiles
     */
    void populateBoardWithTiles() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0)
                        board[i][j] = new Tile(false, Color.BLACK);
                    else
                        board[i][j] = new Tile(false, Color.WHITE);
                }
                else {
                    if (j % 2 == 0)
                        board[i][j] = new Tile(false, Color.WHITE);
                    else
                        board[i][j] = new Tile(false, Color.BLACK);
                }
            }
        }
    }

    /**
     * Method to populate board with standard pieces
     */
    void populateBoardWithPieces() {
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
    private void setUpKnights() {
        Knight whiteKnightLeft = new Knight(1, 0, Color.WHITE, this);
        Knight whiteKnightRight = new Knight(6, 0, Color.WHITE, this);

        Knight blackKnightLeft = new Knight(1, 7, Color.BLACK, this);
        Knight blackKnightRight = new Knight(6, 7, Color.BLACK, this);

        this.board[1][0].isOccupied = true;
        this.board[6][0].isOccupied = true;
        this.board[1][0].occupyingPiece = whiteKnightLeft;
        this.board[6][0].occupyingPiece = whiteKnightRight;

        this.board[1][7].isOccupied = true;
        this.board[6][7].isOccupied = true;
        this.board[1][7].occupyingPiece = blackKnightLeft;
        this.board[6][7].occupyingPiece = blackKnightRight;
    }

    /**
     * Method to set up Rooks into their initial position
     */
    private void setUpBishops() {
        Bishop whiteBishopLeft = new Bishop(2, 0, Color.WHITE, this);
        Bishop whiteBishopRight = new Bishop(5, 0, Color.WHITE, this);

        Bishop blackBishopLeft = new Bishop(2, 7, Color.BLACK, this);
        Bishop blackBishopRight = new Bishop(5, 7, Color.BLACK, this);

        this.board[2][0].isOccupied = true;
        this.board[5][0].isOccupied = true;
        this.board[2][0].occupyingPiece = whiteBishopLeft;
        this.board[5][0].occupyingPiece = whiteBishopRight;

        this.board[2][7].isOccupied = true;
        this.board[5][7].isOccupied = true;
        this.board[2][7].occupyingPiece = blackBishopLeft;
        this.board[5][7].occupyingPiece = blackBishopRight;
    }

    /**
     * Method to set up Bishops into their initial position
     */
    private void setUpRooks() {
        Rook whiteRookLeft = new Rook(0, 0, Color.WHITE, this);
        Rook whiteRookRight = new Rook(7, 0, Color.WHITE, this);

        Rook blackRookLeft = new Rook(0, 7, Color.BLACK, this);
        Rook blackRookRight = new Rook(7, 7, Color.BLACK, this);

        this.board[0][0].isOccupied = true;
        this.board[7][0].isOccupied = true;
        this.board[0][0].occupyingPiece = whiteRookLeft;
        this.board[7][0].occupyingPiece = whiteRookRight;

        this.board[0][7].isOccupied = true;
        this.board[7][7].isOccupied = true;
        this.board[0][7].occupyingPiece = blackRookLeft;
        this.board[7][7].occupyingPiece = blackRookRight;
    }

    /**
     * Method to set up Pawns into their initial position
     */
    private void setUpPawns() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            Pawn whitePawn = new Pawn(i, 1, Color.WHITE, this);
            Pawn blackPawn = new Pawn(i, 6, Color.BLACK, this);

            this.board[i][1].isOccupied = true;
            this.board[i][6].isOccupied = true;

            this.board[i][1].occupyingPiece = whitePawn;
            this.board[i][6].occupyingPiece = blackPawn;
        }
    }

    /**
     * Method to set up Queens into their initial position
     */
    private void setUpQueens() {
        Queen whiteQueen = new Queen(3, 0, Color.WHITE, this);

        Queen blackQueen = new Queen(3, 7, Color.BLACK, this);

        this.board[3][0].isOccupied = true;
        this.board[3][0].occupyingPiece = whiteQueen;

        this.board[3][7].isOccupied = true;
        this.board[3][7].occupyingPiece = blackQueen;
    }

    /**
     * Method to set up Kings into their initial position
     */
    private void setUpKings() {
        whiteKing = new King(4, 0, Color.WHITE, this);

        blackKing = new King(4, 7, Color.BLACK, this);

        this.board[4][0].isOccupied = true;
        this.board[4][0].occupyingPiece = whiteKing;

        this.board[4][7].isOccupied = true;
        this.board[4][7].occupyingPiece = blackKing;
    }
}
