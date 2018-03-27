package com.nova.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nova.robot.application.controller.RobotController;
import com.nova.robot.application.utils.ConsoleInputReader;
import com.nova.robot.application.utils.ConsoleOutputWriter;




@SpringBootApplication
public class RobotApplication {
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(RobotApplication.class, args);

		RobotController bean = ctx.getBean(RobotController.class);
		bean.setInputReader(new ConsoleInputReader());
		bean.setOutputWriter(new ConsoleOutputWriter());

		boolean quit = false;

		while (!quit) {
			quit = bean.run();
		}

	}
}
