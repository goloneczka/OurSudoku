package pl.comp;

public class SudokuBoardDaoFactory {

     public static FileSudokuBoardDao getFileDao(final String file) {
         return new  FileSudokuBoardDao(file);
     }

}
