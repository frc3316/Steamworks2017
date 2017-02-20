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
public class DriveDistance extends DBugCommand {

	private double distRight, distLeft, initDistanceRight = 0, initDistanceLeft = 0;
	private PIDController pidRight, pidLeft;
	private double velocityRight = 0, velocityLeft = 0;

	public DriveDistance(double distanceRight, double distanceLeft) {
		this.distRight = distanceRight;
		this.distLeft = distanceLeft;
		initPIDRight();
		initPIDLeft();
	}

	private void initPIDRight() {
		pidRight = new PIDController(0, 0, 0, new PIDSource() {
			public void setPIDSourceType(PIDSourceType pidSource) {
				return;
			}

			public double pidGet() {
				double currentDist = Robot.chassis.getRightDistance() - initDistanceRight;

				return currentDist;
			}

			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {

			public void pidWrite(double output) {
				velocityRight = output;
			}
		}, 0.02);
	}
	
	private void initPIDLeft() {
		pidLeft = new PIDController(0, 0, 0, new PIDSource() {
			public void setPIDSourceType(PIDSourceType pidSource) {
				return;
			}

			public double pidGet() {
				double currentDist = Robot.chassis.getLeftDistance() - initDistanceLeft;

				return currentDist;
			}

			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {

			public void pidWrite(double output) {
			    velocityLeft = output;
			}
		}, 0.02);
	}

	// Called just before this Command runs the first time
	protected void init() {
		Robot.chassis.setBrake(true);
		
		Robot.chassis.resetYaw();
		
		pidRight.setOutputRange(-1, 1);
		pidLeft.setOutputRange(-1, 1);

		pidRight.setAbsoluteTolerance((double) config.get("chassis_DriveDistance_PID_Tolerance"));
		pidLeft.setAbsoluteTolerance((double) config.get("chassis_DriveDistance_PID_Tolerance"));

		pidRight.setPID((double) config.get("chassis_DriveDistance_PID_RIGHT_KP") / 1000,
				(double) config.get("chassis_DriveDistance_PID_RIGHT_KI") / 1000,
				(double) config.get("chassis_DriveDistance_PID_RIGHT_KD") / 1000);
		pidLeft.setPID((double) config.get("chassis_DriveDistance_PID_LEFT_KP") / 1000,
			(double) config.get("chassis_DriveDistance_PID_LEFT_KI") / 1000,
			(double) config.get("chassis_DriveDistance_PID_LEFT_KD") / 1000);

		pidRight.setSetpoint(distRight);
		pidLeft.setSetpoint(distLeft);

		initDistanceRight = Robot.chassis.getRightDistance();
		initDistanceLeft = Robot.chassis.getLeftDistance();

		pidRight.enable();
		pidLeft.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	    Robot.chassis.setMotors(velocityLeft, velocityRight);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
	    return pidRight.onTarget() && pidLeft.onTarget();
	}

	// Called once after isFinished returns true
	protected void fin() {
		pidRight.reset();
		pidRight.disable();
		
		pidLeft.reset();
		pidLeft.disable();
		
		Robot.chassis.setMotors(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interr() {
		fin();
	}
}
