package mimi.commands;

import mimi.exception.MimiException;
import mimi.ui.Ui;
import mimi.TaskList;
import mimi.storage.Storage;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes this command, prints output via the ui
     * @param tasks the current task list
     * @param ui the ui for printing output
     * @param storage the storage where task list data is saved
     * @throws MimiException if error occurs
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException;

    /**
     * Indicates whether the command will terminate the programme.
     * @return true if command will terminate the programme.
     */
    public boolean isExit() {
        return false;
    }
}
