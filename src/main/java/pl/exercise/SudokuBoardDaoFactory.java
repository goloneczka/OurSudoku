package pl.exercise;

public class SudokuBoardDaoFactory {

     public static FileSudokuBoardDao getFileDao(String file)
     {
         return new FileSudokuBoardDao(file);
     }

}
