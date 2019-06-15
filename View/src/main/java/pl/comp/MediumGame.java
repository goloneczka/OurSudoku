package pl.comp;

import pl.comp.SudokuBoard;

public class MediumGame extends LevelGame  {

    MediumGame(SudokuBoard sudokuBoard) {
        super(sudokuBoard);
        SudokuBoard.LOGGER.info("Uruchomiono latwa gre");

        setNullPoints(25);
        setLevel("Medium");
    }
}
