package org.usfirst.frc.team3316.robot.vision;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.config.Config;
import org.usfirst.frc.team3316.robot.logger.DBugLogger;
import org.usfirst.frc.team3316.robot.utils.Utils;

public class AlignDrivetrain
{
	private static DBugLogger logger;
	private static Config config;
	
	private static double [][] hoodTable;

	static
	{
		logger = Robot.logger;
		config = Robot.config;
	}

	public static double getLiftAngle()
	{
		double liftAngle = VisionServer.Data.get("AA"); // AA = Azimuthal Angle
		return liftAngle;
	}

	/**
	 * This returns the DFC
	 */
	public static double getDistanceFromTower()
	{
		double distance = VisionServer.Data.get("DFC"); // DFC = Distance From
		// Camera
		return distance;
	}

	public static boolean isObjectDetected()
	{
		try
		{
			return VisionServer.Data.get("IOD") == 1.0;
		}
		catch (Exception e)
		{
//			logger.severe(e);
			return false;
		}
	}
}
