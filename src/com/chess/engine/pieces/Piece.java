package com.chess.engine.pieces;

import com.chess.engine.game.Board;
import com.chess.engine.game.Color;
import com.chess.engine.game.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Abstract Piece class
 */
public abstract class Piece {

    public int row, col;
    Board chessBoard;
    public final Color color;
    String pieceType;

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

        board.board[pieceRow][pieceCol].occupyingPiece = this;
        board.board[pieceRow][pieceCol].isOccupied = true;
        this.chessBoard = board;
    }

    /**
     * Abstract method that checks if the specific Piece can move to a location specified by (nextRow, nextCol)
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    protected abstract boolean isValidSpecialMove(int nextRow, int nextCol);

    /**
     * A method that checks if a Piece can be moved to a location
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    public boolean canMoveTo(int nextRow, int nextCol) {
        if(inBoardBounds(nextRow, nextCol) && isNotOrigin(nextRow, nextCol) && isValidSpecialMove(nextRow, nextCol) && hasEnemyPieceAtDestination(nextRow, nextCol) && !wouldPutYourKingInCheck(nextRow, nextCol)) {
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
    private boolean inBoardBounds(int nextRow, int nextCol) {
        if (nextRow < 0 || nextCol < 0 || nextRow >= 8 || nextCol >= 8) {
            return false;
        }
        return true;
    }

    /**
     * Helper method to check if the destination location is unoccupied or contains enemy piece
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    boolean hasEnemyPieceAtDestination(int nextRow, int nextCol) {
        Tile destinationTile = chessBoard.board[nextRow][nextCol];
        if(destinationTile.isOccupied) {
            if(this.color.equals(destinationTile.occupyingPiece.color)) {
                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * A method that moves the Piece into a given location
     * @param nextRow
     * @param nextCol
     */
    public void movePiece(int nextRow, int nextCol) {
        Tile currentTile = chessBoard.board[this.row][this.col];
        Tile destinationTile = chessBoard.board[nextRow][nextCol];

        currentTile.isOccupied = false;
        currentTile.occupyingPiece = null;

        destinationTile.isOccupied = true;
        destinationTile.occupyingPiece = this;

        this.row = nextRow;
        this.col = nextCol;
    }

    /**
     * A helper method to check if the Piece illegally leaps over another Piece
     * @param rowDisplacement
     * @param colDisplacement
     * @return
     */
    boolean isValidLeaping(int rowDisplacement, int colDisplacement) {
        int steps = Math.max(Math.abs(rowDisplacement), Math.abs(colDisplacement));
        int rowDirection = rowDisplacement/steps;
        int colDirection = colDisplacement/steps;

        for(int i = 1; i < steps; i++) {
            Tile tileToCheck = chessBoard.board[this.row + i*rowDirection][this.col + i*colDirection];
            if(tileToCheck.isOccupied) {
                return false;
            }
        }
        return true;
    }

    /**
     * A helper method to check if the Piece lands on the original Tile
     * @param nextRow
     * @param nextCol
     * @return
     */
    private boolean isNotOrigin(int nextRow, int nextCol) {
        if(this.row == nextRow && this.col == nextCol) {
            return false;
        }
        return true;
    }

    /**
     * Helper method to check if the move would put the player's king into check
     * @param nextRow
     * @param nextCol
     * @return boolean
     */
    private boolean wouldPutYourKingInCheck(int nextRow, int nextCol) {
        int originRow = this.row;
        int originCol = this.col;
        King kingToInspect;
        Tile tileToCheck = chessBoard.board[nextRow][nextCol];
        if(tileToCheck.isOccupied) {
            Piece enemyPiece = tileToCheck.occupyingPiece;
            if(this.color.equals(Color.WHITE)) {
                kingToInspect = chessBoard.whiteKing;
            } else {
                kingToInspect = chessBoard.blackKing;
            }

            this.movePiece(nextRow, nextCol);
            if(kingToInspect.isInCheck()) {
                this.movePiece(originRow, originCol);
                enemyPiece.movePiece(nextRow, nextCol);
                return true;
            }
            this.movePiece(originRow, originCol);
            enemyPiece.movePiece(nextRow, nextCol);

        } else {
            if(this.color.equals(Color.WHITE)) {
                kingToInspect = chessBoard.whiteKing;
            } else {
                kingToInspect = chessBoard.blackKing;
            }
            this.movePiece(nextRow, nextCol);
            if(kingToInspect.isInCheck()) {
                this.movePiece(originRow, originCol);
                return true;
            }
            this.movePiece(originRow, originCol);
        }
        return false;
    }

    /**
     * A method to draw specified piece on board after determining the piece asset to draw
     * @param graphic
     * @param tileSize
     * @param row
     * @param col
     */
    public void drawPieceOnBoard(Graphics graphic, int tileSize, int row, int col) {
        String pieceName = this.pieceType.concat(".png");
        String imageDirectory;
        if(this.color.equals(Color.BLACK)) {
            imageDirectory = "assets/black/";
        } else {
            imageDirectory = "assets/white/";
        }
        String imagePath = imageDirectory.concat(pieceName);
        drawPiece(graphic, tileSize, imagePath, row, col);
    }

    /**
     * A helper method to draw piece on the specified location on the board
     * @param graphic
     * @param tileSize
     * @param imagePath
     * @param x
     * @param y
     */
    private void drawPiece(Graphics graphic, int tileSize, String imagePath, int x, int y) {
        File imageFile = new File(imagePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int imageHeight = image.getHeight()/2 - 10;
        int imageWidth = image.getWidth()/2 - 10;
        int heightPadding = (tileSize - imageHeight)/2;
        int widthPadding = (tileSize - imageWidth)/2;
        graphic.drawImage(image, (tileSize*x) + widthPadding, ((7-y)*tileSize) + heightPadding, imageWidth, imageHeight, null);
    }
}
