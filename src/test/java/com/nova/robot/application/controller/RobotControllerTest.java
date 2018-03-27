package com.nova.robot.application.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.nova.robot.application.domain.service.RobotService;
import com.nova.robot.application.utils.InputReader;
import com.nova.robot.application.utils.OutputWriter;
import com.nova.robot.infrastructure.service.RobotServiceImpl;


@RunWith(SpringRunner.class)
public class RobotControllerTest {
	
	private RobotController robotController;

	private RobotService robotService;
	
	@Mock
	private InputReader mockInputReader;
	
	@Mock
	private OutputWriter mockOutputWriter;
	
	@Before
	public void setUp() throws Exception {
		robotService = new RobotServiceImpl();
		
		robotController = new RobotController();
		ReflectionTestUtils.setField(robotController, "robotService", robotService);
		robotController.setInputReader(mockInputReader);
		robotController.setOutputWriter(mockOutputWriter);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidCommandsExampleA() {
		
		when(mockInputReader.readLine()).thenReturn("PLACE 0,0,NORTH");
		boolean quit = robotController.run();
		assertFalse("Quit should not have been set", quit);
		verifyZeroInteractions(mockOutputWriter);
		
		when(mockInputReader.readLine()).thenReturn("MOVE");
		quit = robotController.run();
		assertFalse("Quit should not have been set", quit);
		verifyZeroInteractions(mockOutputWriter);
		
		when(mockInputReader.readLine()).thenReturn("report");
		quit = robotController.run();
		assertFalse("Quit should not have been set", quit);
		verify(mockOutputWriter).writeLine("0,1,NORTH");
	}
	
	@Test
	public void testValidCommandsExampleB() {
		
		when(mockInputReader.readLine()).thenReturn("PLACE 0,0,NORTH");
		boolean quit = robotController.run();
		assertFalse("Quit should not have been set", quit);
		verifyZeroInteractions(mockOutputWriter);
		
		when(mockInputReader.readLine()).thenReturn("LEFT");
		quit = robotController.run();
		assertFalse("Quit should not have been set", quit);
		verifyZeroInteractions(mockOutputWriter);
		
		when(mockInputReader.readLine()).thenReturn("Report");
		quit = robotController.run();
		assertFalse("Quit should not have been set", quit);
		verify(mockOutputWriter).writeLine("0,0,WEST");
	}
	
	@Test
	public void testValidCommandsExampleC() {
		
		when(mockInputReader.readLine()).thenReturn("PLACE 1,2,EAST");
		boolean quit = robotController.run();
		assertFalse("Quit should not have been set", quit);
		verifyZeroInteractions(mockOutputWriter);
		
		when(mockInputReader.readLine()).thenReturn("MOVE");
		quit = robotController.run();
		assertFalse("Quit should not have been set", quit);
		verifyZeroInteractions(mockOutputWriter);
		
		when(mockInputReader.readLine()).thenReturn("move");
		quit = robotController.run();
		assertFalse("Quit should not have been set", quit);
		verifyZeroInteractions(mockOutputWriter);		
		
		when(mockInputReader.readLine()).thenReturn("left");
		quit = robotController.run();
		assertFalse("Quit should not have been set", quit);
		verifyZeroInteractions(mockOutputWriter);	
		
		when(mockInputReader.readLine()).thenReturn("MOVE");
		quit = robotController.run();
		assertFalse("Quit should not have been set", quit);
		verifyZeroInteractions(mockOutputWriter);		
		
		when(mockInputReader.readLine()).thenReturn("REPORT");
		quit = robotController.run();
		assertFalse("Quit should not have been set", quit);
		verify(mockOutputWriter).writeLine("3,3,NORTH");
	}
	
	@Test
	public void testQuitApplication() {
		when(mockInputReader.readLine()).thenReturn("PLACE 1,2,EAST");
		boolean quit = robotController.run();
		assertFalse("Quit should not have been set", quit);
		verifyZeroInteractions(mockOutputWriter);
		
		when(mockInputReader.readLine()).thenReturn("exit");
		quit = robotController.run();
		assertTrue("Quit should have been set", quit);
		verifyZeroInteractions(mockOutputWriter);		
	}
	
}
