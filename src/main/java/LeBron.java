import java.io.IOException;
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
        Storage storage = new Storage();
        TaskList taskList;
        try {
            taskList = storage.load();
        } catch (IOException e) {
            System.out.println("    Couldn't load your saved grind list, starting fresh.");
            taskList = new TaskList(new ArrayList<>());
        }

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
                        System.out.println(taskList);
                        System.out.println("____________________________________________________________");
                    }
                    case MARK -> {
                        int taskNumber = Integer.parseInt((input.split(" "))[1]);
                        taskList.getTask(taskNumber).setStatus(true);
                        System.out.println("    " +  "Oh yea we're striving for greatness!\n" + "    " + taskList.getTask(taskNumber));
                        System.out.println("____________________________________________________________");
                    }
                    case UNMARK -> {
                        int taskNumber = Integer.parseInt((input.split(" "))[1]);
                        taskList.getTask(taskNumber).setStatus(false);
                        System.out.println("    " +  "Oh nah we undoing stuff now?\n" + "    " + taskList.getTask(taskNumber));
                        System.out.println("____________________________________________________________");
                    }
                    case TODO -> {
                        String description = input.length() > 4 ? input.substring(5).trim() : "";
                        if (description.isEmpty()) {
                            throw new LeBronException("Whatchu need to do?");
                        }
                        taskList.addTask(new Todo(description));
                        System.out.println("    More todo!");
                        System.out.println("    " + taskList.getTask(taskList.size()));
                        System.out.println("    " + taskList.size() + " tasks in your grind list now!");
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
                        taskList.addTask(new Deadline(parts[0], parts[1]));
                        System.out.println("    " + "Deadlines forge kings!");
                        System.out.println("    " + taskList.getTask(taskList.size()));
                        System.out.println("    " + taskList.size() + " tasks in your grind list now!");
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
                        taskList.addTask(new Event(parts[0], parts[1], parts[2]));
                        System.out.println("    " + "Event fit for a king!");
                        System.out.println("    " + taskList.getTask(taskList.size()));
                        System.out.println("    " + taskList.size() + " tasks in your grind list now!");
                        System.out.println("____________________________________________________________");
                    }
                    case DELETE -> {
                        int taskNumber = Integer.parseInt((input.split(" "))[1]);
                        Task removed = taskList.getTask(taskNumber);
                        taskList.deleteTask(taskNumber);
                        System.out.println("    Task been taken care of!");
                        System.out.println("    " + removed);
                        System.out.println("    " + taskList.size() + " tasks left kiddo.");
                        System.out.println("____________________________________________________________");
                    }
                    default -> throw new LeBronException("Whatchu tryna do youngblood?");
                }
                storage.save(taskList);
            } catch (LeBronException e) {
                System.out.println("    " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("    This task don't exist, don't piss me off.");
                System.out.println("____________________________________________________________");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    This task don't exist, don't piss me off.");
                System.out.println("____________________________________________________________");
            } catch (IOException e) {
                System.out.println("    Couldn't save your grind list, kid.");
                System.out.println("____________________________________________________________");
            }
        }
    }
}