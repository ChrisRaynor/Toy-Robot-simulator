package com.nova.robot.application.utils;

public class ConsoleOutputWriter implements OutputWriter {

	@Override
	public void writeLine(String output) {
		System.out.println(output);
	}

}
