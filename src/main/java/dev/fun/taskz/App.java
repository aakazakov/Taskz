package dev.fun.taskz;

import java.io.IOException;

import dev.fun.taskz.data.init.InputDataHandler;
import dev.fun.taskz.interaction.Terminal;

/*
CREATE COMMANDS:
create project <title>
create user <name>
create task <projectID> <content> // creates a task in the specified project (<content> means task description)
create subtask <parentTaskID> <content> // creates a subtask for the specified task

DELETE COMMANDS:
delete project <projectID>
delete user <userID>
delete task <taskID>

SHOW COMMANDS:
show project list
show project <projectID>
show user list
show user <userID>
show task list <projectID>
show task <taskID>
show tasks <userID> <projectID> // displays a list of user tasks in the specified project

OTHER COMMANDS:
set status <taskID> <status> // sets the actual status of the task
assign user <projectID> <userID> // assigns user on the project
assign task <userID> <taskID> // assigns task on the user

EXAMPLES:
create project AwesomeProject // saved title: AwesomeProject
create project 'AwesomeProject' // saved title: AwesomeProject
create project 'AwesomeProject // saved title: AwesomeProject
create project 'Awesome Project' // saved title: Awesome Project
create project 'Awesome Project // saved title: Awesome Project
create project 'Awesome Project bla-bla-bla // saved title: Awesome Project bla-bla-bla
create project 'Awesome Project' bla-bla-bla // saved title: Awesome Project
create project Awesome Project bla-bla-bla // saved title: Awesome
show project 2
assign user 2 1
set status 4 done
create task 2 'Greet the world' // in the project with id = 2 a task with the content "Greet the world" will be created

>>> Have fun <<<
*/

public class App {
	
    public static void main( String[] args ) {   	
    	InputDataHandler idh = new InputDataHandler("init_db.txt");
    	
    	try {
				idh.initDb();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	
    	new Thread(new Terminal()).start();
    }
    
}
