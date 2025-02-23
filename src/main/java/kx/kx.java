package kx;

import task.Task;
import misc.kxException;
import misc.Parser;
import misc.Storage;
import misc.Ui;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main class for running the chatbot.
 */
public class kx {

    private static Ui ui = new Ui();
    private ArrayList<Task> taskList = new ArrayList<>();
    private Parser parser;
    /**
     * Point of entry of the application.
     * Runs chatbot, loading stored tasks and processing user input through parser class.
     */
    public kx() {
        try {
            taskList = Storage.loadFile();
        } catch (IOException e) {
            ui.errorMessage(e.getMessage());
            taskList = new ArrayList<>();
        }
        //ui.helloMessage();
        parser = new Parser(ui,taskList);
    }

    public String getResponse(String input) {

        try {
            return parser.userCommand(input);
        } catch (kxException e) {
            return ui.errorMessage(e.getMessage());
        }


    }
}
