package dev.fun.taskz.interaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import javax.persistence.NoResultException;
import javax.persistence.EntityNotFoundException;

import org.hibernate.PropertyValueException;

import dev.fun.taskz.data.init.InputDataHandler;
import dev.fun.taskz.entities.Task;
import dev.fun.taskz.entities.Task.Status;
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
	private static final String SET = "set";
	private static final String TASK_STATUS = "status";
	private static final String ASSIGN = "assign";
	private static final String UNSATISFIED_REQUEST = "there is nothing to satisfy the request";
	
	private final ApplicationManager manager = new ApplicationManager();
	
	private Queue<String> tokenQueue;
	
	public CommandInterpreter() {
		tokenQueue = new LinkedList<>();
	}
	
	public String run(String commandLine) throws CommandException {
		tokenQueue.clear();
		tokenQueue.addAll(Arrays.asList(commandLine.split(DELIMITER)));
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
			if (token.equals(SET)) {
				return onSet();
			}
			if (token.equals(ASSIGN)) {
				return onAssign();
			}
			return UNKNOWN;
		} catch (NullPointerException e) {
			throw new CommandException("invalid: the required token is null");
		} catch (NumberFormatException e) {
			throw new CommandException("invalid: the required token must be a number");
		} catch (NoResultException e) {
			throw new CommandException(UNSATISFIED_REQUEST);
		} catch (PropertyValueException | EntityNotFoundException e) {
			throw new CommandException("invalid: " + e.getMessage());
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
			return ifNullHasBeenReturned(manager.getProject(Long.parseLong(token)));
		}
		if (token.equals(InputDataHandler.USER)) {
			token = tokenQueue.poll();
			if (token.equals(SHOW_LIST))
				return listToString(new ArrayList<>(manager.userList()));
			return ifNullHasBeenReturned(manager.getUser(Long.parseLong(token)));
		}
		if (token.equals(InputDataHandler.TASK)) {
			token = tokenQueue.poll();
			if (token.equals(SHOW_LIST))
				return listToString(new ArrayList<>(manager.taskList(Long.parseLong(tokenQueue.poll()))));
			return ifNullHasBeenReturned(manager.getTask(Long.parseLong(token)));
		}
		if (token.equals(USER_TASKS)) {
			ArrayList<Task> tasks = 
					new ArrayList<>(manager.userTasks(Long.parseLong(tokenQueue.poll()), Long.parseLong(tokenQueue.poll())));
			return listToString(tasks);
		}
		return UNKNOWN;
	}
	
	private String ifNullHasBeenReturned(Object o) {
		return (o == null) ? UNSATISFIED_REQUEST : o.toString();
	}
	
	private String onSet() {
		String token = tokenQueue.poll();
		if (token.equals(TASK_STATUS)){
			Long taskId = Long.parseLong(tokenQueue.poll());
			String status = tokenQueue.poll().toUpperCase();
			if (!taskStatusExists(status)) {
				return UNKNOWN;
			}
			manager.setTaskStatus(taskId, Status.valueOf(status));
		} else {
			return UNKNOWN;
		}
		return SUCCESS;
	}
	
	private boolean taskStatusExists(String status) {
		for (Status s : Status.values()) {
			if (status.equals(s.name()))
				return true;
		}
		return false;
	}
	
	private String onAssign() {
		String token = tokenQueue.poll();
		if (token.equals(InputDataHandler.USER)) {
			manager.assignUserOnProject(Long.parseLong(tokenQueue.poll()), Long.parseLong(tokenQueue.poll()));
		} else if (token.equals(InputDataHandler.TASK)) {
			manager.assignTaskOnUser(Long.parseLong(tokenQueue.poll()), Long.parseLong(tokenQueue.poll()));
		} else {
			return UNKNOWN;
		}
		return SUCCESS;
	}
	
	//TODO: replace Object with common Interface ??
	private String listToString(ArrayList<? extends Object> list) {
		StringBuilder sb = new StringBuilder();
		for (Object o : list) {
			sb.append(o.toString()).append("\n");
		}
		if (sb.length() == 0) return UNSATISFIED_REQUEST;
		return sb.deleteCharAt(sb.length() - 1).toString();
	}

}
