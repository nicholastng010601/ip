package duke.commands;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.tasks.Deadlines;
import duke.ui.Ui;
import duke.utilities.Storage;
import duke.utilities.TaskList;

/**
 * A command to create a deadline task.
 */
public class DeadlineCommand extends Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;
    protected String input;

    /**
     * Deadline command constructor to create a deadline task.
     *
     * @param input The input for the command.
     * @param taskList The taskList to store the task.
     * @param storage The storage system to store the list.
     * @param ui The ui to print the commands.
     */
    public DeadlineCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Execute the command itself.
     *
     * @throws DukeException When there is an error saving.
     */
    @Override
    public String execute() throws DukeException {
        String task = "deadline";

        try {
            Deadlines newTask = new Deadlines(getDescription(task, input), getEndDate(task, input));
            taskList.addTask(newTask);
            storage.save(taskList);
            return ui.printAddTask(taskList, newTask);
        } catch (IOException e) {
            return ui.printError(e.getMessage());
        } catch (DateTimeParseException e) {
            return ui.printInvalidTimeError();
        }
    }
}
