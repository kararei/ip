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

    /**
     * Point of entry of the application.
     * @param args Command line arguments.
     * @throws kxException Thrown if an error occurs during execution.
     */
    public static void main(String[] args) throws kxException {
        new kx().run();
    }

    /**
     * Runs chatbot, loading stored tasks and processing user input through parser class.
     * @throws kxException Thorwn if an error occurs during execution.
     */
    public void run() throws kxException {
        ArrayList<Task> taskList;
        try {
            taskList = Storage.loadFile();
        } catch (IOException e) {
            ui.errorMessage(e.getMessage());
            taskList = new ArrayList<>();
        }

        ui.helloMessage();

        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(ui,taskList);


        while (true) {
            String input = scanner.nextLine();
            try {
                parser.userCommand(input);
            } catch (kxException e) {
                ui.errorMessage(e.getMessage());
            }
        }

    }
}
