package mimi.commands;

import mimi.storage.Storage;
import mimi.TaskList;
import mimi.ui.Ui;
import mimi.exception.MimiException;
import mimi.tasks.Todo;

public class AddTodoCommand extends Command {
    private final String taskName;

    public AddTodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        Todo newTodo = new Todo(taskName);
        tasks.add(newTodo);
        ui.showAddedTask(newTodo, tasks.size());
        storage.save(tasks.asList());
    }
}
