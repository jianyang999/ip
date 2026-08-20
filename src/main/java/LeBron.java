import java.util.Scanner;

public class LeBron {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int counter = 0;

        //Initial message
        String banner = "____________________________________________________________\n" +
                "What's up, I am LeBron.\n" +
                "Can the king assist you in any way?\n" +
                "____________________________________________________________";
        System.out.println(banner);

        //Echo logic
        while (true){
            //Read user input
            String input = scanner.nextLine();

            //If user wants to leave
            if (input.equals("bye")){
                System.out.println("    " + "Peace out fam!\n" + "____________________________________________________________");
                break;
            } else if(input.equals("list")){
                for (int i = 0; i < counter; i++){
                    System.out.println("    " + (i+1) + ") " + list[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                list[counter] = input;
                counter++;
                System.out.println("    " + "Added: " + input + "\n" + "____________________________________________________________");
            }
        }

        //Close the program
        scanner.close();
    }
}
