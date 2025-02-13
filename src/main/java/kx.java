import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class kx {

    private static Ui ui = new Ui();

    public static void main(String[] args) throws kxException {

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
            try{
                parser.userCommand(input);
            } catch (kxException e) {
                ui.errorMessage(e.getMessage());
            }
        }

    }
}
