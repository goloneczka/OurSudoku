package pl.comp;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class Controller  {


 //   @FXML
  //  private Button ButtonlevelOne =  ;

    public void levelOne(ActionEvent actionEvent) {
        if(Main.levelGame!=null)
            Main.levelGame.schowaj();
        Main.levelGame = new EasyGame(new SudokuBoard());
        Main.levelGame.display(false);

    }

    public void levelTwo(ActionEvent actionEvent) {
        if(Main.levelGame!=null)
            Main.levelGame.schowaj();
        Main.levelGame = new MediumGame(new SudokuBoard());
        Main.levelGame.display(false);
    }

    public void levelThree(ActionEvent actionEvent) {
        if(Main.levelGame!=null)
            Main.levelGame.schowaj();
        Main.levelGame = new HardGame(new SudokuBoard());
        Main.levelGame.display(false);
    }

    public void loadGame(ActionEvent actionEvent) {
        if(Main.levelGame!=null)
            Main.levelGame.schowaj();
        FileChooser fileChooser = new FileChooser();
        Stage primaryStage = new Stage();
        fileChooser.setTitle("Load file");
        File file = fileChooser.showOpenDialog(primaryStage);
        Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao(file.getName());
        SudokuBoard sudokuBoard=new SudokuBoard();

        try {
            sudokuBoard=dao.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.levelGame = new LevelGame(sudokuBoard);
        Main.levelGame.display(true);
    }
}
