package dev.fun.taskz.data.init;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import dev.fun.taskz.management.Manager;

public class InputDataHandler {
	
	private static final String COMMENT = "#";
	private static final String DELIMITER = ";";
	private static final String PROJECT = "Project";
	private static final String TASK = "Task";
	private static final String USER = "User";
	
	private final Manager manager;
	
	private String file;
	
	private Long projectId;
	private Long userId;
	
	public String getFile() {
		return file;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
	
	public InputDataHandler(String file) {
		this.manager = new Manager();
		this.file = file;
	}
	
	public void launch() throws IOException {
		read();
	}
	
	private void read() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("init_db.txt"))) {
			while (br.ready()) {
				handle(br.readLine());
			}
		}
	}
	
	private void handle(String line) {
		String[] tokens = line.split(DELIMITER);
		String defToken = tokens[0];
		
		if (defToken.isEmpty() || defToken.startsWith(COMMENT)) return;
		
		if (defToken.equals(PROJECT)) {
			projectId = manager.createProject(tokens[1]);
			userId = null;
		} else if (defToken.equals(USER)) {
			userId = manager.createUser(tokens[1]);
			if (projectId != null) 
				manager.assignUserOnProject(projectId, userId);
		} else if (defToken.equals(TASK)) {
			Long taskId = manager.createTask(projectId, tokens[1]);
			if (userId != null)
				manager.assignTaskOnUser(userId, taskId);
		}
			
	}
	
}
