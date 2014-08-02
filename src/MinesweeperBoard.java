/*
 * Minesweeper
 * Version 1.0
 * Software Developer: Travis O'Donnell
 * Copyright (c) 2014.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class MinesweeperBoard extends JPanel {

    private final Color BACKGROUND = Color.GRAY;
    private final Color LINES = Color.BLACK;

    private final Color SHOWN = new Color(200, 200, 200);

    private Cell[][] gameBoard;

    private byte[][] board;

    private final int ROWS, COLUMNS, NUM_OF_MINES;

    private final byte MINE = -128;

    private boolean gameOver;

    public MinesweeperBoard(Difficulty d) {
        this.ROWS = d.getRows();
        this.COLUMNS = d.getColumns();
        this.NUM_OF_MINES = d.getMines();

        this.setPreferredSize(new Dimension(COLUMNS * 15, ROWS * 15));
        initializeGame();

        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!gameOver) {
                    int row = e.getY() / 15;
                    int col = e.getX() / 15;

                    if (gameBoard[row][col].isHidden()) {
                        gameBoard[row][col].setHidden(false);

                        if (board[row][col] == 0)
                            checkZeroAt(row, col);

                        if (board[row][col] == MINE)
                            gameOver = true;

                        repaint();
                    }
                }
            }
        };

        addMouseListener(listener);
        repaint();
    }

    private void initializeGame() {
        gameOver = false;
        board = new byte[ROWS][COLUMNS];
        gameBoard = new Cell[ROWS][COLUMNS];

        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS; j++)
                gameBoard[i][j] = new Cell();

        generateMines();
        checkNeighbors();
    }

    private void generateMines() {
        Random r = new Random();
        int x, y;
        for (int i = 0; i < NUM_OF_MINES; i++) {
            x = r.nextInt(ROWS);
            y = r.nextInt(COLUMNS);
            if (board[x][y] == MINE) // mine already in place here
                i--;
            else
                board[x][y] = MINE;
        }
    }

    private void checkNeighbors() {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS; j++)
                if (board[i][j] != MINE)
                    board[i][j] = (byte) (checkMineAt(i - 1, j) + checkMineAt(i - 1, j - 1) + checkMineAt(i - 1, j + 1) + checkMineAt(i, j - 1) + checkMineAt(i, j + 1) + checkMineAt(i + 1, j) + checkMineAt(i + 1, j - 1) + checkMineAt(i + 1, j + 1));
    }

    private byte checkMineAt(int x, int y) {
        if (x < 0 || y < 0 || x > ROWS - 1 || y > COLUMNS - 1)
            return 0;
        else if (board[x][y] == MINE)
            return 1;
        else
            return 0;
    }

    private boolean checkZeroAt(int x, int y) {
        if (x < 0 || y < 0 || x > ROWS - 1 || y > COLUMNS - 1)
            return false;
        else if (board[x][y] == 0 && !gameBoard[x][y].wasChecked()) {
            gameBoard[x][y].setChecked(true);
            gameBoard[x][y].setHidden(false);
            checkZeroAt(x, y - 1);
            checkZeroAt(x, y + 1);
            checkZeroAt(x + 1, y);
            checkZeroAt(x - 1, y);
            return true;
        } else if (!gameBoard[x][y].wasChecked()) {
            gameBoard[x][y].setChecked(true);
            gameBoard[x][y].setHidden(false);
            return false;
        } else
            return false;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setBackground(BACKGROUND);

        graphics2D.setFont(new Font("pixelmix", Font.BOLD, 10));

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (!gameBoard[i][j].isHidden()) {
                    String text;

                    if (board[i][j] == 0)
                        text = "";

                    else if (board[i][j] == MINE)
                        text = "*";

                    else
                        text = "" + board[i][j];
                    graphics2D.setColor(SHOWN);
                    ((Graphics2D) g).fill(new Rectangle(j * 15, i * 15, 15, 15));
                    graphics2D.setColor(getColor(board[i][j]));
                    graphics2D.drawString(text, j * 15 + 5, i * 15 + 13);
                }
            }
        }

        graphics2D.setColor(LINES);

        for (int i = 1; i < ROWS; i++)
            graphics2D.drawLine(0, i * 15, getWidth(), i * 15);

        for (int i = 1; i < COLUMNS; i++)
            graphics2D.drawLine(i * 15, 0, i * 15, getHeight());


    }


    private Color getColor(byte b) {
        switch (b) {
            case 1:
                return Color.BLUE.brighter();
            case 2:
                return Color.GREEN.darker();
            case 3:
                return Color.RED;
            case 4:
                return Color.BLUE;
            case 5:
                return Color.RED.darker(); //brown
            case 6:
                return Color.CYAN;
            case 7:
                return Color.BLACK;
            case 8:
                return Color.LIGHT_GRAY;
            default:
                return Color.black;
        }
    }


}
