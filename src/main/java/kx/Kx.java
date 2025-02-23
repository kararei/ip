package kx;

import task.Task;
import misc.kxException;
import misc.Parser;
import misc.Storage;
import misc.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class for running the chatbot.
 */
public class Kx {

    private static Ui ui = new Ui();
    private ArrayList<Task> taskList = new ArrayList<>();
    private Parser parser;
    private String helloMessage;

    /**
     * Initialises the kaixin chatbot.
     * Loads stored tasks and processes user input through Parser class.
     */
    public Kx() {
        this.taskList = loadStorage();
        this.helloMessage = ui.helloMessage();
        this.parser = new Parser(ui,taskList);
    }

    /**
     * Loads tasks from storage, handling any errors gracefully.
     * @return The list of stored tasks or if there is an error, an empty list.
     */
    private ArrayList<Task> loadStorage() {
        try {
            return Storage.loadFile();
        } catch (IOException e) {
            ui.errorMessage(e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Processes user input and returns a response.
     *
     * @param input The user input string.
     * @return Kx's response.
     */
    public String getResponse(String input) {
        try {
            return parser.userCommand(input);
        } catch (kxException e) {
            return ui.errorMessage(e.getMessage());
        }
    }

    /**
     * Retrieves and returns the chatbot's greeting message.
     */
    public String getHelloMessage() {
        return helloMessage;
    }
}
