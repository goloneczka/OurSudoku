package pl.exercise;

import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private static final int N = 9;
    private Random rand = new Random();
    private boolean czyJestElementLosowy = false;


    private boolean checkArea(int x, int y, int value, final SudokuBoard board) {
        for (int i = 0; i < N; i++) {
            if (value == board.get(x, i).getValue() || value == board.get(i, y).getValue()) {
                return false;
            }
        }
        int r = x / 3;
        int c = y / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (value == board.get(r * 3 + i, c * 3 + j).getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    // zwraca null jak nie ma juz punktu z wartoscia 0
    private Point whereIsZero(final SudokuBoard board) {
        Point punkt = null;
        for (int z = 0; z < 9; z++) {
            for (int g = 0; g < 9; g++) {
                if (board.get(z, g).getValue() == 0) {
                    punkt = new Point(z, g, 0, false);
                }
            }
        }
        return punkt;
    }

    private void wypelnijJedenElementLosowo(final SudokuBoard board) {
        Point point = whereIsZero(board);

        int k = rand.nextInt(9) + 1;
        if (checkArea(point.getX(), point.getY(), k, board)) {
            board.get(point.getX(), point.getY()).setValue(k);
            czyJestElementLosowy = true;
        }
    }


    @Override
    public boolean solve(final SudokuBoard board) {
        if (!czyJestElementLosowy) {
            wypelnijJedenElementLosowo(board);
        }

        Point point = whereIsZero(board);
        if (point == null) {
            czyJestElementLosowy = false;
            return true;
        }

        for (int k = 1; k <= N; k++) {
            if (checkArea(point.getX(), point.getY(), k, board)) {
                board.get(point.getX(), point.getY()).setValue(k);
                if (solve(board)) {
                    return true;
                }
            } else {
                board.get(point.getX(), point.getY()).setValue(0);
            }
        }
        return false;
    }
}
