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

	private double dist, initDistance = 0;
	private PIDController pid;

	public DriveDistance(double distance) {
		requires(Robot.chassis);
		this.dist = distance;
		initPID();
	}

	private void initPID() {
		pid = new PIDController(0, 0, 0, new PIDSource() {
			public void setPIDSourceType(PIDSourceType pidSource) {
				return;
			}

			public double pidGet() {
				double currentDist = Robot.chassis.getDistance() - initDistance;

				// REMOVE AFTER TESTINGS
				SmartDashboard.putNumber("Current Distance", currentDist);

				return currentDist;
			}

			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {

			public void pidWrite(double output) {
				double velocity = output;
				Robot.chassis.setMotors(velocity, velocity);
			}
		}, 0.02);
	}

	// Called just before this Command runs the first time
	protected void init() {
		Robot.chassis.setBrake(true);
		
		pid.setOutputRange(-1, 1);

		pid.setAbsoluteTolerance((double) config.get("chassis_DriveDistance_PID_Tolerance"));

		pid.setPID((double) config.get("chassis_DriveDistance_PID_KP") / 1000,
				(double) config.get("chassis_DriveDistance_PID_KI") / 1000,
				(double) config.get("chassis_DriveDistance_PID_KD") / 1000);

		pid.setSetpoint(dist - initDistance);

		initDistance = Robot.chassis.getDistance();

		pid.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return pid.onTarget();
	}

	// Called once after isFinished returns true
	protected void fin() {
		pid.reset();
		pid.disable();
		Robot.chassis.setMotors(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interr() {
		fin();
	}
}
