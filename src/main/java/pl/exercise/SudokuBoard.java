package pl.exercise;
import java.util.Random;


public class SudokuBoard {

    private static final int N = 9;
    private Point[][] board = new Point[N][N];

    public SudokuBoard() {
        //generuje puste sudoku
        for (int i=0; i < N; i++) {
            for (int j=0; j < N; j++) {
                this.board[i][j]= new Point(i, j, 0, false);
            }
        }
    }

    // Zakladam ze check Board ma sprawdzac te poczatkowe punkty wprowadzane przez uytkownika
    private boolean checkBoard(int x, int y, int value) {
        for (int i=0; i < N; i++) {
            if (value == board[x][i].getValue() || value == board[i][y].getValue()) {
                return false;
            }
        }
        int r = x / 3;
        int c = y / 3;
        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {
                if (value == board[r * 3 + i][c * 3 + j].getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Point get(int i, int j) {
        return board[i][j];
    }

    public void set(int i, int j, int k) {
        if (!checkBoard(i, j, k)) {
            return;
        }
        Point punkt = new Point(i, j, k, true);
        board[punkt.getX()][punkt.getY()] = punkt;
    }

    public void displayBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j].getValue());
            }
            System.out.println();
        }
        System.out.println();
    }

    // nie usuwa stalych point!
    public void clearBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!board[i][j].isConstPoint()) {
                    board[i][j].setValue(0);
                }
            }
        }
    }

    public Point[][] getBoard() {
        return board;
    }


}

