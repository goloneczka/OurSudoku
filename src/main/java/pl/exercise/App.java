package pl.exercise;

public class App {
    public static void display(final SudokuBoard sudokuBoard) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuBoard.get(i, j).getFieldValue());
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(final String[] args) {


        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.set(2, 2, 2);
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);
        display(sudokuBoard);
        sudokuBoard.clearBoard();
        display(sudokuBoard);
        sudokuSolver.solve(sudokuBoard);
        display(sudokuBoard);
        sudokuBoard.clearBoard();
        display(sudokuBoard);




    }
}
