package mimi.commands;

import mimi.storage.Storage;
import mimi.TaskList;
import mimi.ui.Ui;
import mimi.exception.MimiException;
import mimi.tasks.Task;

/**
 * Marks the task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an Unmark command.
     * @param taskNumber the list number of the task to be marked
     */
    public UnmarkCommand(int taskNumber) {
        this.index = taskNumber;
    }

    /**
     * Executes the Unmark command.
     * Finds the task at the given task number and marks it as not done.
     * Displays the unmarked task message to user.
     * Saves the updated task list to the data file.
     * @param tasks the current task list
     * @param ui the ui for printing output
     * @param storage the storage where task list data is saved
     * @throws MimiException if list number given is out of range of the list or other errors occur
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        if (index >= tasks.size()) {
            throw new MimiException("Task number is out of list range! Enter a valid task number!");
        }
        Task taskName = tasks.get(index);
        tasks.unmark(index);
        ui.showUnmarked(taskName);
        storage.save(tasks.asList());
    }
}
