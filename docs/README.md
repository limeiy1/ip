# Mimi User Guide

Mimi is a Personal Assistant Chatbot that helps a person to keep track of various tasks, 
optimized for use via a Command Line Interface (CLI).
The user can add tasks of three types to a task list: todo, deadline and event.
The tasks can be marked as done upon completion.
- [Quick Start](https://github.com/limeiy1/ip/blob/master/docs/README.md#quick-start)
- [Features](https://github.com/limeiy1/ip/blob/master/docs/README.md#features)
  - [Listing all tasks: `list`](https://github.com/limeiy1/ip/blob/master/docs/README.md#listing-all-tasks-list)
  - [Finding a task: `find`](https://github.com/limeiy1/ip/blob/master/docs/README.md#finding-a-task-find)
  - [Adding a todo task: `todo`](https://github.com/limeiy1/ip/blob/master/docs/README.md#adding-a-todo-task-todo)
  - [Adding a deadline task: `deadline`](https://github.com/limeiy1/ip/blob/master/docs/README.md#adding-a-deadline-task-deadline)
  - [Adding an event: `event`](https://github.com/limeiy1/ip/blob/master/docs/README.md#adding-an-event-event)
  - [Marking a task as done: `mark`](https://github.com/limeiy1/ip/blob/master/docs/README.md#marking-a-task-as-done-mark)
  - [Marking a task as not done: `unmark`](https://github.com/limeiy1/ip/blob/master/docs/README.md#marking-a-task-as-not-done-unmark)
  - [Deleting a task: `delete`](https://github.com/limeiy1/ip/blob/master/docs/README.md#deleting-a-task-delete)
  - [Exiting the programme: `bye`](https://github.com/limeiy1/ip/blob/master/docs/README.md#exiting-the-programme-bye)

## Quick Start

1. Ensure you have Java `17` or above installed in your Computer.
2. Download the latest `mimi.jar` file from [here]().
3. Copy the file to the folder you want to use as the home folder for your Mimi Chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar mimi.jar` command to run the chatbot.
   A greeting message below should appear.
```
____________________________________________________________
Hello! I'm Mimi.
What can I do for you?
____________________________________________________________
```
5. Type a command in the terminal and press Enter to execute it. 
e.g. typing `list` and pressing Enter will show a list of all the tasks in the task list.
Some example commands you can try:
   - `list`: List all tasks
   - `todo do laundry`: Adds a todo task "do laundry" to the task list.
   - `mark 1`: Marks the 1st task in the list as done.
   - `delete 1`: Deletes the 1st task in the list.
   - `bye`: Exits the program.
6. Refer to the [Features](https://github.com/limeiy1/ip/blob/master/docs/README.md#features) below for details of each command.


## Features

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`


### Finding a task: `find`

Finds the tasks which contain the keyword.

Format: `find KEYWORD`

Example: `find assignment` returns
```
Here are the matching tasks in the list:
1. [D][ ] assignment (by: 7 jan)
2. [T][ ] coding assignment
```


### Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `todo TASK`

Example: `todo practice piano`
```
I've added this task:
[T][ ] practice piano
Now you have 8 tasks in the list.
```

### Adding a deadline task: `deadline`

Adds a deadline task to the task list.

Format: `deadline TASK /by DATE/TIME`

Example: `deadline submit assignment /by 10 jan`
```
I've added this task:
[D][ ] submit assignment (by: 10 jan)
Now you have 9 tasks in the list.
```


### Adding an event: `event`

Adds an event task to the task list.

Format: `event TASK /from DATE/TIME /to DATE/TIME`

Example: `event meeting /from Fri 1pm /to 2pm`
```
I've added this task:
[E][ ] meeting (from: Fri 1pm to: 2pm)
Now you have 10 tasks in the list.
```


### Marking a task as done: `mark`

Marks the specified task as done.

Format: `mark INDEX`

Example: `mark 8` marks the 8th task in the list as done.
```
Yay! I've marked this task as done:
[T][X] practice piano
```


### Marking a task as not done: `unmark`

Marks the specified task as not done yet.

Format: `unmark INDEX`

Example: `unmark 8` marks the 8th task in the list as not done yet.
```
Aw ok, I've marked this task as not done yet:
[T][ ] practice piano
```


### Deleting a task: `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

Example: `delete 2` deletes the 2nd task in the list.


### Exiting the programme: `bye`

Exits the program.

Format: `bye`
```
Bye! Hope to see you again soon!
```


