package mimi.tasks;

/**
 * Represents a deadline task with deadline date.
 */
public class Deadline extends Task {

    private final String by;

    /**
     * Constructs a new Deadline with description and deadline date.
     * @param description the description of the deadline
     * @param by the deadline date
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline for output.
     * Includes the task type, completion status, description, and deadline date.
     * @return string representation for output
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the deadline in the format to save.
     * @return string representation to save
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
