package com.nova.robot.infrastructure.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.nova.robot.application.domain.service.RobotService;

@RunWith(SpringRunner.class)
public class RobotServiceImplTest {

	private RobotService robotService;

	@Before
	public void setUp() throws Exception {
		robotService = new RobotServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidCommandsExampleA() {

		String output = robotService.processCommand("PLACE 0,0,NORTH");
		assertNull("Result should be null", output);

		output = robotService.processCommand("MOVE");
		assertNull("Result should be null", output);

		output = robotService.processCommand("REPORT");
		assertNotNull("Result should not be null", output);
		assertEquals("0,1,NORTH", output);
	}

	@Test
	public void testValidCommandsExampleB() {

		String output = robotService.processCommand("PLACE 0,0,NORTH");
		assertNull("Result should be null", output);

		output = robotService.processCommand("LEFT");
		assertNull("Result should be null", output);

		output = robotService.processCommand("REPORT");
		assertNotNull("Result should not be null", output);
		assertEquals("0,0,WEST", output);
	}

	@Test
	public void testValidCommandsExampleC() {

		String output = robotService.processCommand("PLACE 1,2,EAST");
		assertNull("Result should be null", output);

		output = robotService.processCommand("MOVE");
		assertNull("Result should be null", output);

		output = robotService.processCommand("MOVE");
		assertNull("Result should be null", output);

		output = robotService.processCommand("LEFT");
		assertNull("Result should be null", output);

		output = robotService.processCommand("MOVE");
		assertNull("Result should be null", output);

		output = robotService.processCommand("REPORT");
		assertNotNull("Result should not be null", output);
		assertEquals("3,3,NORTH", output);
	}

	@Test
	public void testInvalidPlaceCommand() {

		String output = robotService.processCommand("PLACE1,2,EAST");
		assertNotNull("Result should not be null", output);
		assertEquals("Invalid command entered", output);
	}

	@Test
	public void testInvalidRotateAntiClockwiseCommand() {

		String output = robotService.processCommand("LEFTz");
		assertNotNull("Result should not be null", output);
		assertEquals("Invalid command entered", output);
	}

	@Test
	public void testInvalidRotateClockwiseCommand() {

		String output = robotService.processCommand("RIGHT 1");
		assertNotNull("Result should not be null", output);
		assertEquals("Invalid command entered", output);
	}

	@Test
	public void testInvalidReportCommand() {

		String output = robotService.processCommand("REPORTx");
		assertNotNull("Result should not be null", output);
		assertEquals("Invalid command entered", output);
	}
}
