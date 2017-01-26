/**
 * Al robot sensors
 */
package org.usfirst.frc.team3316.robot.robotIO;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.config.Config;
import org.usfirst.frc.team3316.robot.logger.DBugLogger;
import org.usfirst.frc.team3316.robot.vision.VisionServer;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;

public class Sensors
{
	Config config = Robot.config;
	DBugLogger logger = Robot.logger;

	public PowerDistributionPanel pdp;

	// Chassis
	public AHRS navx;

	public Sensors()
	{
	}

	/*
	 * General
	 */
	public void GeneralSensors()
	{
		pdp = new PowerDistributionPanel();
	}
	
	/*
	 * Vision
	 */
	public void VisionSensors()
	{
		VisionServer visionServer = new VisionServer();
		Thread visionThread = new Thread(visionServer);
		visionThread.start();
	}

	/*
	 * Chassis
	 */
	public void ChassisSensors()
	{
		try
		{
			navx = new AHRS(SPI.Port.kMXP);
		}
		catch (RuntimeException ex)
		{
			DriverStation.reportError(
					"Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
	}
}
