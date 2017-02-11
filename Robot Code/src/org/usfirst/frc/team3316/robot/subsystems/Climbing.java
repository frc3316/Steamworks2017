package org.usfirst.frc.team3316.robot.subsystems;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.chassis.TankDrive;
import org.usfirst.frc.team3316.robot.commands.chassis.TankDriveXbox;
import org.usfirst.frc.team3316.robot.robotIO.DBugSpeedController;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climbing extends DBugSubsystem
{
	// Actuators
	private DBugSpeedController motor;
	
	// Sensors
	private DigitalInput limitSwitch;
	
	public Climbing()
	{
		// Actuators
		Robot.actuators.ClimbingActuators();
		
		motor = Robot.actuators.climbingMotor;

		// Sensors
		Robot.sensors.ClimbingSensors();
		
		limitSwitch = Robot.sensors.climbingSwitch;
	}

	public void initDefaultCommand()
	{}
	
	public void setMotor(double v)
	{
		motor.setMotor(v);
	}
	
	public boolean isClimbingRaising() {
		return limitSwitch.get();
	}
}
