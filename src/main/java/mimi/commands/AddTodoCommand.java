package mimi.commands;

import mimi.storage.Storage;
import mimi.TaskList;
import mimi.ui.Ui;
import mimi.exception.MimiException;
import mimi.tasks.Todo;

/**
 * Adds a Todo task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String taskName;

    /**
     * Constructs an AddTodo command.
     * @param taskName name of the todo task
     */
    public AddTodoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     *  Executes the AddTodo command.
     *  Constructs a new todo task, adds it to the task list.
     *  Displays the added todo message to user.
     *  Saves the updated task list to the data file.
     * @param tasks the current task list
     * @param ui the ui for printing output
     * @param storage the storage where task list data is saved
     * @throws MimiException if error occurs
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        Todo newTodo = new Todo(taskName);
        tasks.add(newTodo);
        ui.showAddedTask(newTodo, tasks.size());
        storage.save(tasks.asList());
    }
}
