package misc;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

/**
 * Handles storage methods for updating and loading tasks from a file.
 */
public class Storage {

    private static final String DIRECTORY = "./data";
    private static final String DATAPATH = "./data/Kx.txt";

    /**
     * Updates the storage file with the current list of tasks.
     *
     * @param tasklist The list of tasks to be saved.
     * @throws IOException Thrown if an error occurs during file writing.
     */
    public static void updateFile(ArrayList<Task> tasklist) throws IOException {
        Path directory = Paths.get(DIRECTORY);
        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }
        try (FileWriter file = new FileWriter(DATAPATH)) {
            for (Task task : tasklist) {
                file.write(task.toFileFormat());
            }
        }
    }

    /**
     * Loads tasks from the saved storage file.
     *
     * @return An ArrayList containing the loaded tasks from the previous session.
     * @throws IOException Thrown if an error occurs during file reading.
     */
    public static ArrayList<Task> loadFile() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(DATAPATH);

        if (!file.exists()) {
            return taskList;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Task task = parseTask(line);
            if (task != null) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    /**
     * Parses a line from the storage file into a Task object.
     *
     * @param line The line describing a task.
     * @return The corresponding Task object or null if parsing fails.
     */
    private static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];

        Task task = switch (type) {
            case "T" -> new Todo(parts[2]);
            case "D" -> new Deadline(parts[2], parts[3]);
            case "E" -> new Event(parts[2], parts[3], parts[4]);
            default -> null;
        };

        if (task != null && parts[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }
}
