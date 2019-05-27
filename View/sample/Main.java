package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.comp.SudokuBoard;


public class Main extends Application implements EventHandler<ActionEvent> {

    Stage window;
    Scene scene0, scene1, scene2;

    @Override
    public void handle(ActionEvent event) {};

    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;

        Text text = new Text();
        text.setFont(new Font(45));
        text.setText("Greeting!");

        Button button = new Button("level one");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EasyGame easyGame = new EasyGame(new SudokuBoard());
                easyGame.display();
            }
        });

        Button button1 = new Button("level two");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MediumGame mg = new MediumGame(new SudokuBoard());
                mg.display();
            }
        });

        Button button2 = new Button("level three");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HardGame hg = new HardGame(new SudokuBoard());
                hg.display();
            }
        });

        // FORMULARZ GRY
        VBox layout = new VBox(20);
        layout.getChildren().addAll(text, button, button1, button2);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 300, 300);


       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }


}
