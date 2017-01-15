package org.usfirst.frc.team3316.robot.commands.chassis;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;
import org.usfirst.frc.team3316.robot.vision.AlignDrivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 *
 */
public class DrivetrainHeadingPIDCamera extends DBugCommand {

	private double leftV, rightV, pidInput;
	private PIDController pid;

	public DrivetrainHeadingPIDCamera() {
		pid = new PIDController(0, 0, 0, new PIDSource() {

			@Override
			public void setPIDSourceType(PIDSourceType arg0) {
			}

			@Override
			public double pidGet() {
				return pidInput;
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {

			@Override
			public void pidWrite(double arg0) {
				// TODO Auto-generated method stub
				leftV = arg0;
				rightV = -arg0;
			}
		});
	}

	// Called just before this Command runs the first time
	protected void init() {
		pidInput = 0.0;

		pid.setAbsoluteTolerance((double) config.get("chassis_SetHeading_PID_Tolerance"));
		pid.setOutputRange(-1.0, 1.0);
		pid.setPID((double) config.get("chassis_SetHeading_PID_KP"), (double) config.get("chassis_SetHeading_PID_KI"),
				(double) config.get("chassis_SetHeading_PID_KD"));

		pid.setSetpoint((double) config.get("chassis_SetHeadingCamera_PID_Setpoint"));

		pid.enable(); // Starting PID
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (AlignDrivetrain.isObjectDetected()) {
			// Lift detected
			pidInput = AlignDrivetrain.getLiftAngle();

			Robot.chassis.setMotors(leftV, rightV);
		} else {
			// Lift not detected
			pid.disable();
			pid.reset();

			Robot.chassis.setMotors(0.0, 0.0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return pid.onTarget();
	}

	// Called once after isFinished returns true
	protected void fin() {
		pid.disable();
		Robot.chassis.setMotors(0.0, 0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interr() {
		fin();
	}
}
