import java.util.Scanner;
import java.util.ArrayList;

public class kx {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("  Hello! I'm kx, the kai xin bot!\n  What can I do for you?");
        System.out.println("____________________________________________________________");

        ArrayList<String> tasks = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("  Bye bye and hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            } else if ( input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } 

            tasks.add(input);
            System.out.println("____________________________________________________________");
            System.out.println("  added: " + input);
            System.out.println("____________________________________________________________");
            
        }
        scanner.close();

    }
}
