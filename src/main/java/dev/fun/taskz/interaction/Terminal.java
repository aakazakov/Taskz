package dev.fun.taskz.interaction;

import java.util.Scanner;

public class Terminal implements Runnable {
	
	private final CommandInterpreter interpreter = new CommandInterpreter();
	
	@Override
	public void run() {
		System.out.println("Hi");
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine();
		
		while (!command.equals(CommandInterpreter.QUITE)) {
			try {
				System.out.println(interpreter.run(command));
			} catch (CommandException e) {
				System.out.println(e.getMessage());
			}
			command = scanner.nextLine();
		}
		
		scanner.close();
		System.out.println("Bye, Bye");
	}

}
