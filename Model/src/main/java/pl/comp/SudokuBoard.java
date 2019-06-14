package pl.comp;


import org.apache.commons.collections.list.FixedSizeList;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.*;

import static org.apache.commons.lang3.builder.ToStringStyle.SIMPLE_STYLE;


public class SudokuBoard implements Cloneable {


    private static final int N = 9;
    private List<List<SudokuField>> board;


    public SudokuBoard() {
        //generuje liste
        // Arrays.asList(new [100]);



        board = FixedSizeList.decorate(Arrays.asList(new List[N]));

        for (int i = 0; i < N; i++) {
            board.set(i, Arrays.asList(new SudokuField[N]));
        }
        // dodaje puste pola
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board.get(i).set(j, new SudokuField(i, j, 0));
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SudokuBoard sudokuBoard = null ;
        try {
            sudokuBoard = (SudokuBoard) super.clone();
            sudokuBoard.board=FixedSizeList.decorate(Arrays.asList(new List[N]));
            for (int i = 0; i < N; i++) {
                sudokuBoard.board.set(i, Arrays.asList(new SudokuField[N]));
            }
            // dodaje puste pola
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sudokuBoard.board.get(i).set(j, this.board.get(i).get(j).clone());
                }
            }
        } catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
        return sudokuBoard;
    }



    // Konstruktor se kopiujacy
    public SudokuBoard(final SudokuBoard sudokuBoard) {
        board = FixedSizeList.decorate(Arrays.asList(new List[N]));

        for (int i = 0; i < N; i++) {
            board.set(i, Arrays.asList(new SudokuField[N]));
        }
        // dodaje puste pola
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (sudokuBoard.get(i, j).isConstPoint()) {
                    board.get(i).set(j, new SudokuField(i, j, sudokuBoard.get(i, j).getFieldValue(), true));
                } else {
                    board.get(i).set(j, new SudokuField(i, j, sudokuBoard.get(i, j).getFieldValue()));
                }
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
            for (int j = 0; j < 3; j++) {
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
        ArrayList<SudokuField> list = new ArrayList<SudokuField>(N);
        for (int i = 0; i < N; i++) {
            list.add(i, get(y, i));
        }
        return new SudokuRow(list);
    }

    public SudokuColumn getColumn(int x) {
        ArrayList<SudokuField> list = new ArrayList<SudokuField>(N);
        for (int i = 0; i < N; i++) {
            list.add(i, get(i, x));
        }
        return new SudokuColumn(list);
    }

    public SudokuBox getBox(int x, int y) {
        ArrayList<SudokuField> list = new ArrayList<SudokuField>(N);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                list.add(i, get((x / 3) * 3 + i, (y / 3) * 3 + j));
            }
        }
        return new SudokuBox(list);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SIMPLE_STYLE).
                append("Pola Sudoku: ", board).toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).
                append(board).toHashCode();

    }
}

