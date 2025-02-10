import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;


public class StoreFile {

    private static final String DIRECTORY = "./data";
    private static final String DATAPATH = "./data/kx.txt";
    public static final String SEPARATOR = "____________________________________________________________";



    public static void updateFile(ArrayList<Task> tasklist) throws IOException {
        Path directory = Paths.get(DIRECTORY);
        //System.out.println("updating file");
        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }
        //System.out.println("directory created");
        try (FileWriter file = new FileWriter(DATAPATH)) {
            for (Task task : tasklist) {
                file.write(task.toFileFormat());
                //System.out.println("task added");
            }
        }
    }

    public static ArrayList<Task> loadFile() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(DATAPATH);

        if (!file.exists()) {
            return taskList;
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Task task = null;
            String[] parts = line.split(" \\| ");
            String type = parts[0];

            if (type.equals("T")) {
                task = new Todo(parts[2]);
            } else if (type.equals("D")) {
                task = new Deadlines(parts[2], parts[3]);
            } else if (type.equals("E")) {
                String[] parts2 = parts[3].split("-");
                task = new Events(parts[2], parts2[0], parts2[1]);
            }

            if (task != null) {
                if (parts[1].equals("1")) {
                    task.markAsDone();
                }
            }

            if (task != null) {
                taskList.add(task);
            }
        }
        return taskList;
    }
}
