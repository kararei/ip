# kx (Kai Xin) - Task Management Chatbot

<div align="center">

A command-line task management chatbot that helps you stay organized efficiently.

[Quick Start](#-quick-start) • [Features](#features) • [Commands](#command-details) • [Command Summary](#command-summary)


![kx GUI](/docs/Ui.png)
</div>

## Contents
* [Quick Start](#-quick-start)
* [Features](#features)
* [Command Details](#command-details)
* [Command Summary](#command-summary)
* [FAQ](#faq)
* [Acknowledgments](#acknowledgments)

## 🚀 Quick Start

### Prerequisites
Ensure you have **Java 17** installed on your computer. You can check your version by running:
```sh
java-version
```
### Next steps
1. Download 'Kx.jar' from the [release](https://github.com/kararei/ip/releases) page on github

2. Open your CLI and navigate to the respective folder with the downloaded JAR file'

3. Run application using:
```sh
java -jar Kx.jar
```

## Overview

kx (**Kai Xin**) is a **task management chatbot** designed to help users efficiently manage their tasks using a **simple command-line
interface**. It supports **adding, deleting, marking tasks as done/undone, finding, viewing and more**.

## Features

kx (**Kai Xin**) is a **task management chatbot** that helps you:
* Manage different types of tasks (todo, deadline, event)
* Track task completion status
* Search through your tasks
* View tasks by date
* And more!

 :information_source: **Command Format**
* Words in `UPPER_CASE` are parameters you need to supply
    * Example: in `todo DESCRIPTION`, `DESCRIPTION` can be `read book`
* Dates should be in the format: `dd-MM-yyyy HHmm`
* or for command 'view' `yyyy-MM-dd`
* Times are in 24-hour format

## Command Details
### 1. Adding a Task
#### Todo Task: `todo`
Adds a simple task without any deadlines.

**Format**: `todo DESCRIPTION`

**Example**: `todo read book` adds a task "read book"

#### Deadline Task: `deadline`
Adds a task with a specific deadline.

**Format**: `deadline DESCRIPTION /by DATE_TIME`

**Example**: `deadline submit report /by 10-03-2025 1700` creates a deadline for March 10, 2025, 5 PM

#### Event Task: `event`
Adds an event with a start and end times.

**Format**: `event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME`
**Example**: `event hackathon /from 10-03-2025 1200 /to 13-03-2025 0900`

### 2. Managing Tasks

#### Delete Tasks: `delete`
Removes a task.

**Format**: `delete INDEX`

**Example**: `delete 2`

#### List Tasks: `list`
Displays all tasks.

**Format**: `list`


#### Mark/Unmark: `mark` / `unmark`
Changes task completion status.

**Format**:`mark INDEX` or `unmark INDEX`

**Example**:
* `mark 1` marks first task as complete
* `unmark 1` marks first task as incomplete

### 3. Finding Tasks
#### Search `find`
Searches for tasks containing a keyword

**Format**: `find KEYWORD`

**Example**: `find read` finds all tasks containing "read"

#### View by Date: `view`
Displays tasks for a specific date

**Format**: `view DATE`

**Example**: `view 2025-03-12` shows tasks for March 12, 2025

## Command Summary

| Command  | Format                                                      | Example                                                     |
|----------|-------------------------------------------------------------|-------------------------------------------------------------|
| Todo     | `todo DESCRIPTION`                                          | `todo read book`                                            |
| Deadline | `deadline DESCRIPTION /by DATE_TIME`                        | `deadline submit report /by 10-03-2025 1700`                |
| Event    | `event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME` | `event hackathon /from 10-03-2025 1200 /to 13-03-2025 0900` |
| List     | `list`                                                      | `list`                                                      |
| Delete   | `delete INDEX`                                              | `delete 2`                                                  |
| Mark     | `mark INDEX`                                                | `mark 1`                                                    |
| Unmark   | `unmark INDEX`                                              | `unmark 1`                                                  |
| Find     | `find KEYWORD`                                              | `find read`                                                 |
| View     | `view DATE`                                                 | `view 2025-03-12`                                           |

## FAQ

**Q**: How do I save my tasks?
**A**: Tasks are saved automatically after each command.

**Q**: Where is my data stored?
**A**: Data is stored in `data/tasks.txt` in the same directory as your JAR file.

## Acknowledgments
* Ai (ChatGPT and Claude.AI) was used for some JavaDoc comments and part of this README