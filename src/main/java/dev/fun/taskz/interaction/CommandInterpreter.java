package dev.fun.taskz.interaction;

public class CommandInterpreter {
	
	public static final String QUITE = "q";
	
	private static final String DELIMITER = " ";
	private static final String SUCCESS = "success";
	private static final String UNKNOWN = "unknown command";
	private static final String ADD = "add";
	private static final String DELETE = "del";
	private static final String INFO = "inf";
	
	private String[] tokens;
	
	public String run(String commandLine) throws CommandException {
		tokens = commandLine.split(DELIMITER);
		try {
			String command = tokens[0];
			if (command.equals(ADD)) {
				return onAdd();
			}
			if (command.equals(DELETE)) {
				return onDelete();
			}
			if (command.equals(INFO)) {
				return onInfo();
			}		
			return UNKNOWN;
		} catch (Exception e) {
			throw new CommandException("invalid command");
		}
	}
	
	private String onAdd() {
		return SUCCESS;
	}
	
	private String onDelete() {
		return SUCCESS;
	}
	
	private String onInfo() {
		return null;
	}

}
