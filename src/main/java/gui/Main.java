package gui;

import kx.Kx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main entry point for launching the Kx GUI application.
 * The class displays the main window of the chatbot.
 * Uses JavaFX and FXML for the user interface
 */
public class Main extends Application {
    private static final String MAIN_WINDOW = "/view/MainWindow.fxml";
    private Kx kaixin = new Kx();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW));
            AnchorPane rootLayout = fxmlLoader.load();

            Scene scene = new Scene(rootLayout);
            stage.setTitle("Kx");
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();
            controller.setKaixin(kaixin); // Inject chatbot instance

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
