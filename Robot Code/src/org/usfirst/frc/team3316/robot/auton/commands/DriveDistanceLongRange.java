package org.usfirst.frc.team3316.robot.auton.commands;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.chassis.motion.MotionPlanner;
import org.usfirst.frc.team3316.robot.chassis.motion.PlannedMotion;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;
import org.usfirst.frc.team3316.robot.commands.chassis.BrakeMode;
import org.usfirst.frc.team3316.robot.commands.chassis.CoastMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistanceLongRange extends DBugCommand {

	private DriveDistance cmd;

	public DriveDistanceLongRange(double distanceRight, double distanceLeft) {
	    requires(Robot.chassis);
		cmd = new DriveDistance(distanceRight, distanceLeft);
		cmd.started = false;
	}

	// Called just before this Command runs the first time
	protected void init() {
		// Right
		config.add("chassis_DriveDistance_PID_RIGHT_KP", config.get("chassis_DriveDistanceLongRange_PID_RIGHT_KP"));
		config.add("chassis_DriveDistance_PID_RIGHT_KI", config.get("chassis_DriveDistanceLongRange_PID_RIGHT_KI"));
		config.add("chassis_DriveDistance_PID_RIGHT_KD", config.get("chassis_DriveDistanceLongRange_PID_RIGHT_KD"));
		// Left
		config.add("chassis_DriveDistance_PID_LEFT_KP", config.get("chassis_DriveDistanceLongRange_PID_LEFT_KP"));
		config.add("chassis_DriveDistance_PID_LEFT_KI", config.get("chassis_DriveDistanceLongRange_PID_LEFT_KI"));
		config.add("chassis_DriveDistance_PID_LEFT_KD", config.get("chassis_DriveDistanceLongRange_PID_LEFT_KD"));
		cmd.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
	    logger.info("DriveDistanceLong isFinished: " + cmd.isRunning() + " " + cmd.isCanceled());
	    return (!cmd.isRunning() || cmd.isCanceled()) && cmd.started;
	}

	// Called once after isFinished returns true
	protected void fin() {
	    logger.info("DriveDistanceLong ended");
	    cmd.started = false;
	    cmd.cancel();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interr() {
		fin();
	}
}
