import java.util.Scanner;
import java.util.ArrayList;

public class LeBron {

    private static CommandType getCommandType(String input) {
        String firstWord = input.split(" ")[0];
        switch (firstWord) {
            case "bye":
                return CommandType.BYE;
            case "list":
                return CommandType.LIST;
            case "mark":
                return CommandType.MARK;
            case "unmark":
                return CommandType.UNMARK;
            case "todo":
                return CommandType.TODO;
            case "deadline":
                return CommandType.DEADLINE;
            case "event":
                return CommandType.EVENT;
            case "delete":
                return CommandType.DELETE;
            default:
                return CommandType.UNKNOWN;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        //Initial message
        String banner = "____________________________________________________________\n" +
                "What's up, I am LeBron.\n" +
                "Can the king assist you in any way?\n" +
                "____________________________________________________________";
        System.out.println(banner);

        while (true){
            //Read user input
            String input = scanner.nextLine();
            CommandType commandType = getCommandType(input);

            try {
                switch (commandType) {
                    case BYE -> {
                        System.out.println("    " + "Peace out fam!\n" + "____________________________________________________________");
                        scanner.close();
                        return;
                    }
                    case LIST -> {
                        for (int i = 0; i < list.size(); i++){
                            System.out.println("    " + (i+1) + ") " + list.get(i));
                        }
                        System.out.println("____________________________________________________________");
                    }
                    case MARK -> {
                        int taskNumber = Integer.parseInt((input.split(" "))[1]);
                        int index = taskNumber - 1;
                        list.get(index).setStatus(true);
                        System.out.println("    " +  "Oh yea we're striving for greatness!\n" + "    " + list.get(index));
                        System.out.println("____________________________________________________________");
                    }
                    case UNMARK -> {
                        int taskNumber = Integer.parseInt((input.split(" "))[1]);
                        int index = taskNumber - 1;
                        list.get(index).setStatus(false);
                        System.out.println("    " +  "Oh nah we undoing stuff now?\n" + "    " + list.get(index));
                        System.out.println("____________________________________________________________");
                    }
                    case TODO -> {
                        String description = input.length() > 4 ? input.substring(5).trim() : "";
                        if (description.isEmpty()) {
                            throw new LeBronException("Whatchu need to do?");
                        }
                        list.add(new Todo(description));
                        System.out.println("    More todo!");
                        System.out.println("    " + list.get(list.size() - 1));
                        System.out.println("    " + list.size() + " tasks in your grind list now!");
                        System.out.println("____________________________________________________________");
                    }
                    case DEADLINE -> {
                        String fullDesc = input.length() > 8 ? input.substring(9).trim() : "";
                        if (fullDesc.isEmpty()) {
                            throw new LeBronException("Yo specify your deadline!");
                        }
                        if (!fullDesc.contains(" by ")) {
                            throw new LeBronException("Do not test me kid, specify your due date!");
                        }
                        String[] parts = fullDesc.split(" by ");
                        list.add(new Deadline(parts[0], parts[1]));
                        System.out.println("    " + "Deadlines forge kings!");
                        System.out.println("    " + list.get(list.size() - 1));
                        System.out.println("    " + list.size() + " tasks in your grind list now!");
                        System.out.println("____________________________________________________________");
                    }
                    case EVENT -> {
                        String fullDesc = input.length() > 5 ? input.substring(6).trim() : "";
                        if (fullDesc.isEmpty()) {
                            throw new LeBronException("What event you tryna do?");
                        }
                        if (!fullDesc.contains(" from ") || !fullDesc.contains(" to ")) {
                            throw new LeBronException("Please tell me when it starts and ends little one.");
                        }
                        String[] parts = fullDesc.split(" from | to ");
                        list.add(new Event(parts[0], parts[1], parts[2]));
                        System.out.println("    " + "Event fit for a king!");
                        System.out.println("    " + list.get(list.size() - 1));
                        System.out.println("    " + list.size() + " tasks in your grind list now!");
                        System.out.println("____________________________________________________________");
                    }
                    case DELETE -> {
                        int taskNumber = Integer.parseInt((input.split(" "))[1]);
                        int index = taskNumber - 1;
                        Task removed = list.get(index);
                        list.remove(index);
                        System.out.println("    Task been taken care of!");
                        System.out.println("    " + removed);
                        System.out.println("    " + list.size() + " tasks left kiddo.");
                        System.out.println("____________________________________________________________");
                    }
                    default -> throw new LeBronException("Whatchu tryna do youngblood?");
                }
            } catch (LeBronException e) {
                System.out.println("    " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("    This task don't exist, don't piss me off.");
                System.out.println("____________________________________________________________");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    This task don't exist, don't piss me off.");
                System.out.println("____________________________________________________________");
            }
        }
    }
}