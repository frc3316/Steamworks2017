package org.usfirst.frc.team3316.robot.commands.intake;
import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

public class MoveIntake extends DBugCommand {
	double v;
	
	public MoveIntake() {
		v = 0.0;
		
		requires(Robot.intake);
	}
	
	@Override
	protected void init() {
		Robot.intake.setMotor(0.0);
	}

	@Override
	protected void execute() {
		v = (double) Robot.config.get("intake_MoveIntake_V");
		Robot.intake.setMotor(v);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void fin() {
		Robot.intake.setMotor(0.0);
	}

	@Override
	protected void interr() {
		fin();
	}

}
