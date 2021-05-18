package model;

import java.util.Random;

public class Ai{
    Game game;
    Random r = new Random();
    private boolean possibleWin;
    private int[] coordinates = new int[2];

    public Ai(Game game) {
        this.game = game;
    }

    private void generateRandomCoordinates() {
        coordinates[0] = r.nextInt(3);
        coordinates[1] = r.nextInt(3);
    }

    public void easyAiMove(char c) {
        do {
            generateRandomCoordinates();
            if (game.isCellEmpty(coordinates)) {
                game.writeToCell(coordinates, c);
                break;
            }
        } while (true);
    }

    public void mediumAiMove(char c) {
        getWinningCoordinates();
        if (possibleWin) {
            game.writeToCell(coordinates,c);
        } else {
            easyAiMove(c);
        }
    }

    public void hardAiMove(char c) {
        if (checkForEmptyGrid()) {
            generateRandomCoordinates();
        } else {
            coordinates = Minimax.getBestMove(game, c);
        }
        game.writeToCell(coordinates, c);
    }

    protected boolean checkForEmptyGrid() {
        boolean flag = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.getGrid()[i][j] != ' ') {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public void getWinningCoordinates() {
        possibleWin = false;
        int t = game.countFrontDiagonal();
        if (t == 208 || t == 190) {
            for (int i = 0; i < 3; i++) {
                if (game.getGrid()[i][i] == ' ') {
                    coordinates[0] = i;
                    coordinates[1] = i;
                    possibleWin = true;
                    break;
                }
            }
        }
        t = game.countBackDiagonal();
        if (t == 208 || t == 190) {
            for (int i = 0; i < 3; i++) {
                if (game.getGrid()[i][2 - i] == ' ') {
                    coordinates[0] = i;
                    coordinates[1] = 2 - i;
                    possibleWin = true;
                    break;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            t = game.countRow(i);
            if (t == 208 || t == 190) {
                for (int j = 0; j < 3; j++) {
                    if (game.getGrid()[i][j] == ' ') {
                        coordinates[0] = i;
                        coordinates[1] = j;
                        possibleWin = true;
                        break;
                    }
                }
                break;
            }
        }
        for (int i = 0; i < 3; i++) {
            t = game.countCol(i);
            if (t == 208 || t == 190) {
                for (int j = 0; j < 3; j++) {
                    if (game.getGrid()[j][i] == ' ') {
                        coordinates[0] = j;
                        coordinates[1] = i;
                        possibleWin = true;
                        break;
                    }
                }
                break;
            }
        }
    }
}