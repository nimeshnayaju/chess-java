package com.chess.engine.game;

import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;
import com.chess.gui.ChessDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class ChessGame {

    Board chessBoard;
    Piece movingPiece;
    Color gameTurn;
    static Player playerWhite;
    static Player playerBlack;
    int tileSize;
    boolean gameOver;
    boolean firstClick;

    JFrame window;
    JPanel gamePanel;
    JPanel sidePanel;

    JLabel whiteLabel;
    JLabel blackLabel;
    JLabel whiteScore;
    JLabel blackScore;

    JButton restartButton;

    Stack<Move> moveStack;

    /**
     * A method to initialize the chess board
     */
    public void gameInit() {
        this.chessBoard = new Board();
        chessBoard.populateBoardWithTiles();
        chessBoard.populateBoardWithPieces();
        gameTurn = Color.WHITE;
        gameOver = false;
        firstClick = true;
        tileSize = 60;
        moveStack = new Stack();
    }

    /**
     * A helper method to display and set up players for the current game
     */
    private static void playersInit() {
        String nameWhite = JOptionPane.showInputDialog("Please input Player 1 (White) name");
        String nameBlack = JOptionPane.showInputDialog("Please input Player 2 (Black) name");

        playerWhite = new Player(nameWhite, Color.WHITE);
        playerBlack = new Player(nameBlack, Color.BLACK);
    }

    /**
     * A method to start the game thread and run game loop
     */
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

        whiteLabel = new JLabel("Player 1: ".concat(playerWhite.playerName));
        whiteLabel.setForeground(java.awt.Color.BLUE);
        blackLabel = new JLabel("Player 2: ".concat(playerBlack.playerName));

        whiteScore = new JLabel("Score: " + playerWhite.playerScore);
        whiteScore.setForeground(java.awt.Color.BLUE);
        blackScore = new JLabel("Score: " + playerBlack.playerScore);

        sideDisplay.setLayout(new BoxLayout(sideDisplay, BoxLayout.PAGE_AXIS));
        sideDisplay.add(restartButton);
        sideDisplay.add(whiteLabel);
        sideDisplay.add(whiteScore);
        sideDisplay.add(blackLabel);
        sideDisplay.add(blackScore);

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
                if(firstClick) {
                    int originRow = e.getX();
                    int originCol = e.getY();
                    originRow = originRow/tileSize;
                    originCol = 7 - originCol/tileSize;

                    movingPiece = chessBoard.board[originRow][originCol].occupyingPiece;
                    if(movingPiece == null) {
                        firstClick = true;
                    }
                    else if(gameTurn == movingPiece.color) {
                        firstClick = false;
                    }
                } else {
                    int finalRow = e.getX();
                    int finalCol = e.getY();
                    finalRow = finalRow/tileSize;
                    finalCol = 7 - finalCol/tileSize;

                    if(movingPiece.canMoveTo(finalRow, finalCol)) {
                        Piece enemyPiece = null;
                        if(chessBoard.board[finalRow][finalCol].isOccupied) {
                            enemyPiece = chessBoard.board[finalRow][finalCol].occupyingPiece;
                        }
                        Move newMove = new Move(movingPiece, enemyPiece, finalRow, finalCol);
                        moveStack.add(newMove);
                        newMove.executeMove();
                        gameTurn = gameTurn.opposite();
                        checkKingStatus(movingPiece);
                    } else {
                        JOptionPane.showMessageDialog(null,"Illegal Move", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    firstClick = true;
                }
            }
        });
    }

    /**
     * A helper method to check if the player's king is in check
     * @param movingPiece
     */
    private void checkKingStatus(Piece movingPiece) {
        Player currentPlayer;
        Player opponentPlayer;
        King kingToInspect;
        if(movingPiece.color.equals(Color.WHITE)) {
            currentPlayer = playerWhite;
            opponentPlayer = playerBlack;
            kingToInspect = chessBoard.blackKing;
        } else {
            currentPlayer = playerBlack;
            opponentPlayer = playerWhite;
            kingToInspect = chessBoard.whiteKing;
        }

        if(kingToInspect.isInCheck()) {
            if(kingToInspect.isInCheckmate()) {
                JOptionPane.showMessageDialog(null, currentPlayer.playerName + "'s King is in checkmate\n Please click Restart to play again", "Checkmate", JOptionPane.WARNING_MESSAGE);
                gameOver = true;
                opponentPlayer.playerScore++;
                whiteScore.setText("Score: " + playerWhite.playerScore);
                blackScore.setText("Score: " + playerBlack.playerScore);
                return;
            }
            JOptionPane.showMessageDialog(null, currentPlayer.playerName + "'s King is in check", "Check", JOptionPane.WARNING_MESSAGE);
        }
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
        newGame.mouseActions();
    }
}


