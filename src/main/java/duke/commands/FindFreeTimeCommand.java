package duke.commands;

import duke.exception.DukeException;
import duke.ui.Ui;
import duke.utilities.TaskList;

/**
 * A command to find tasks with the keyword specified.
 */
public class FindFreeTimeCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    private String input;

    /**
     * Constructor for the find command.
     *
     * @param taskList The taskList that stores the task.
     * @param ui The UI which will print the output.
     * @param input The keyword for searching.
     */
    public FindFreeTimeCommand(String input, TaskList taskList, Ui ui) throws DukeException {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * The function which executes the find function in UI class.
     *
     */
    @Override
    public String execute() throws DukeException {
        String[] inputList = this.input.split("hour");
        try {
            if (inputList.length == 1) {
                throw new DukeException("Your input is wrong. This is unacceptable");
            }
            String[] findFreeTimeInput = inputList[0].split(" ");
            String hoursInString = findFreeTimeInput[findFreeTimeInput.length - 1];
            int hours = Integer.parseInt(hoursInString);
            return ui.findFreeTime(taskList, hours);
        } catch (DukeException e) {
            return ui.printError(e.getMessage());
        }
    }
}
