import java.util.Scanner;

public class kx {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("  Hello! I'm kx, the kai xin bot!\n  What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("  Bye bye and hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println("  " + input);
            System.out.println("____________________________________________________________");
        }
        scanner.close();

    }
}
