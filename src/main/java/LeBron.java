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

        while (true){
            //Read user input
            String input = scanner.nextLine();

            try {
                if (input.equals("bye")){
                    System.out.println("    " + "Peace out fam!\n" + "____________________________________________________________");
                    break;
                } else if(input.equals("list")){
                    for (int i = 0; i < counter; i++){
                        System.out.println("    " + (i+1) + ") " + list[i]);
                    }
                    System.out.println("____________________________________________________________");
                } else if(input.startsWith("mark ")){
                    int taskNumber = Integer.parseInt((input.split(" "))[1]);
                    int index = taskNumber - 1;
                    list[index].setStatus(true);
                    System.out.println("    " +  "Oh yea we're striving for greatness!\n" + list[index]);
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("unmark ")){
                    int taskNumber = Integer.parseInt((input.split(" "))[1]);
                    int index = taskNumber - 1;
                    list[index].setStatus(false);
                    System.out.println("    " +  "Oh nah we undoing stuff now?\n" + list[index]);
                    System.out.println("____________________________________________________________");
                } else if(input.equals("todo") || input.startsWith("todo ")){
                    String description = input.length() > 4 ? input.substring(5).trim() : "";
                    if (description.isEmpty()) {
                        throw new LeBronException("Whatchu need to do?");
                    }
                    list[counter] = new Todo(description);
                    counter++;
                    System.out.println("    More todo!");
                    System.out.println(list[counter-1]);
                    System.out.println(counter + " tasks in your grind list now!");
                    System.out.println("____________________________________________________________");
                } else if(input.equals("deadline") || input.startsWith("deadline ")){
                    String fullDesc = input.length() > 8 ? input.substring(9).trim() : "";
                    if (fullDesc.isEmpty()) {
                        throw new LeBronException("Yo specify your deadline!");
                    }
                    if (!fullDesc.contains(" by ")) {
                        throw new LeBronException("Do not test me kid, specify your due date!");
                    }
                    String[] parts = fullDesc.split(" by ");
                    String description = parts[0];
                    String by = parts[1];
                    list[counter] = new Deadline(description, by);
                    counter++;
                    System.out.println("    " + "Deadlines forge kings!");
                    System.out.println(list[counter-1]);
                    System.out.println(counter + " tasks in your grind list now!");
                    System.out.println("____________________________________________________________");
                } else if(input.equals("event") || input.startsWith("event ")){
                    String fullDesc = input.length() > 5 ? input.substring(6).trim() : "";
                    if (fullDesc.isEmpty()) {
                        throw new LeBronException("What event you tryna do?");
                    }
                    if (!fullDesc.contains(" from ") || !fullDesc.contains(" to ")) {
                        throw new LeBronException("Please tell me when it starts and ends little one.");
                    }
                    String[] parts = fullDesc.split(" from | to ");
                    String description = parts[0];
                    String start = parts[1];
                    String end = parts[2];
                    list[counter] = new Event(description, start, end);
                    counter++;
                    System.out.println("    " + "Event fit for a king!");
                    System.out.println(list[counter-1]);
                    System.out.println(counter + " tasks in your grind list now!");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new LeBronException("Whatchu tryna do youngblood?");
                }
            } catch (LeBronException e) {
                System.out.println("    " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("    This task don't exist, don't piss me off.");
                System.out.println("____________________________________________________________");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("    This task don't exist, don't piss me off.");
                System.out.println("____________________________________________________________");
            } catch (NullPointerException e) {
                System.out.println("    That stuff ain't there kid.");
                System.out.println("____________________________________________________________");
            }
        }

        //Close the program
        scanner.close();
    }
}