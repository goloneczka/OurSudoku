package pl.exercise;

public class App {
    public static void main(final String[] args) {

        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.displayBoard();
        sudokuBoard.fillBoard();
        sudokuBoard.displayBoard();
        sudokuBoard.clearBoard();
        sudokuBoard.displayBoard();
        sudokuBoard.fillBoard();
        sudokuBoard.displayBoard();

    }
}
