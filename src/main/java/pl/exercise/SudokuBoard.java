package pl.exercise;



import org.apache.commons.collections.list.FixedSizeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SudokuBoard {

    private static final int N = 9;



    private List<List<SudokuField>> board;



    public SudokuBoard() {
        //generuje liste
       // Arrays.asList(new [100]);
        board = FixedSizeList.decorate(Arrays.asList(new List[N]));

        // this.board = Arrays.asList(new List[N]);
      //  board = new ArrayList<>(N);
        //dodaje listy do listy
        for (int i=0; i < N; i++) {
            board.set(i, Arrays.asList(new SudokuField[N]));
        }
        // dodaje puste pola
        for (int i=0; i < N; i++) {
            for (int j=0; j < N; j++) {
                board.get(i).set(j, new SudokuField(i, j, 0));
            }
        }
    }

    // ChceckBoard ktore sprawdza cale  sudoku
    private boolean checkBoard() {
        for (int i = 0; i < N; i++) {
            if (!getRow(i).verify() || !getColumn(i).verify()) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j=0; j < 3; j++) {
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    public SudokuField get(int i, int j) {
        return board.get(i).get(j);
    }

    public void set(int i, int j, int k) {
        SudokuField punkt = new SudokuField(i, j, k, true);
        board.get(punkt.getX()).set(punkt.getY(), punkt);
        // sprawdzam cale sudoku i jak nie pasuje to zeruje ten punkt
        if (!checkBoard()) {
            punkt.setFieldValue(0);
            board.get(punkt.getX()).set(punkt.getY(), punkt);
        }
        return;
    }

    // nie usuwa stalych sudokuField!
    public void clearBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!board.get(i).get(j).isConstPoint()) {
                    board.get(i).get(j).setFieldValue(0);
                }
            }
        }
    }

    public List<List<SudokuField>> getBoard() {
        return board;
    }

    public SudokuRow getRow(int y) {
        ArrayList<SudokuField> list= new ArrayList<SudokuField>(N);
        for (int i = 0; i < N; i++) {
            list.add(i, get(y, i));
        }
        return new SudokuRow(list);
    }
    public SudokuColumn getColumn(int x) {
        ArrayList<SudokuField> list= new ArrayList<SudokuField>(N);
        for (int i = 0; i < N; i++) {
            list.add(i, get(i, x));
        }
        return new SudokuColumn(list);
    }
    public SudokuBox getBox(int x, int y) {
        ArrayList<SudokuField> list= new ArrayList<SudokuField>(N);
        for (int i=0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                list.add(i, get((x / 3) * 3 + i, (y / 3) * 3 + j));
            }
        }
        return new SudokuBox(list);
    }




}

