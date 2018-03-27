package com.nova.robot.application.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.nova.robot.application.domain.service.RobotService;
import com.nova.robot.application.utils.InputReader;
import com.nova.robot.application.utils.OutputWriter;



@Component
public class RobotController {
	
	private static final Logger logger = LoggerFactory.getLogger(RobotController.class);	
	
	@Autowired
	private RobotService robotService;
	
	private InputReader inputReader;
	private OutputWriter outputWriter;
	
	
	 
	public RobotController() {
		super();
	}



	public boolean run() {
		boolean quit = false;

		if (inputReader != null) {
			System.out.println("Please enter a command...");

			String input = inputReader.readLine();

			if (!StringUtils.isEmpty(input)) {
				// Convert all commands to upper case.
				input = input.toUpperCase(Locale.ROOT);

				// Check for quit.
				if (input.equals("Q") || input.equals("QUIT") || input.equals("EXIT")) {
					logger.info("Exiting!");
					quit = true;
				} else {
					String output = robotService.processCommand(input);
					if (!StringUtils.isEmpty(output) && outputWriter != null) {
						outputWriter.writeLine(output);
					}
				}
			}
		}

		return quit;
	}


	public void setInputReader(InputReader inputReader) {
		this.inputReader = inputReader;
	}

	public void setOutputWriter(OutputWriter outputWriter) {
		this.outputWriter = outputWriter;
	}




	
	
	
}
