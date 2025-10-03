package mimi;

import mimi.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list using data from an existing task list
     * @param tasks the existing task list to be copied to the new task list
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns the number of tasks
     * @return the list size
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at given task number
     * @param index the task number
     * @return the task
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the list of tasks that match the given keyword
     * @param keyword given keyword by the user
     * @return list of matching tasks
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            String taskDescription = (task.toString()).toLowerCase();
            if (taskDescription.contains(keyword.toLowerCase())) {
                matches.add(task);
            }
        }
        return matches;
    }

    /**
     * Adds a task to the task list.
     * @param taskName the name of task to be added
     */
    public void add(Task taskName) {
        tasks.add(taskName);
    }

    /**
     * Deletes a task from the task list
     * @param index the task number of the task to be deleted
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Marks a task as done
     * @param index the task number of the task to be marked
     */
    public void mark(int index) {
        Task t = tasks.get(index);
        t.markAsDone();
    }

    /**
     * Marks a task as not done yet
     * @param index the task number of the task to be unmarked
     */
    public void unmark(int index) {
        Task t = tasks.get(index);
        t.unmarkAsDone();
    }

    /**
     * Lists all the tasks in the task list.
     * @return the list of tasks
     */
    public ArrayList<Task> asList() {
        return new ArrayList<>(tasks);
    }
}
