package pl.exercise;

public class App {
    public static void main(final String[] args) {

        System.out.println("Hello World!");
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();
        sudokuBoard.displayBoard();

    }
}
