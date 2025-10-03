package mimi.commands;

import mimi.storage.Storage;
import mimi.TaskList;
import mimi.ui.Ui;
import mimi.exception.MimiException;
import mimi.tasks.Deadline;

/**
 * Adds a deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    private final String taskName;
    private final String deadlineDate;

    /**
     * Constructs an AddDeadline command.
     * @param taskName name of the deadline task
     * @param deadlineDate the deadline date of the task
     */
    public AddDeadlineCommand(String taskName, String deadlineDate) {
        this.taskName = taskName;
        this.deadlineDate = deadlineDate;
    }

    /**
     * Executes the AddDeadline command.
     * Constructs a new Deadline task, adds it to the task list.
     * Displays the added deadline message to user.
     * Saves the updated task list to the data file.
     * @param tasks the current task list
     * @param ui the ui for printing output
     * @param storage the storage where task list data is saved
     * @throws MimiException if error occurs
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        Deadline newDeadline = new Deadline(taskName, deadlineDate);
        tasks.add(newDeadline);
        ui.showAddedTask(newDeadline, tasks.size());
        storage.save(tasks.asList());
    }
}
