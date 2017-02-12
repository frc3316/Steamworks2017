package org.usfirst.frc.team3316.robot.commands.intake;
import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

public class WaitForGear extends DBugCommand {
	
	public WaitForGear() {}
	
	@Override
	protected void init() {}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Robot.intake.isGearIn();
	}

	@Override
	protected void fin() {
	}

	@Override
	protected void interr() {
		fin();
	}

}
