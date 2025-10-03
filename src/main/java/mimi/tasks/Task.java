package mimi.tasks;

/**
 * Represents an abstract class with description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with description and not marked as done.
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a completed task with "X".
     * @return "X" if done, blank space if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task that indicates its completion status and description.
     * Additional information to be implemented by subclasses.
     * @return a string representation of the task
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String toSaveFormat();
}
