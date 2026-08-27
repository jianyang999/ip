import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class LeBron {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public LeBron() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = storage.load();
        } catch (IOException e) {
            ui.showMessage("Couldn't load your saved grind list, starting fresh.");
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.showBanner();
        while (true) {
            String input = ui.readCommand();
            CommandType commandType = Parser.getCommandType(input);

            try {
                switch (commandType) {
                    case BYE -> {
                        ui.showMessage("Peace out see ya later!");
                        return;
                    }
                    case LIST -> {
                        ui.showTaskList(taskList);
                    }
                    case TODO -> {
                        String description = Parser.parseTodoDescription(input);
                        Task task = new Todo(description);
                        taskList.addTask(task);
                        ui.showTodoAdded(task, taskList.size());
                    }
                    case DEADLINE -> {
                        Parser.DeadlineArgs args = Parser.parseDeadlineArgs(input);
                        Task task = new Deadline(args.description(), args.by());
                        taskList.addTask(task);
                        ui.showDeadlineAdded(task, taskList.size());
                    }
                    case EVENT -> {
                        Parser.EventArgs args = Parser.parseEventArgs(input);
                        Task task = new Event(args.description(), args.start(), args.end());
                        taskList.addTask(task);
                        ui.showEventAdded(task, taskList.size());
                    }
                    case MARK -> {
                        Task task = taskList.getTask(Parser.parseTaskNumber(input));
                        task.setStatus(true);
                        ui.showTaskMarked(task);
                    }
                    case UNMARK -> {
                        Task task = taskList.getTask(Parser.parseTaskNumber(input));
                        task.setStatus(false);
                        ui.showTaskUnmarked(task);
                    }
                    case DELETE -> {
                        Task task = taskList.getTask(Parser.parseTaskNumber(input));
                        taskList.deleteTask(Parser.parseTaskNumber(input));
                        ui.showTaskDeleted(task, taskList.size());
                    }
                    default -> throw new LeBronException("Whatchu tryna do youngblood?");
                }
                storage.save(taskList);
            } catch (LeBronException e) {
                ui.showMessage(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showMessage("This task don't exist, don't play with me!.");
            } catch (IndexOutOfBoundsException e) {
                ui.showMessage("This task don't exist, don't play with me!.");
            } catch (IOException e) {
                ui.showMessage("Couldn't save your grind list.");
            } catch (DateTimeParseException e) {
                ui.showMessage("That date don't look right, use yyyy-MM-dd HHmm man, e.g. 2019-10-15 1800.");
            }
        }
    }

    public static void main(String[] args) {
        new LeBron().run();
    }
}