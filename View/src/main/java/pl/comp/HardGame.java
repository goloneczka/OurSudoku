package pl.comp;


import pl.comp.SudokuBoard;

public class HardGame extends LevelGame  {

    HardGame(SudokuBoard sudokuBoard) {
        super(sudokuBoard);
        Main.LOGGER.info("Uruchomiono latwa gre");

        setNullPoints(40);
        setLevel("Hard");
    }
}
