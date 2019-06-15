package pl.comp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.*;


public class Main extends Application implements EventHandler<ActionEvent> {

    static Stage window;
    static pl.comp.LevelGame levelGame;
    pl.comp.Controller controller = new pl.comp.Controller();
    public static ResourceBundle resourceBundle;

    /*public static Logger LOGGER;
  //  static Handler fileHandler  = null;

    static {
        System.setProperty("java.util.logging.config.file",
                "/home/przemzan/Desktop/Programowanie Komponentowe/OurSudoku/View/src/main/resources/logging/logging.properties");

        //must initialize loggers after setting above property
        LOGGER = Logger.getLogger(Main.class.getName());
    }*/

   /* private static final Logger logger =
            Logger.getLogger(FileSudokuBoardDao.class.getName());*/

    @Override
    public void handle(ActionEvent event) {

    };

    @Override
    public void start(Stage primaryStage) throws Exception{
       // final Locale locale = new Locale("pl", "PL");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.bundle_en");
        this.resourceBundle=resourceBundle;
        window = primaryStage;
        URL url = new File("View/src/main/resources/fxml/menuOne.fxml").toURL();
        Parent root = FXMLLoader.load(url,resourceBundle);


        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

       // Controller controller = new Controller();


        // wywoluje sie po wcisieciu x w menu
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if(Main.levelGame!=null)
                Main.levelGame.schowaj();
                SudokuBoard.LOGGER.info("Aplikajcja jest zamykana");


            }
        });

    }

    public static void main(String[] args) {




     /*   try {
            fileHandler  = new FileHandler("./sudokuView.log");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

   //     LOGGER.addHandler(fileHandler);



        SudokuBoard.LOGGER.info("Uruchomiono Aplikacje");








        Application.launch(Main.class, args);




    }

}
