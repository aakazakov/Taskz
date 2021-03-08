package dev.fun.taskz.interaction;

import java.util.ArrayList;
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
	private static final String SHOW_LIST = "list";
	private static final String USER_TASKS = "tasks";
	private static final String NOTHING = "nothing to show you...";
	
	private final ApplicationManager manager = new ApplicationManager();
	
	private Queue<String> tokenQueue;
	
	public String run(String commandLine) throws CommandException {
		tokenQueue = new LinkedList<>(Arrays.asList(commandLine.split(DELIMITER))); // new ???
		try {
			String token = tokenQueue.poll();
			if (token.equals(CREATE)) {
				return onCreate();
			}
			if (token.equals(DELETE)) {
				return onDelete();
			}
			if (token.equals(SHOW)) {
				return onShow();
			}		
			return UNKNOWN;
//		} catch (NullPointerException e) {
//			throw new CommandException("invalid: required token is null");
		} catch (NumberFormatException e) {
			throw new CommandException("invalid: required token must be a number " + "(" + e.getMessage() + ")");
		}
	}
	
	private String onCreate() {
		String token = tokenQueue.poll();
		if (token.equals(InputDataHandler.PROJECT)) {
			manager.createProject(tokenQueue.poll());
		} else if (token.equals(InputDataHandler.USER)) {
			manager.createUser(tokenQueue.poll());
		} else if (token.equals(InputDataHandler.TASK)) {
			manager.createTask(Long.parseLong(tokenQueue.poll()), tokenQueue.poll());
		} else {
			return UNKNOWN;
		}
		return SUCCESS;
	}
	
	private String onDelete() {
		String token = tokenQueue.poll();
		if (token.equals(InputDataHandler.PROJECT)) {
			manager.deleteProject(Long.parseLong(tokenQueue.poll()));
		} else if (token.equals(InputDataHandler.USER)) {
			manager.deleteUser(Long.parseLong(tokenQueue.poll()));
		} else if (token.equals(InputDataHandler.TASK)) {
			manager.deleteTask(Long.parseLong(tokenQueue.poll()));
		} else {
			return UNKNOWN;
		}
		return SUCCESS;
	}
	
	private String onShow() {
		String token = tokenQueue.poll();
		if (token.equals(InputDataHandler.PROJECT)) {
			token = tokenQueue.poll();
			if (token.equals(SHOW_LIST))
				return listToString(new ArrayList<>(manager.projectList()));
			return manager.getProject(Long.parseLong(token)).toString();
		}
		if (token.equals(InputDataHandler.USER)) {
			token = tokenQueue.poll();
			if (token.equals(SHOW_LIST))
				return listToString(new ArrayList<>(manager.userList()));
			return manager.getUser(Long.parseLong(token)).toString();
		}
		if (token.equals(InputDataHandler.TASK)) {
			token = tokenQueue.poll();
			if (token.equals(SHOW_LIST))
				return listToString(new ArrayList<>(manager.taskList(Long.parseLong(tokenQueue.poll()))));
			return manager.getTask(Long.parseLong(token)).toString();
		}
		if (token.equals(USER_TASKS)) {
			return manager.userTasks(Long.parseLong(tokenQueue.poll()), Long.parseLong(tokenQueue.poll())).toString();
		}
		return UNKNOWN;
	}
	
	//TODO: replace Object with common Interface ??
	private String listToString(ArrayList<? extends Object> list) {
		StringBuilder sb = new StringBuilder();
		for (Object o : list) {
			sb.append(o.toString()).append("\n");
		}
		if (sb.length() == 0) return NOTHING;
		return sb.deleteCharAt(sb.length() - 1).toString();
	}

}
