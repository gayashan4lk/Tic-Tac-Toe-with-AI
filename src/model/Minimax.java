package model;

public class Minimax {
    public static double miniMax(Game game, char aiPlayer, boolean isMax){
        char opponent;
        if (aiPlayer == 'X') {
            opponent = 'O';
        } else {
            opponent = 'X';
        }
        double value = evaluateBoard(game.getGrid(), aiPlayer);
        if (Math.abs(value) > 0 || game.isGridFull()) {
            return value;
        }
        double val;
        if (isMax) {
            // MAX part of minimax
            val = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game.getGrid()[i][j] == ' ') {
                        game.writeToCell(new int[]{i, j}, aiPlayer);
                        val = Math.max(val, miniMax(game, aiPlayer, false));
                        game.writeToCell(new int[]{i, j}, ' ');
                    }
                }
            }
        } else {
            // MIN part of minimax
            val = Double.POSITIVE_INFINITY;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game.getGrid()[i][j] == ' ') {
                        game.writeToCell(new int[]{i, j}, opponent);
                        val = Math.min(val, miniMax(game, aiPlayer,true));
                        game.writeToCell(new int[]{i, j}, ' ');
                    }
                }
            }
        }
        return val;
    }

    public static int[] getBestMove(Game game, char aiPlayer) {
        int[] bestMove = new int[2];
        double bestValue = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.getGrid()[i][j] == ' ') {
                    game.writeToCell(new int[] {i, j}, aiPlayer);
                    double value = miniMax(game, aiPlayer, false);
                    game.writeToCell(new int[] {i, j}, ' ');
                    if (value > bestValue) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestValue = value;
                    }
                }
            }
        }
        return bestMove;
    }

    public static double evaluateBoard(char[][] grid, char aiPlayer) {
        double score = 0;
        int count = 0;
        for (int i = 0; i < 3; i++) {
            count = count + (int)grid[i][i];
            if (count == 264 || count == 237) {
                if ((count == 264 && aiPlayer == 'X') || (count == 237 && aiPlayer == 'O')) {
                    score = utility(grid, +1);
                    break;
                }
                if ((count == 264 && aiPlayer == 'O') || (count == 237 && aiPlayer == 'X')) {
                    score = utility(grid, -1);
                    break;
                }
            }
        }
        count = 0;
        for (int i = 0; i < 3; i++) {
            count = count + (int)grid[i][(grid[i].length - 1) - i];
            if ((count == 264 && aiPlayer == 'X') || (count == 237 && aiPlayer == 'O')) {
                score = utility(grid, +1);
                break;
            }
            if ((count == 264 && aiPlayer == 'O') || (count == 237 && aiPlayer == 'X')) {
                score = utility(grid, -1);
                break;
            }
        }
        count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                count = count + (int)grid[i][j];
                if ((count == 264 && aiPlayer == 'X') || (count == 237 && aiPlayer == 'O')) {
                    score = utility(grid, +1);
                    break;
                }
                if ((count == 264 && aiPlayer == 'O') || (count == 237 && aiPlayer == 'X')) {
                    score = utility(grid, -1);
                    break;
                }
            }
            count = 0;
        }
        count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                count = count + (int)grid[j][i];
                if ((count == 264 && aiPlayer == 'X') || (count == 237 && aiPlayer == 'O')) {
                    score = utility(grid, +1);
                    break;
                }
                if ((count == 264 && aiPlayer == 'O') || (count == 237 && aiPlayer == 'X')) {
                    score = utility(grid, -1);
                    break;
                }
            }
            count = 0;
        }
        return score;
    }

    public static int utility(char[][] grid, int sign){
        int emptyCellCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    emptyCellCount++;
                }
            }
        }
        return sign * (emptyCellCount + 1);
    }
}
