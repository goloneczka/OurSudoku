package pl.comp;

import bundles.Bundle_pl_PL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControllerAutors  {
    @FXML
    public Text tekst1;
    @FXML
    public Text tekst2;
    @FXML
    void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.Bundle_pl_PL");
        tekst1.setText(resourceBundle.getString("autor1"));
        tekst2.setText(resourceBundle.getString("autor2"));
    }

    public void back(ActionEvent actionEvent) throws IOException {
        SudokuBoard.LOGGER.info("Powrot do glownego menu");
        URL url = new File("View/src/main/resources/fxml/menuOne.fxml").toURL();
       // Locale locale = new Locale("pl", "PL");
        //ResourceBundle bundle = ResourceBundle.getBundle("bundles.bundle_pl", locale);
        Parent root = FXMLLoader.load(url,Main.resourceBundle);
        Scene scene = new Scene(root, 400, 400);
        Main.window.setScene(scene);
        Main.window.show();
    }
}
