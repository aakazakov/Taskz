package dev.fun.taskz.interaction;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import dev.fun.taskz.data.init.InputDataHandler;
import dev.fun.taskz.management.ApplicationManager;

public class CommandInterpreter {
	
	public static final String QUITE = "q";
	
	private static final String DELIMITER = " ";
	private static final String SUCCESS = "success";
	private static final String UNKNOWN = "unknown command";
	private static final String CREATE = "create";
	private static final String DELETE = "delete";
	private static final String SHOW = "show";
	
	private final ApplicationManager manager = new ApplicationManager();
	
	private Queue<String> tokenQueue;
	
	public String run(String commandLine) throws CommandException {
		tokenQueue = new LinkedList<>(Arrays.asList(commandLine.split(DELIMITER)));
		try {
			String command = tokenQueue.poll();
			if (command.equals(CREATE)) {
				return onCreate();
			}
			if (command.equals(DELETE)) {
				return onDelete();
			}
			if (command.equals(SHOW)) {
				return onShow();
			}		
			return UNKNOWN;
		} catch (Exception e) {
			throw new CommandException("invalid");
		}
	}
	
	private String onCreate() {
		String command = tokenQueue.poll();
		if (command.equals(InputDataHandler.PROJECT)) {
			manager.createProject(tokenQueue.poll());
		} else if (command.equals(InputDataHandler.USER)) {
			manager.createUser(tokenQueue.poll());
		} else if (command.equals(InputDataHandler.TASK)) {
			manager.createTask(Long.parseLong(tokenQueue.poll()), tokenQueue.poll());
		} else {
			return UNKNOWN;
		}
		return SUCCESS;
	}
	
	private String onDelete() {
		String command = tokenQueue.poll();
		if (command.equals(InputDataHandler.PROJECT)) {
			manager.deleteProject(Long.parseLong(tokenQueue.poll()));
		} else if (command.equals(InputDataHandler.USER)) {
			manager.deleteUser(Long.parseLong(tokenQueue.poll()));
		} else if (command.equals(InputDataHandler.TASK)) {
			manager.deleteTask(Long.parseLong(tokenQueue.poll()));
		} else {
			return UNKNOWN;
		}
		return SUCCESS;
	}
	
	private String onShow() {
		return null;
	}

}
