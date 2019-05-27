package sample;


import pl.comp.SudokuBoard;

public class EasyGame extends LevelGame  {

    EasyGame(SudokuBoard sudokuBoard) {
        super(sudokuBoard);
        setNullPoints(10);
        setLevel("Easy");
    }
}
