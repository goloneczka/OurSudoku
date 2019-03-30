package pl.exercise;

public class App {
    public static void main(final String[] args) {

        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.set(2, 2, 2);
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);
        sudokuBoard.displayBoard();
       // sudokuSolver.clearBoard(sudokuBoard);   nie mozna wywolac metody niezaimplementowanej w interfejsie ???
        sudokuBoard.clearBoard();
        sudokuBoard.displayBoard();
        sudokuSolver.solve(sudokuBoard);
        sudokuBoard.displayBoard();
        sudokuBoard.clearBoard();
        sudokuBoard.displayBoard();



    }
}
