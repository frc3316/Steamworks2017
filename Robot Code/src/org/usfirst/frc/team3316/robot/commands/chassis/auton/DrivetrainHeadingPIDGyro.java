package org.usfirst.frc.team3316.robot.commands.chassis.auton;

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
public class DrivetrainHeadingPIDGyro extends DBugCommand {

	private double leftV, rightV, setpoint;
	private PIDController pid;

	public DrivetrainHeadingPIDGyro() {
		pid = new PIDController(0, 0, 0, new PIDSource() {

			@Override
			public void setPIDSourceType(PIDSourceType arg0) {
			}

			@Override
			public double pidGet() {
				return Robot.chassis.getYaw();
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
		leftV = 0.0;
		rightV = 0.0;
		setpoint = 0.0;
		
		// Setting intial values for PID
		pid.setAbsoluteTolerance((double) config.get("chassis_SetHeading_PID_Tolerance"));
		pid.setOutputRange(-1.0, 1.0);
		pid.setPID((double) config.get("chassis_SetHeading_PID_KP"), (double) config.get("chassis_SetHeading_PID_KI"),
				(double) config.get("chassis_SetHeading_PID_KD"));

		pid.enable(); // Starting PID
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (AlignDrivetrain.isObjectDetected()) {
			// Lift detected
			setpoint = Robot.chassis.getYaw() + AlignDrivetrain.getLiftAngle();
			pid.setSetpoint(setpoint);
			
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
