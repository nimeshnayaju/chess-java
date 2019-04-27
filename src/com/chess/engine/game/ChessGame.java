package com.chess.engine.game;

import com.chess.engine.pieces.Piece;
import com.chess.gui.ChessDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessGame {

    Board chessBoard;
    Piece movingPiece;
    Color gameTurn;
    static Player playerWhite;
    static Player playerBlack;
    int tileSize;
    boolean gameOver;

    JFrame window;
    JPanel gamePanel;
    JPanel sidePanel;

    JButton restartButton;

    public void gameInit() {
        this.chessBoard = new Board();
        chessBoard.populateBoardWithTiles();
        chessBoard.populateBoardWithPieces();
        gameTurn = Color.WHITE;
        gameOver = false;
        tileSize = 60;
    }

    private static void playersInit() {
        String nameWhite = JOptionPane.showInputDialog("Please input Player 1 (White) name");
        String nameBlack = JOptionPane.showInputDialog("Please input Player 2 (Black) name");

        playerWhite = new Player(nameWhite, Color.WHITE);
        playerBlack = new Player(nameBlack, Color.BLACK);
    }

    public void gameStart() {
        Thread gameThread = new Thread() {
            @Override
            public void run() {
                gameLoop();
            }
        };
        gameThread.start();
    }

    public void gameLoop() {
        while(true) {
            if(gameOver) {
                break;
            }
            gamePanel.repaint();
        }
    }

    public void setUpDisplay() {
        window = new JFrame("Chess");
        gamePanel = initializeGamePanel(chessBoard);
        Container contentPanel = window.getContentPane();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));

        sidePanel = initializeSidePanel();
        contentPanel.add(gamePanel);
        contentPanel.add(sidePanel);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.validate();
        window.pack();
    }

    private JPanel initializeGamePanel(Board chessBoard) {
        ChessDisplay chessDisplay = new ChessDisplay(chessBoard, tileSize);
        chessDisplay.setPreferredSize(new Dimension(550, 550));
        chessDisplay.setLayout(new BorderLayout());
        return chessDisplay;
    }

    private JPanel initializeSidePanel() {
        JPanel sideDisplay = new JPanel();
        restartButton = new JButton("Restart");

        setUpButtonListeners();

        sideDisplay.setLayout(new BoxLayout(sideDisplay, BoxLayout.PAGE_AXIS));
        sideDisplay.add(restartButton);

        return sideDisplay;
    }

    private void setUpButtonListeners() {
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
    }

    private void restartGame() {
        String player;
        if(gameTurn.equals(Color.WHITE)) {
            player = playerBlack.playerName;
        } else {
            player = playerWhite.playerName;
        }
        int response = JOptionPane.showConfirmDialog(null, "Would you like you restart the game?");
        if(response == JOptionPane.YES_OPTION) {
            gameOver = true;
            window.setVisible(false);
            startNewGame();
        }
    }

    public void mouseActions() {
        gamePanel.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }
        });
    }

    public static void main(String[] args) {
        playersInit();
        startNewGame();
    }

    private static void startNewGame() {
        ChessGame newGame = new ChessGame();
        newGame.gameInit();
        newGame.setUpDisplay();
        newGame.gameStart();
    }
}


