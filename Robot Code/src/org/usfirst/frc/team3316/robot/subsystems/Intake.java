package org.usfirst.frc.team3316.robot.subsystems;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.intake.MoveIntake;
import org.usfirst.frc.team3316.robot.robotIO.DBugSpeedController;

import edu.wpi.first.wpilibj.DigitalInput;

public class Intake extends DBugSubsystem
{
	private DBugSpeedController motor;
	private DigitalInput switch1,switch2;
	
	public Intake() {
		// Actuators
		Robot.actuators.IntakeActuators();
		
		motor = Robot.actuators.intakeMotor;
		
		// Sensors
		Robot.sensors.IntakeSensors();
		
		switch1 = Robot.sensors.intakeSwitch1;
		switch2 = Robot.sensors.intakeSwitch2;
	}
	
	@Override
	public void initDefaultCommand()
	{}
	
	public void setMotor(double v) {
		motor.setMotor(v);
	}
	
	public boolean isGearIn() {
		return switch1.get() || switch2.get();
	}
	
}
