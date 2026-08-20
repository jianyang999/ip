import java.util.Scanner;

public class LeBron {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Initial message
        String banner = "____________________________________________________________\n" +
                "What's up, I am LeBron.\n" +
                "Can the king assist you in any way?\n" +
                "____________________________________________________________\n";
        System.out.println(banner);

        //Echo logic
        while (true){
            //Read user input
            String input = scanner.nextLine();

            //If user wants to leave
            if (input.equals("bye")){
                System.out.println("    " + "Peace out fam!\n" + "____________________________________________________________\n");
                break;
            } else {
                System.out.println("    " + input + "\n" + "____________________________________________________________\n");
            }
        }

        //Close the program
        scanner.close();
    }
}
