package gui;

import kx.Kx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static final String EXIT_COMMAND = "bye";
    private static final double EXIT_DELAY = 2.0; // In seconds

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Kx kaixin;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image kxImage = new Image(this.getClass().getResourceAsStream("/images/Kaixin.png"));

    /**
     * Binds the scroll pane to the dialog container for auto scrolling.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Kx instance and displays a welcome message.
     * @param k The chatbot instance.
     */
    public void setKaixin(Kx k) {
        kaixin = k;
        dialogContainer.getChildren().add(DialogBox.getKaixinDialog(kaixin.getHelloMessage(), kxImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kaixin.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKaixinDialog(response, kxImage)
        );
        userInput.clear();

        if (input.equals(EXIT_COMMAND)) {
            PauseTransition delay = new PauseTransition(Duration.seconds(EXIT_DELAY));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}

