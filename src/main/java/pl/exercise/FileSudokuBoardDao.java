package pl.exercise;

import java.io.*;

public class FileSudokuBoardDao implements Dao  {

    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Object read() throws IOException {

        int b = 0,l=0, tab[] = new int[81];
        try (FileInputStream fileInputStream = new FileInputStream(fileName) ) {

            while (b != -1) {
                b = fileInputStream.read();
                if (b < 57 && b > 47) {
                    tab[l] = b - 48;
                    l++;
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        SudokuBoard sudokuBoard = new SudokuBoard();
        for(int i=0;i<9;i++)
        {
            for (int j=0;j<9;j++)
            {
                sudokuBoard.set(i, j, tab[i*9+j] );
            }
        }
        return sudokuBoard;
    }

    @Override
    public void write(Object obj) throws FileNotFoundException {

        try(PrintWriter write = new PrintWriter(fileName) ) {
            write.print(obj);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void finalize() throws Throwable {

        super.finalize();
        }
    }




