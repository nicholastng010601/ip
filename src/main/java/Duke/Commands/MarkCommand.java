package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

import java.io.IOException;

public class MarkCommand extends Command{
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;
    protected int index;

    /**
     * ByeCommand constructor
     *
     * @param taskList The existing taskList
     * @param storage The storage that stores the data of the program
     * @param ui The ui interface used to print messages
     * @param index The index of task to update
     */
    public MarkCommand(TaskList taskList, Storage storage, Ui ui, int index) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
        this.index = index;
    }

    /**
     * Method that marks task as done
     *
     */
    @Override
    public void execute() throws DukeException {
        ui.printMarkedDone(taskList, index - 1);
        taskList.markAsDone(index - 1);
        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }
}
