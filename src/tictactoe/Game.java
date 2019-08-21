package tictactoe;

import java.util.Arrays;

public class Game {
    char[][] board;
    char turn;
    int moved;
    String mode;
    char winner = ' ';

    Game(String mode) {
        reset();
        this.mode = mode;
    }

    void reset() {
        board = new char[][]{
                { ' ',' ',' ' },
                { ' ',' ',' ' },
                { ' ',' ',' ' }
        };
        turn = 'X';
        moved = 0;
    }

    String getMode() {
        return mode;
    }

    char getTurn() {
        return turn;
    }

    char getWinner() {
        return winner;
    }

    boolean isValid(int x, int y) {
        return board[x][y] == ' ';
    }

    void move(int x, int y) {
        board[x][y] = turn;
        moved++;
    }

    void switchTurn() {
        if (turn == 'X') turn = 'O';
        else turn = 'X';
    }

    boolean isEnd() {
        return moved == 9;
    }

    boolean isWin() {
        boolean isSame;
        for (int i = 0; i < 3; i++) {
            isSame = board[i][0] != ' ' &&
                     board[i][0] == board[i][1] &&
                     board[i][1] == board[i][2];
            if (isSame) {
                winner = board[i][0];
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            isSame = board[0][i] != ' ' &&
                     board[0][i] == board[1][i] &&
                     board[1][i] == board[2][i];
            if (isSame) {
                winner = board[0][i];
                return true;
            }
        }

        isSame = board[1][1] != ' ' && (
                (board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0]));
        if (isSame) winner = board[1][1];

        return isSame;
    }

    private int[] suggestMove(char c) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == ' ' && board[i][1] == c && board[i][2] == c)
                return new int[]{i, 0};
            else if (board[i][0] == c && board[i][1] == ' ' && board[i][2] == c)
                return new int[]{i, 1};
            else if (board[i][0] == c && board[i][1] == c && board[i][2] == ' ')
                return new int[]{i, 2};
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i] == ' ' && board[1][i] == c && board[2][i] == c)
                return new int[]{0, i};
            else if (board[0][i] == c && board[1][i] == ' ' && board[2][i] == c)
                return new int[]{1, i};
            else if (board[0][i] == c && board[1][i] == c && board[2][i] == ' ')
                return new int[]{2, i};
        }

        if (board[0][0] == ' ' && board[1][1] == c && board[2][2] == c)
            return new int[]{0, 0};
        else if (board[0][0] == c && board[1][1] == ' ' && board[2][2] == c)
            return new int[]{1, 1};
        else if (board[0][0] == c && board[1][1] == c && board[2][2] == ' ')
            return new int[]{2, 2};

        if (board[0][2] == ' ' && board[1][1] == c && board[2][0] == c)
            return new int[]{0, 2};
        else if (board[0][2] == c && board[1][1] == ' ' && board[2][0] == c)
            return new int[]{1, 1};
        else if (board[0][2] == c && board[1][1] == c && board[2][0] == ' ')
            return new int[]{2, 0};

        return null;
    }

    int[] computerMove() {
        int[] res;
        // attack
        res = suggestMove('O');
        if (res != null) {
            move(res[0], res[1]);
            return res;
        }

        // Defense
        res = suggestMove('X');
        if (res != null) {
            move(res[0], res[1]);
            return res;
        }

        int[][] pos = {{1,1},{0,1},{2,1},{1,0},{1,2},{0,0},{0,2},{2,0},{2,2}};
        for (int[] p : pos) {
            int x = p[0], y = p[1];
            if (board[x][y] == ' ') {
                move(x, y);
                return new int[]{x, y};
            }
        }
        return null;
    }

    void printBoard() {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
