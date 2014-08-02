/*
 * Minesweeper
 * Version 1.0
 * Software Developer: Travis O'Donnell
 * Copyright (c) 2014.
 */

public class Cell {
    private boolean hidden;
    private boolean checked;

    public Cell() {
        this.hidden = true;
        this.checked = false;
    }

    protected boolean isHidden() {
        return hidden;
    }

    protected void setHidden(boolean b) {
        hidden = b;
    }

    protected boolean wasChecked() {
        return checked;
    }

    protected void setChecked(boolean b) {
        checked = b;
    }
}
