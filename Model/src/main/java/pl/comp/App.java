package pl.comp;

import java.io.IOException;

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

    public static void main(final String[] args) throws IOException {


        SudokuBoard sudokuBoard = new SudokuBoard();
      /*  sudokuBoard.set(2, 2, 2);
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);
        System.out.println(sudokuBoard);
        sudokuBoard.clearBoard();
       // display(sudokuBoard);
        sudokuSolver.solve(sudokuBoard);
        display(sudokuBoard);


        Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("fileSudokuBoardDao.txt");
        System.out.println(dao.read());

        Dao<SudokuBoard> dao1 = SudokuBoardDaoFactory.getFileDao("fileSudokuBoardDao1.txt");
        dao1.write(sudokuBoard);*/





    }
}
