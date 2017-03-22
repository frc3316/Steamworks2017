package org.usfirst.frc.team3316.robot.commands.climbing;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

public class ClimbingUpSlow extends DBugCommand {
	public ClimbingUpSlow() {
	    requires(Robot.climbing);
	}
    
	@Override
	protected void init() {
		Robot.climbing.setMotor(0.0);
	}

	@Override
	protected void execute() {
		Robot.climbing.setMotor((double) config.get("climbing_UpSlow_Voltage"));
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
