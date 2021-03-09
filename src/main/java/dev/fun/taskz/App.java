package dev.fun.taskz;

import java.io.IOException;

import dev.fun.taskz.data.init.InputDataHandler;
import dev.fun.taskz.interaction.Terminal;

/*
CREATE COMMANDS:
create project <title>
create user <name>
create task <projectID> <description>
create subtask <parentTaskID> <description>

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
show tasks <userID> <projectID>

OTHER COMMANDS:
set status <taskID> <status>
assign user <projectID> <userID>
assign task <userID> <taskID>

command examples:
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
