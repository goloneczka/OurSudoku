package pl.comp;

import java.io.*;

public class FileSudokuBoardDao implements Dao  {

    private String fileName;

    public FileSudokuBoardDao(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Object read() throws IOException {

        int b = 0, l=0;
        int[] tab = new int[81];
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {

            while (b != -1) {
                b = fileInputStream.read();
                if (b < 58 && b > 47) {
                    tab[l] = b - 48;
                    l++;
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        SudokuBoard sudokuBoard = new SudokuBoard();
        int k=0;
        for (int i=0; i < 9; i++) {
            for (int j=0; j < 9; j++) {
                sudokuBoard.set(i, j, tab[k]);
                k++;
            }
        }
        return sudokuBoard;
    }

    @Override
    public void write(final Object obj) throws FileNotFoundException {

        try (PrintWriter write = new PrintWriter(fileName)) {
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




