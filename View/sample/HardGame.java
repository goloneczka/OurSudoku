package sample;


import pl.comp.SudokuBoard;

public class HardGame extends LevelGame  {

    HardGame(SudokuBoard sudokuBoard) {
        super(sudokuBoard);
        setNullPoints(40);
        setLevel("Hard");
    }
}
