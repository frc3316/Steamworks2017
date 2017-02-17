package org.usfirst.frc.team3316.robot.auton.commands;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

public class SetAngle extends DBugCommand {
    
    private double angle;
    private DBugCommand cmd;
    
    public SetAngle(double angle) {
	this.angle = angle;
    }
    @Override
    protected void init() {
	double setpoint = angle - Robot.chassis.getYaw();
	cmd = new TurnByGyro(setpoint);
	cmd.start();
    }

    @Override
    protected void execute() {}

    @Override
    protected boolean isFinished() {
	return !cmd.isRunning();
    }

    @Override
    protected void fin() {
	cmd.cancel();
    }

    @Override
    protected void interr() {
	fin();
    }

}
