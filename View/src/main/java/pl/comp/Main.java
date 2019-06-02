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
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application implements EventHandler<ActionEvent> {

    Stage window;





    static LevelGame levelGame;

    @Override
    public void handle(ActionEvent event) {

    };

    @Override
    public void start(Stage primaryStage) throws Exception{
       // final Locale locale = new Locale("pl", "PL");
       // Locale.setDefault(locale);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.bundle");
        System.out.println(resourceBundle.getObject("easyLevel"));


        window = primaryStage;

        URL url = new File("/home/przemzan/Desktop/Programowanie Komponentowe/OurSudoku/View/src/main/resources/fxml/menuOne.fxml").toURL();
        Parent root = FXMLLoader.load(url,resourceBundle);



       // Parent root = FXMLLoader.load(getClass().getResource("menuOne.fxml"));
        Scene scene = new Scene(root, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
        // wywoluje sie po wcisieciu x w menu
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if(Main.levelGame!=null)
                Main.levelGame.schowaj();
                System.out.println("Stage is closing");
            }
        });


    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }


}
