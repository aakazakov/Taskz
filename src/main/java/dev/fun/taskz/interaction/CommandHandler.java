package dev.fun.taskz.interaction;

public class CommandHandler {
	
	public static final String QUITE = "\\q";
	
	private static final String DELIMITER = " ";
	private static final String ADD_OBJECT = "\\add";
	private static final String DELETE_OBJECT = "\\del";
	private static final String INFO = "\\inf";
	private static final String TO = "\\to";
	private static final String FROM = "\\from";
	
	private static final String SHOW_LIST = "\\list";
	
	public String handle(String command) {
		String[] tokens = command.split(DELIMITER);
		
		if (tokens[0].equals(ADD_OBJECT)) { // \add project ProjectTitle | TO
			
		} else if (tokens[0].equals(DELETE_OBJECT)) {
			
		} else if (tokens[0].equals(INFO)) {
			
		} else {
			return "unknown command";
		}
		return null;
	}
	
	private void commandAdd(String[] tokens) {
		
	}

}
