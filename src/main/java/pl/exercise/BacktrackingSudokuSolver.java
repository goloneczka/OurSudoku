package pl.exercise;

import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private static final int N = 9;
    private Random rand = new Random();
    private boolean isRandomOne = false;

    private boolean checkArea(int x, int y, int value, final SudokuBoard board) {
        for (int i = 0; i < N; i++) {
            if (value == board.get(x, i).getFieldValue() || value == board.get(i, y).getFieldValue()) {
                return false;
            }
        }
        int r = x / 3;
        int c = y / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (value == board.get(r * 3 + i, c * 3 + j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    // zwraca null jak nie ma juz punktu z wartoscia 0
    private SudokuField whereIsZero(final SudokuBoard board) {
        SudokuField punkt = null;
        for (int z = 0; z < 9; z++) {
            for (int g = 0; g < 9; g++) {
                if (board.get(z, g).getFieldValue() == 0) {
                    punkt = new SudokuField(z, g, 0, false);
                }
            }
        }
        return punkt;
    }

    private void wypelnijJedenElementLosowo(final SudokuBoard board) {
        SudokuField sudokuField = whereIsZero(board);

        int k = rand.nextInt(9) + 1;
        if (checkArea(sudokuField.getX(), sudokuField.getY(), k, board)) {
            board.get(sudokuField.getX(), sudokuField.getY()).setFieldValue(k);
            isRandomOne = true;
        }
    }


    @Override
    public boolean solve(final SudokuBoard board) {
        if (!isRandomOne) {
            wypelnijJedenElementLosowo(board);
        }

        SudokuField sudokuField = whereIsZero(board);
        if (sudokuField == null) {
            isRandomOne = false;
            return true;
        }

        for (int k = 1; k <= N; k++) {
            if (checkArea(sudokuField.getX(), sudokuField.getY(), k, board)) {
                board.get(sudokuField.getX(), sudokuField.getY()).setFieldValue(k);
                if (solve(board)) {
                    return true;
                }
            } else {
                board.get(sudokuField.getX(), sudokuField.getY()).setFieldValue(0);
            }
        }
        return false;
    }
}
