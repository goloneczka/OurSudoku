package pl.comp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class Controller  {

    @FXML
    private SplitPane splitPane;
    @FXML
   public MenuItem menuPolski;
    @FXML
    public MenuItem menuAngielski;
    @FXML
    void initialize() {

    }

    private Scene scene;

    public void scene( ResourceBundle bundle)  throws Exception{
        URL url = new File("View/src/main/resources/fxml/menuOne.fxml").toURL();
        Parent root = FXMLLoader.load(url,bundle);
        Scene scene = new Scene(root, 400, 400);
        Main.window.setScene(scene);
        Main.window.show();
    }

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
            SudokuBoard.LOGGER.info("Udalo sie wczytac gra");
       //     throw new FileExeption("Nie mozna wczytac").initCause(new FileNotFoundException("Nie mozna odnalez pliku"));

        } catch (FileExeption fileExeption) {
            SudokuBoard.LOGGER.warning(fileExeption.toString());
            SudokuBoard.LOGGER.warning(fileExeption.getCause().toString());


        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Main.levelGame = new LevelGame(sudokuBoard);
        Main.levelGame.display(true);
    }

    public void bundlePL(ActionEvent actionEvent) throws Exception {
        SudokuBoard.LOGGER.info("Zmiana jezyka na Polski");
        Locale locale = new Locale("pl", "PL");
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.bundle_pl", locale);
        Main.resourceBundle=bundle;
        scene(bundle);
    }

    public void bundleEN(ActionEvent actionEvent)  throws Exception{
        SudokuBoard.LOGGER.info("Zmiana jezyka na Angielski");
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.bundle_en");
        Main.resourceBundle=bundle;
        scene(bundle);

    }

    public void autors(ActionEvent actionEvent) throws IOException {
        SudokuBoard.LOGGER.info("Scena z autorami");
        URL url = new File("View/src/main/resources/fxml/autors.fxml").toURL();
      //  Locale locale = new Locale("pl", "PL");
      //  ResourceBundle bundle = ResourceBundle.getBundle("bundles.bundle_pl", locale);
        Parent root = FXMLLoader.load(url,Main.resourceBundle);
        Scene scene = new Scene(root, 400, 400);
        Main.window.setScene(scene);
        Main.window.show();
    }
}
