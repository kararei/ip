package gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Main method that starts the Kx application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
