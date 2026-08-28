package lebron;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import lebron.command.Command;
import lebron.exception.LeBronException;
import lebron.parser.Parser;
import lebron.storage.Storage;
import lebron.task.TaskList;
import lebron.ui.Ui;

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

            try {
                Command command = Parser.parse(input);
                command.execute(taskList, ui);
                if (command.isExit()) {
                    return;
                }
                storage.save(taskList);
            } catch (LeBronException e) {
                ui.showMessage(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showMessage("This task don't exist, don't play with me!");
            } catch (IndexOutOfBoundsException e) {
                ui.showMessage("This task don't exist, don't play with me!");
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
