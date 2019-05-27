package pl.comp;

import java.util.ArrayList;
import java.util.List;


public class SudokuNineFields implements Cloneable{
    private final List<SudokuField> list;

    SudokuNineFields(final ArrayList<SudokuField> list) {
        this.list = list;
    }

    @Override
    public SudokuNineFields clone() throws CloneNotSupportedException {
        return (SudokuNineFields) super.clone();
    }

    boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                // sprawdzam czy nie ma drugiego takiego Value w list (nie sprawdzam zer)
                if (list.get(i).getFieldValue() == list.get(j).getFieldValue() && list.get(i).getFieldValue() != 0) {
                    return false;
                }
            }


        }
        return true;
    }
}


