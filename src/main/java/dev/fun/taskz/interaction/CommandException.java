package dev.fun.taskz.interaction;

public class CommandException extends Exception {

	private static final long serialVersionUID = 1L;

	public CommandException() {
		super();
	}
	
	public CommandException(String msg) {
		super(msg);
	}
	
}
