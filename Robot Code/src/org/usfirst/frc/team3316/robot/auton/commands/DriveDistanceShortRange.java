package org.usfirst.frc.team3316.robot.auton.commands;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

/**
 *
 */
public class DriveDistanceShortRange extends DBugCommand {

    private DBugCommand cmd;

    public DriveDistanceShortRange(double distanceRight, double distanceLeft) {
	requires(Robot.chassis);
	cmd = new DriveDistance(distanceRight, distanceLeft);
    }

    // Called just before this Command runs the first time
    protected void init() {
	// Right
	config.add("chassis_DriveDistance_PID_RIGHT_KP", config.get("chassis_DriveDistanceShortRange_PID_RIGHT_KP"));
	config.add("chassis_DriveDistance_PID_RIGHT_KI", config.get("chassis_DriveDistanceShortRange_PID_RIGHT_KI"));
	config.add("chassis_DriveDistance_PID_RIGHT_KD", config.get("chassis_DriveDistanceShortRange_PID_RIGHT_KD"));
	// Left
	config.add("chassis_DriveDistance_PID_LEFT_KP", config.get("chassis_DriveDistanceShortRange_PID_LEFT_KP"));
	config.add("chassis_DriveDistance_PID_LEFT_KI", config.get("chassis_DriveDistanceShortRange_PID_LEFT_KI"));
	config.add("chassis_DriveDistance_PID_LEFT_KD", config.get("chassis_DriveDistanceShortRange_PID_LEFT_KD"));
	cmd.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return !cmd.isRunning() || cmd.isCanceled();
    }

    // Called once after isFinished returns true
    protected void fin() {
	cmd.cancel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interr() {
	fin();
    }
}
