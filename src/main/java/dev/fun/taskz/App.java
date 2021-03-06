package dev.fun.taskz;

import java.io.IOException;

import dev.fun.taskz.data.init.InputDataHandler;

public class App {
	
    public static void main( String[] args ) {   	
    	InputDataHandler idh = new InputDataHandler("init_db.txt");
    	try {
				idh.launch();
			} catch (IOException e) {
				e.printStackTrace();
			}        
    }
    
}
