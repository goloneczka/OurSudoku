package pl.comp;

import java.util.ArrayList;

public class SudokuBox extends SudokuNineFields implements Cloneable {
    SudokuBox(final ArrayList<SudokuField> list) {
        super(list);
    }
}
