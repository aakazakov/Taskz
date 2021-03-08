package dev.fun.taskz;

import java.io.IOException;

import dev.fun.taskz.data.init.InputDataHandler;
import dev.fun.taskz.interaction.Terminal;

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

/*
	CREATE COMMANDS:
	create project <title>
	create user <name>
	create task <projectID> <description>
	
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
*/
