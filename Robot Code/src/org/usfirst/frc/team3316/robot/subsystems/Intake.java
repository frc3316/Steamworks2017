package org.usfirst.frc.team3316.robot.subsystems;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.intake.MoveIntake;
import org.usfirst.frc.team3316.robot.robotIO.DBugSpeedController;

public class Intake extends DBugSubsystem
{
	private DBugSpeedController motor;
	
	public Intake() {
		// Actuators
		Robot.actuators.IntakeActuators();
		
		motor = Robot.actuators.intakeMotor;
	}
	
	@Override
	public void initDefaultCommand()
	{}
	
	public void setMotor(double v) {
		motor.setMotor(v);
	}
	
}
