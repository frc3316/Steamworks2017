package org.usfirst.frc.team3316.robot.commands.intake;
import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

public class ReleaseIntakeExtender extends DBugCommand {
	
	public ReleaseIntakeExtender() {
		requires(Robot.intake);
	}
	
	@Override
	protected void init() {
		Robot.intake.releaseExtender();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void fin() {
	}

	@Override
	protected void interr() {
		fin();
	}

}
