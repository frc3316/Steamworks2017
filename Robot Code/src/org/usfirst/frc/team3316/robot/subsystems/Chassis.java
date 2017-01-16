package org.usfirst.frc.team3316.robot.subsystems;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.chassis.TankDrive;
import org.usfirst.frc.team3316.robot.robotIO.DBugSpeedController;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Chassis extends DBugSubsystem
{
	// Actuators
	private DBugSpeedController leftMotor1, rightMotor2, leftMotor2, rightMotor1;

	// Sensors
	private AHRS navx; // For the navX
//	private Encoder leftEncoder; // TODO: Remove comment-out after installing encoders
//	private Encoder rightEncoder; // TODO: Remove comment-out after installing encoders

	// Variables
	private double pitchOffset, rollOffset;

	
	public Chassis()
	{
		// Actuators
		Robot.actuators.ChassisActuators();
		
		leftMotor1 = Robot.actuators.chassisLeft1;
		rightMotor2 = Robot.actuators.chassisRight2;
		leftMotor2 = Robot.actuators.chassisLeft2;
		rightMotor1 = Robot.actuators.chassisRight1;

		// Sensors
		Robot.sensors.ChassisSensors();
		
		navx = Robot.sensors.navx;
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new TankDrive());
	}

	/*
	 * Motor methods
	 */
	public void setMotors(double left, double right)
	{
		logger.finest("Setting chassis. left: " + left + ", right: " + right);
		
		// Validate the given values are in the range
		left = normalizeInput(left);
		right = normalizeInput(right);
		
		leftMotor1.setMotor(left);
		leftMotor2.setMotor(left);

		rightMotor1.setMotor(right);
		rightMotor2.setMotor(right);
	}
	
	/**
	 * This method normalizes the input for the speed controller
	 * @param input
	 * @return the normalized input
	 */
	public double normalizeInput(double input) {
		input = Math.max(0.0, input);
		input = Math.min(input, 1.0);
		
		return input;
	}

	/*
	 * Methods related to NavX angle
	 */

	public double getPitch()
	{
		return navx.getRoll() + pitchOffset;
	}

	public double getRoll()
	{
		return navx.getPitch() + rollOffset;
	}
	
	public void resetPitch() 
	{
		pitchOffset = pitchOffset - getPitch();
		SmartDashboard.putNumber("Pitch offset", pitchOffset);
	}
	
	public void resetRoll() 
	{
		rollOffset = rollOffset - getRoll();
		SmartDashboard.putNumber("Roll offset", rollOffset);
	}

	public double getYaw()
	{
		return fixYaw(navx.getYaw());
	}

	// Returns the same heading in the range (-180) to (180)
	private static double fixYaw(double heading)
	{
		double toReturn = heading % 360;

		if (toReturn < -180)
		{
			toReturn += 360;
		}
		else if (toReturn > 180)
		{
			toReturn -= 360;
		}
		return toReturn;
	}

	/*
	 * Encoder Methods
	 */
	public double getLeftDistance()
	{
//		return leftEncoder.getDistance(); // TODO: Remove comment-out after installing encoders
		return 0.0;
	}

	public double getRightDistance()
	{
//		return rightEncoder.getDistance(); // TODO: Remove comment-out after installing encoders
		return 0.0;
	}
//
//	public double getLeftSpeed()
//	{
//		return leftEncoder.getRate(); // Returns the speed in meter per
//										// second units.
//	}
//
//	public double getRightSpeed()
//	{
//		return rightEncoder.getRate(); // Returns the speed in meter per
//										// second units.
//	}
//
	public double getDistance()
	{
//		return (rightEncoder.getDistance() + leftEncoder.getDistance()) / 2; // TODO: Remove comment-out after installing encoders
		return 0.0;
	}

	public void resetEncoders()
	{
//		rightEncoder.reset();
//		leftEncoder.reset();
	}
}
