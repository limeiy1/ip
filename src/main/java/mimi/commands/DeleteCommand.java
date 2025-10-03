package mimi.commands;

import mimi.storage.Storage;
import mimi.TaskList;
import mimi.ui.Ui;
import mimi.exception.MimiException;
import mimi.tasks.Task;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a Delete command.
     * @param taskNumber the list number of the task to be deleted
     */
    public DeleteCommand(int taskNumber) {
        this.index = taskNumber;
    }

    /**
     * Executes the Delete command.
     * Finds the task at the given task number and delete it from the list.
     * Displays the deleted task message to user.
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
        tasks.delete(index);
        ui.showDeletedTask(taskName, tasks.size());
        storage.save(tasks.asList());
    }
}
