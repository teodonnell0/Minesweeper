/*
 * Minesweeper
 * Version 1.0
 * Software Developer: Travis O'Donnell
 * Copyright (c) 2014.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;


public class MinesweeperGUI extends JFrame {

    private MinesweeperBoard game;

    public MinesweeperGUI() {
        super("Minesweeper");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        game = new MinesweeperBoard(new Easy());

        getContentPane().add(initMenu());
        getContentPane().add(game);
        pack();
        setVisible(true);
    }

    private JMenuBar initMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem newGame = new JMenuItem(new AbstractAction("New Game") {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new MinesweeperBoard(new Easy());
            }
        });


        JMenuItem exit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        file.add(newGame);
        file.add(exit);

        JMenu difficulty = new JMenu("Difficulty");

        JMenuItem easy = new JMenuItem(new AbstractAction("Easy") {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new MinesweeperBoard(new Easy());
            }
        });


        JMenuItem medium = new JMenuItem(new AbstractAction("Medium") {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new MinesweeperBoard(new Medium());
            }
        });


        JMenuItem hard = new JMenuItem(new AbstractAction("Hard") {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new MinesweeperBoard(new Hard());
            }
        });

        difficulty.add(easy);
        difficulty.add(medium);
        difficulty.add(hard);

        menuBar.add(file);
        menuBar.add(difficulty);

        return menuBar;


    }

    public static void main(String[] args) {
        new MinesweeperGUI();
    }
}
