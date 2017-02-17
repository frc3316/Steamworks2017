package org.usfirst.frc.team3316.robot.commands.installer;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

public class WaitForPeg extends DBugCommand {

	@Override
	protected void init() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
	    if (Robot.installer.isPegPushing()) {
		logger.info("PEG IS PUSHING");
	    }
		return Robot.installer.isPegPushing();
	}

	@Override
	protected void fin() {}

	@Override
	protected void interr() {}

}
