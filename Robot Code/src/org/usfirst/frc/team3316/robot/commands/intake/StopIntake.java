package org.usfirst.frc.team3316.robot.commands.intake;
import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

public class StopIntake extends DBugCommand {
	
	public StopIntake() {
		requires(Robot.intake);
	}
	
	@Override
	protected void init() {
		Robot.intake.setMotor(0.0);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void fin() {
	}

	@Override
	protected void interr() {
		fin();
	}

}
