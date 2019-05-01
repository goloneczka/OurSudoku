package pl.exercise;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
public class SudokuBoardTest {

    SudokuBoard sudokuBoard = new SudokuBoard();
    SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

    @Test
    public void InitialSudokuBoard()
    {
        boolean test = true;
        for(int i=0; i<9; i++)
        {
            for(int j=0;j<9;j++)
            {
                if(sudokuBoard.get(i,j).getFieldValue() != 0)
                {
                    test = false;
                }
            }
        }
        sudokuBoard.set(2,2,5);
        if(sudokuBoard.get(2,2).getFieldValue() != 5) {
            test = false;
        }
        assertTrue(test);
    }

    @Test
    public void goodFillBoard() {
        boolean test = true;
        int ilePowinnoByc = 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1;
        sudokuSolver.solve(sudokuBoard);
        //sprawdzam wiersze
        for (int i = 0; i < 9; i++) {
            int iloczyn = 1;
            for (int j = 0; j < 9; j++) {
                iloczyn *= sudokuBoard.get(i, j).getFieldValue();
            }
            if (iloczyn != ilePowinnoByc) {
                test = false;
            }
        }
        //sprawdzam kolumny
        for (int i = 0; i < 9; i++) {
            int iloczyn = 1;
            for (int j = 0; j < 9; j++) {
                iloczyn *= sudokuBoard.get(i, j).getFieldValue();
            }
            if (iloczyn != ilePowinnoByc) {
                test = false;
            }
        }
        // sprawdzam bloki 3x3
        int x = 0, y = 0;
        for (int z = 0; z < 9; z++) {
            int iloczyn = 1;
            for (int i = x; i < 3 + x; i++) {

                for (int j = y; j < 3 + y; j++) {
                    iloczyn *= sudokuBoard.get(i, j).getFieldValue();
                }
            }
            if (iloczyn != ilePowinnoByc) {
                test = false;
            }
            if (x + 3 == 9) {
                x = 0;
                y = y + 3;
            } else {
                x = x + 3;
            }
        }

        assertTrue(test);
    }

    @Test
    public void rozneWypelnieniaFillBoard() {
        sudokuBoard.clearBoard();
        sudokuSolver.solve(sudokuBoard);
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                board[i][j] = sudokuBoard.getBoard().get(i).get(j).getFieldValue();
            }
        }

        sudokuBoard.clearBoard();
        sudokuSolver.solve(sudokuBoard);
        int[][] board2 = new int[9][9];
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                board2[i][j] = sudokuBoard.getBoard().get(i).get(j).getFieldValue();
            }
        }

        boolean test = false;
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                // jak znajdziemy chociaz jeŁdna roznice to tablice sa rozne
                // System.out.println(board[i][j]+ "   "+ board2[i][j]);
                if (board[i][j] != board2[i][j]) {
                    test = true;
                }
            }
        }
        assertTrue(test);
    }

    @Test
    public void badFillSudoku() {
        sudokuBoard.clearBoard();
        sudokuBoard.set(1,1,1);
        // kolejne set nie zadziala i bedzie zero
        sudokuBoard.set(1,2,1);
        sudokuBoard.set(2,1,1);
        sudokuBoard.set(2,2,1);

        boolean test = false;
        if(sudokuBoard.get(1,2).getFieldValue()==0 && sudokuBoard.get(2,1).getFieldValue()==0 && sudokuBoard.get(2,2).getFieldValue()==0) {
            test=true;
        }
        assertTrue(test);
    }

    @Test
    public void equalsAttempts()
    {
        sudokuBoard.clearBoard();
        sudokuSolver.solve(sudokuBoard);
        boolean test = true;
        SudokuBoard sudokuBoard1 = new SudokuBoard(sudokuBoard);

        if( !sudokuBoard.equals(sudokuBoard1) ) {
            test = false;
        }
        sudokuBoard.clearBoard();
        if( sudokuBoard.equals(sudokuBoard1) ) {
            test = false;
        }
        sudokuBoard1.clearBoard();
        if( !sudokuBoard.equals(sudokuBoard1) ) {
            test = false;
        }

        assertTrue(test);
    }

    @Test
    public void HashCodeAttempts()
    {
        sudokuBoard.clearBoard();
        sudokuSolver.solve(sudokuBoard);
        boolean test = true;
        SudokuBoard sudokuBoard1 = new SudokuBoard(sudokuBoard);

        if( sudokuBoard.hashCode() != sudokuBoard1.hashCode() ) {
            test = false;
        }
        sudokuBoard.clearBoard();
        if( sudokuBoard.hashCode() == sudokuBoard1.hashCode() ) {
            test = false;
        }
        sudokuBoard1.clearBoard();
        if( sudokuBoard.hashCode() != sudokuBoard1.hashCode() ) {
            test = false;
        }

        assertTrue(test);
    }
}
