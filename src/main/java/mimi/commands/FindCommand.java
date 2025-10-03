package mimi.commands;

import mimi.TaskList;
import mimi.exception.MimiException;
import mimi.storage.Storage;
import mimi.tasks.Task;
import mimi.ui.Ui;

import java.util.ArrayList;

/**
 * Finds a task in the task list.
 */
public class FindCommand extends Command {
    public String keyword;

    /**
     * Constructs a Find command.
     * @param keyword to find in the list
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the Find command.
     * Finds the tasks which contain the keyword.
     * Displays the found task message to user.
     * Saves the updated task list to the data file.
     * @param tasks the current task list
     * @param ui the ui for printing output
     * @param storage the storage where task list data is saved
     * @throws MimiException if error occurs
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        ArrayList<Task> matches = tasks.find(keyword);
        ui.showFoundTasks(matches);
        storage.save(tasks.asList());
    }
}
