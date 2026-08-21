import java.util.Scanner;

public class LeBron {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
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
            } else if(input.equals("list")){// list tasks
                for (int i = 0; i < counter; i++){
                    System.out.println("    " + (i+1) + ") " + list[i]);
                }
                System.out.println("____________________________________________________________");
            } else if(input.startsWith("mark ")){//mark tasks as done
                int taskNumber = Integer.parseInt((input.split(" "))[1]);
                int index = taskNumber - 1;
                list[index].setStatus(true);
                System.out.println("    " +  "Oh yea we're striving for greatness!\n" + list[index]);
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark ")){//unmark tasks
                int taskNumber = Integer.parseInt((input.split(" "))[1]);
                int index = taskNumber - 1;
                list[index].setStatus(false);
                System.out.println("    " +  "Oh nah we undoing stuff now?\n" + list[index]);
                System.out.println("____________________________________________________________");
            } else {
                list[counter] = new Task(input);
                counter++;
                System.out.println("    " + "Added: " + input + "\n" + "____________________________________________________________");
            }
        }

        //Close the program
        scanner.close();
    }
}
