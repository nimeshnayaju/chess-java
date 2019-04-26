package com.chess.gui;

import com.chess.engine.game.Board;
import com.chess.engine.game.Tile;

import javax.swing.*;
import java.awt.*;

import static com.chess.engine.game.Board.BOARD_SIZE;

public class ChessDisplay extends JPanel {
    Board chessboard;
    int tileSize;

    public ChessDisplay(Board chessBoard, int tileSize) {
        this.chessboard = chessBoard;
        this.tileSize = tileSize;
    }

    @Override
    public void paintComponent(Graphics graphic) {
        for(int row = 0; row < BOARD_SIZE; row++) {
            for(int col = 0; col < BOARD_SIZE; col++) {
                Tile tile = chessboard.board[row][col];
                if(tile.color.equals(Color.BLACK)) {
                    graphic.setColor(new Color(30, 30, 30));
                    graphic.fillRect((tileSize*row), (7 - col)*tileSize, tileSize, tileSize);
                    if(tile.isOccupied) {
                        tile.occupyingPiece.drawPieceOnBoard(graphic, tileSize, row, col);
                    } else {
                        graphic.setColor(new Color(230, 230, 250));
                        graphic.fillRect((tileSize*row), (7 - col)*tileSize, tileSize, tileSize);
                        if(tile.isOccupied) {
                            tile.occupyingPiece.drawPieceOnBoard(graphic, tileSize, row, col);
                        }
                    }
                }
            }
        }
    }
}
