package org.usfirst.frc.team3316.robot.commands.climbing;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

public class ClimbingUpFast extends DBugCommand {
	public ClimbingUpFast() {
	    requires(Robot.climbing);
	}
    
	@Override
	protected void init() {
		Robot.climbing.setMotor(0.0);
	}

	@Override
	protected void execute() {
		Robot.climbing.setMotor((double) config.get("climbing_UpFast_Voltage"));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void fin() {
		Robot.climbing.setMotor(0.0);
	}

	@Override
	protected void interr() {
		fin();
	}

}
