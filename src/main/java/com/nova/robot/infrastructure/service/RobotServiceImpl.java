package com.nova.robot.infrastructure.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nova.robot.application.domain.service.RobotService;

@Service
public class RobotServiceImpl implements RobotService {

	private int x;
	private int y;

	private enum Direction {
		NORTH, EAST, SOUTH, WEST;
	}

	private Direction direction;

	public RobotServiceImpl() {
		super();

		// Set initial position of the robot.
		x = y = 0;

		// Set initial direction of the robot.
		direction = Direction.NORTH;
	}

	@Override
	public String processCommand(String command) {
		String output = null;

		// Invalid until proven otherwise.
		boolean commandValid = false;

		if (command.startsWith("PLACE ")) {
			String parameters = command.substring(6);

			if (!StringUtils.isEmpty(parameters)) {
				String delims = "[,]";
				String[] tokens = parameters.split(delims);

				// Only expecting 3 tokens.
				if (tokens != null && tokens.length == 3) {
					try {
						int x = Integer.parseInt(tokens[0]);
						int y = Integer.parseInt(tokens[1]);

						String direction = tokens[2];
						if (!StringUtils.isEmpty(direction)) {
							switch (direction.trim()) {
							case "NORTH":
								processPlaceCommand(x, y, Direction.NORTH);
								commandValid = true;
								break;

							case "EAST":
								processPlaceCommand(x, y, Direction.EAST);
								commandValid = true;
								break;

							case "SOUTH":
								processPlaceCommand(x, y, Direction.SOUTH);
								commandValid = true;
								break;

							case "WEST":
								processPlaceCommand(x, y, Direction.WEST);
								commandValid = true;
								break;
							}
						}
					} catch (NumberFormatException e) {
						// Nothing to do...
					}
				}
			}
		}

		if (command.equals("MOVE")) {
			processMoveCommand();
			commandValid = true;
		}

		if (command.equals("LEFT")) {
			processRotateAntiClockwiseCommand();
			commandValid = true;
		}

		if (command.equals("RIGHT")) {
			processRotateClockwiseCommand();
			commandValid = true;
		}

		if (command.equals("REPORT")) {
			output = String.format("%d,%d,%s", x, y, direction);
			commandValid = true;
		}

		if (!commandValid) {
			output = "Invalid command entered";
		}

		return output;
	}

	private void processPlaceCommand(int x, int y, Direction direction) {
		if ((x >= 0 && x <= 4) && (y >= 0 && y <= 4)) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}

	private void processRotateClockwiseCommand() {
		switch (this.direction) {
		case NORTH:
			this.direction = Direction.EAST;
			break;

		case EAST:
			this.direction = Direction.SOUTH;
			break;

		case SOUTH:
			this.direction = Direction.WEST;
			break;

		default:
			this.direction = Direction.NORTH;
		}
	}

	private void processRotateAntiClockwiseCommand() {
		switch (this.direction) {
		case NORTH:
			this.direction = Direction.WEST;
			break;

		case EAST:
			this.direction = Direction.NORTH;
			break;

		case SOUTH:
			this.direction = Direction.EAST;
			break;

		default:
			this.direction = Direction.SOUTH;
		}
	}

	private void processMoveCommand() {
		switch (this.direction) {
		case NORTH:
			if (y < 4) {
				y++;
			}
			break;

		case EAST:
			if (x < 4) {
				x++;
			}
			break;

		case SOUTH:
			if (y > 0) {
				y--;
			}
			break;

		default:
			if (x > 0) {
				x--;
			}
		}
	}

}
