package org.usfirst.frc.team3316.robot.commands.intake;
import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

public class IntakeEmptyCommand extends DBugCommand {
	double v;
	
	public IntakeEmptyCommand() {
		requires(Robot.intake);
	}
	
	@Override
	protected void init() {}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void fin() {}

	@Override
	protected void interr() {}

}
