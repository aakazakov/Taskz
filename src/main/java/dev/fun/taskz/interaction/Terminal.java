package dev.fun.taskz.interaction;

import java.util.Scanner;

public class Terminal implements Runnable {
	
	private final CommandHandler commandHandler = new CommandHandler();
	
	@Override
	public void run() {
		System.out.println("Hi");
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine();
		
		while (!command.equals(CommandHandler.QUITE)) {
			System.out.println(commandHandler.handle(command));
			command = scanner.nextLine();
		}
		
		scanner.close();
		System.out.println("Bye, Bye");
	}

}
