package com.chess.gui;

import com.chess.engine.game.Board;
import com.chess.engine.game.Tile;
import com.chess.engine.game.Color;

import javax.swing.*;
import java.awt.*;

import static com.chess.engine.game.Board.BOARD_SIZE;

public class ChessDisplay extends JPanel {
    Board chessboard;
    int tileSize;

    /**
     * Constructor for ChessDisplay
     * @param chessBoard
     * @param tileSize
     */
    public ChessDisplay(Board chessBoard, int tileSize) {
        this.chessboard = chessBoard;
        this.tileSize = tileSize;
    }

    /**
     * A method that overrides JPanel's paintComponent to draw custom components on the Panel
     * @param graphic
     */
    @Override
    public void paintComponent(Graphics graphic){
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                Tile tileToDraw = chessboard.board[i][j];
                if(tileToDraw.color.equals(Color.BLACK)){
                    graphic.setColor(new java.awt.Color(118, 205, 194));
                    graphic.fillRect((tileSize*i), (7-j)*tileSize, tileSize, tileSize);
                    if(tileToDraw.isOccupied) {
                        tileToDraw.occupyingPiece.drawPieceOnBoard(graphic, tileSize, i, j);
                    }
                }
                else{
                    graphic.setColor(new java.awt.Color(230, 230, 250));
                    graphic.fillRect((tileSize*i), (7-j)*tileSize, tileSize, tileSize);
                    if(tileToDraw.isOccupied) {
                        tileToDraw.occupyingPiece.drawPieceOnBoard(graphic, tileSize, i, j);
                    }
                }
            }
        }
    }
}
