package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextField;
import pl.comp.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelGame {
    private String level = " ";



    private int nullPoints =0;
    private SudokuBoard sudokuBoard;
    private SudokuBoard sudokuBoardClone;
    private Random rand = new Random();
    private Button button;
    private List<Tile> tiles;

    LevelGame(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public void setNullPoints(int nullPoints) {
        this.nullPoints = nullPoints;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(450, 500);

        tiles = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tiles.add(new Tile(String.valueOf(sudokuBoard.get(i, j).getFieldValue())));
            }
        }

        for (int i = 0; i < 81; i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(50 * (i % 9));
            tile.setTranslateY(50 * (i / 9));
            root.getChildren().add(tile);
        }
        button = new Button("Check!");
        button.setAlignment(Pos.BOTTOM_CENTER); // czemu nie dziala XD ?
        root.getChildren().add(button);
        return root;
    }


    public void level() {
        boolean flag = false;
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuBoard.set(2, 8, 7);
        sudokuSolver.solve(sudokuBoard);
        //  try {
        sudokuBoardClone = new SudokuBoard(sudokuBoard); // tu powinien byc jebany klon, ale klon jest chujowy
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
        for (int i = 0; i < nullPoints; i++) {
            do {
                int l = rand.nextInt(9);
                int j = rand.nextInt(9);
                if (sudokuBoard.get(l, j).getFieldValue() != 0) {
                    sudokuBoard.set(l, j, 0);
                    flag = false;
                } else
                    flag = true;
            } while (flag);
        }
    }

    public void display() {

        level();
        Stage primaryStage = new Stage();
        primaryStage.setTitle(level);
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        check();

    }

    public void check(){


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final boolean[] flag1 = {true};
                for(int i=0;i<9;i++){
                    for (int j=0;j<9;j++){
                        if(!String.valueOf(sudokuBoardClone.get(i,j).getFieldValue()).equals(tiles.get(i*9+j).text.getText()) )
                        {
                            System.out.println(tiles.get(i*9+j).text.getText());
                            flag1[0] = false;
                        }
                    }
                }
                if(flag1[0])
                    System.out.println("YOU ARE WINNER");
                else
                    System.out.println("You can do this better");
            }
        });
    }

    private class Tile extends StackPane {

        private TextField text = new TextField();
        private boolean flag = false;

        public Tile(String value) {
            Rectangle border = new Rectangle(50, 50);
            border.setStroke(Color.BLACK);
            if (value.equals("0")) {
                text.setText(null);
                border.setFill(Color.BLUE);
                flag = true;
            } else {
                text.setText(value);
                border.setFill(null);
            }
            text.setFont(Font.font(30));
            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            if(!flag)
                text.setEditable(false);

            open(); // dodawanie liczb --> no powinno byc cyfr
        }

        public void open() {
            text.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {

                }
            });
        }
    }

}
