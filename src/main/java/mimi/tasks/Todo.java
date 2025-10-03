package mimi.tasks;

/**
 * Represents an todo task.
 */
public class Todo extends mimi.tasks.Task {
    /**
     * Constructs a new Todo with description.
     * @param description the description of the event
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task for output.
     * Includes the task type, completion status, and description.
     * @return string representation for output
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task in the format to save.
     * @return string representation to save
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
