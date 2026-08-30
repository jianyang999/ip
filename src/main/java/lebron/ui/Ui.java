package lebron.ui;

import java.util.Scanner;

import lebron.task.Task;
import lebron.task.TaskList;

/**
 * Ui for user interactions
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructs a Ui with a scanner.
     */
    public Ui(){
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays LeBron's initial banner message.
     */
    public void showBanner() {
        System.out.println( "____________________________________________________________\n" +
                "What's up, I am LeBron.\n" +
                "Can the king assist you in any way?\n" +
                "____________________________________________________________");
    }

    /**
     * Displays message.
     *
     * @param message to be delivered.
     */
    public void showMessage(String message) {
        System.out.println("    " + message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays tasks in TaskList.
     *
     * @param taskList a list of tasks.
     */
    public void showTaskList(TaskList taskList) {
        System.out.println(taskList.toString());
        System.out.println("____________________________________________________________");
    }

    /**
     * Scans for user input.
     *
     * @return String containing user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays confirmation that a Todo was added.
     *
     * @param task The Todo that was added.
     * @param taskListSize The number of tasks in the list after adding.
     */
    public void showTodoAdded(Task task, int taskListSize) {
        System.out.println("    More todo!");
        System.out.println("    " + task.toString());
        System.out.println("    " + taskListSize + " tasks left to grind now!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays confirmation that a Deadline was added.
     *
     * @param task The Deadline that was added.
     * @param taskListSize The number of tasks in the list after adding.
     */
    public void showDeadlineAdded(Task task, int taskListSize) {
        System.out.println("    Don't put these off!");
        System.out.println("    " + task.toString());
        System.out.println("    " + taskListSize + " tasks left to grind now!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays confirmation that an Event was added.
     *
     * @param task The Event that was added.
     * @param taskListSize The number of tasks in the list after adding.
     */
    public void showEventAdded(Task task, int taskListSize) {
        System.out.println("    Go have some fun young blood!");
        System.out.println("    " + task.toString());
        System.out.println("    " + taskListSize + " tasks left to grind now!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays confirmation that a task was deleted.
     *
     * @param task The task that was deleted.
     * @param taskListSize The number of tasks remaining in the list.
     */
    public void showTaskDeleted(Task task, int taskListSize) {
        System.out.println("    Task been taken care of!");
        System.out.println("    " + task.toString());
        System.out.println("    " + taskListSize + " tasks left to grind now!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays confirmation that a task was marked as done.
     *
     * @param task The task that was marked.
     */
    public void showTaskMarked(Task task) {
        System.out.println("    Oh yea we're striving for greatness!");
        System.out.println("    " + task.toString());
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays confirmation that a task was marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("    Oh nah we undoing stuff now?");
        System.out.println("    " + task.toString());
        System.out.println("____________________________________________________________");
    }
}
