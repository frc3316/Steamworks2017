package org.usfirst.frc.team3316.robot.commands.chassis.auton;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class DriveDistance extends DBugCommand {

	private PIDController pid;
	private double v, distance;

	public DriveDistance(double dist) {
		pid = new PIDController((double) config.get("chassis_DriveDistance_PID_KP"),
				(double) config.get("chassis_DriveDistance_PID_KI"),
				(double) config.get("chassis_DriveDistance_PID_KD"), new PIDSource() {
					@Override
					public void setPIDSourceType(PIDSourceType pidSource) {}

					@Override
					public double pidGet() {
						return Robot.chassis.getDistance();
					}

					@Override
					public PIDSourceType getPIDSourceType() {
						return PIDSourceType.kDisplacement;
					}
				}, new PIDOutput() {

					@Override
					public void pidWrite(double output) {
						v = output;
					}
				});
		
		distance = dist;
	}

	@Override
	protected void init() {
		v = 0.0;
		
		Robot.chassis.resetEncoders();
		pid.setAbsoluteTolerance((double) config.get("chassis_DriveDistance_PID_Tolerance"));
		pid.setOutputRange(-1.0, 1.0);
		pid.setPID((double) config.get("chassis_DriveDistance_PID_KP"), (double) config.get("chassis_DriveDistance_PID_KI"),
				(double) config.get("chassis_DriveDistance_PID_KD"));

		pid.setSetpoint(distance);

		pid.enable(); // Starting PID
	}

	@Override
	protected void execute() {
		Robot.chassis.setMotors(v, v);
	}

	@Override
	protected boolean isFinished() {
		return pid.onTarget();
	}

	@Override
	protected void fin() {
		Robot.chassis.setMotors(0.0, 0.0);
	}

	@Override
	protected void interr() {
		fin();
	}

}
