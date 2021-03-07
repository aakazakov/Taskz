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
	ADD COMMANDS:
	add project <title>
	add user <name>
	add task <projectID> <description>
	
	DELETE COMMANDS:
	
	INFO COMMANDS:
	
	OTHER COMMANDS:
	
*/