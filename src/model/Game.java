package model;

public class Game {
    private char[][] grid = new char[3][3];
    private boolean xWins;
    private boolean oWins;
    private boolean gameOver;
    private String state;

    public Game() {
        emptyGrid();
    }

    protected void emptyGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.grid[i][j] = ' ';
            }
        }
    }

    public boolean isGridFull() {
        boolean flag = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public void writeToCell(int[] coordinates, char c) {
        c = Character.toUpperCase(c);
        grid[coordinates[0]][coordinates[1]] = c;
    }

    public boolean isCellEmpty(int[] coordinates) {
        return grid[coordinates[0]][coordinates[1]] == ' ';
    }

    void setGameOver() {
        if (xWins || oWins) {
            gameOver = true;
        } else gameOver = isGridFull();
    }

    void setGameState() {
        if (xWins) {
            state = "X wins";
        } else if (oWins) {
            state = "O wins";
        } else if (isGridFull()) {
            state = "Draw";
        } else {
            state = "Game not finished";
        }
    }

    void setWinner() {
        int t1 = countFrontDiagonal();
        int t2 = countBackDiagonal();
        if (t1 == 264 || t2 == 264) {
            xWins = true;
        }

        if (t1 == 237 || t2 == 237) {
            oWins = true;
        }

        for (int i = 0; i < 3; i++) {
            int t3 = countRow(i);
            int t4 = countCol(i);
            if (t3 == 264 || t4 == 264) {
                xWins = true;
                break;
            }
            if (t3 == 237 || t4 == 237) {
                oWins = true;
                break;
            }
        }
    }

    public void checkGame() {
        setWinner();
        setGameState();
        setGameOver();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char[][] getGrid() {
        return grid;
    }

    public String getState() {
        return state;
    }

    public void makeNewGame() {
        emptyGrid();
        xWins = false;
        oWins = false;
        state = "";
        gameOver = false;
    }

    protected int countRow(int n) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            count = count + (int)grid[n][i];
        }
        return count;
    }

    protected int countCol(int n) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            count = count + (int)grid[i][n];
        }
        return count;
    }

    protected int countFrontDiagonal() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            count = count + (int)grid[i][i];
        }
        return count;
    }

    protected int countBackDiagonal() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            count = count + (int)grid[i][(grid[i].length - 1) - i];
        }
        return count;
    }
}