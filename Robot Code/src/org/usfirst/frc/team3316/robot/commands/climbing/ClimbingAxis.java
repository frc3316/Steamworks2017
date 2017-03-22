package org.usfirst.frc.team3316.robot.commands.climbing;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

public class ClimbingAxis extends DBugCommand {

    public ClimbingAxis() {
	requires(Robot.climbing);
    }

    @Override
    protected void init() {
	Robot.climbing.setMotor(0.0);
    }

    @Override
    protected void execute() {
	double value = Robot.joysticks.joystickOperator.getRawAxis(3);
	Robot.climbing.setMotor(value * (double) config.get("climbing_Up_Voltage"));
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
