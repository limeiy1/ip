package mimi.tasks;

import mimi.exception.MimiException;

public class Todo extends mimi.tasks.Task {

    public Todo(String description) throws MimiException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
