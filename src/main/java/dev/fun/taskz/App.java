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
	add project <title>
	add user <name>
	add task <projectID> <description>
	
	DELETE COMMANDS:
	del project <projectID>
	del user <userID>
	del task <taskID>
	
	INFO COMMANDS:
	inf project list
	inf project <projectID>
	inf user list
	inf user <userID>
	inf task <projectID>
	inf task <taskID>
	inf tasks <userID> <projectID>
	
	OTHER COMMANDS:
	set status <taskID> <status>
	assign user <projectID> <userID>
	assign task <userID> <taskID>
*/