package com.nova.robot.application.utils;

public class ConsoleInputReader implements InputReader {

	@Override
	public String readLine() {
		return System.console().readLine();
	}

	
}
