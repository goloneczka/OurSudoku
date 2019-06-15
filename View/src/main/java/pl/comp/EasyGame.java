package pl.comp;


import pl.comp.SudokuBoard;

public class EasyGame extends LevelGame  {

    EasyGame(SudokuBoard sudokuBoard) {


        super(sudokuBoard);
        SudokuBoard.LOGGER.info("Uruchomiono latwa gre");

        setNullPoints(10);
        setLevel("Easy");
    }
}
