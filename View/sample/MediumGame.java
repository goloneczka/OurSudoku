package sample;

import pl.comp.SudokuBoard;

public class MediumGame extends LevelGame  {

    MediumGame(SudokuBoard sudokuBoard) {
        super(sudokuBoard);
        setNullPoints(25);
        setLevel("Medium");
    }
}
