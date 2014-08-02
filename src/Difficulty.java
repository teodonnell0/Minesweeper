/*
 * Minesweeper
 * Version 1.0
 * Software Developer: Travis O'Donnell
 * Copyright (c) 2014.
 */

public abstract class Difficulty {

    private final int ROWS;
    private final int COLUMNS;
    private final int NUM_OF_MINES;

    public Difficulty(int r, int c, int m) {
        this.ROWS = r;
        this.COLUMNS = c;
        this.NUM_OF_MINES = m;
    }

    int getRows() {
        return ROWS;
    }

    int getColumns() {
        return COLUMNS;
    }

    int getMines() {
        return NUM_OF_MINES;
    }

}

class Easy extends Difficulty {
    public Easy() {
        super(15, 20, 30);
    }
}

class Medium extends Difficulty {
    public Medium() {
        super(45, 60, 270);
    }
}

class Hard extends Difficulty {
    public Hard() {
        super(100, 100, 1000);
    }
}
/* Upcoming feature
class CustomDifficulty extends Difficulty {
    public CustomDifficulty(int r, int c, int m) {
        super(r, c, m);

        if (r < 10 || c < 10 || m > r * c || m < 1)
            System.err.println("Invalid custom settings");
    }
}
*/