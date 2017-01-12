package org.usfirst.frc.team3316.robot.commands;

import org.usfirst.frc.team3316.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 *
 */
public class DriveByCamera extends DBugCommand
{

	private PIDController pid;

	public DriveByCamera(double dist)
	{
		requires(Robot.chassis);
		pid = new PIDController(0, 0, 0, new PIDSource()
				{

					@Override
					public void setPIDSourceType(PIDSourceType pidSource)
					{
						// TODO Auto-generated method stub
						return;
					}

					@Override
					public double pidGet()
					{
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public PIDSourceType getPIDSourceType()
					{
						// TODO Auto-generated method stub
						return null;
					}
				}, new PIDOutput()
				{

					@Override
					public void pidWrite(double output)
					{
						// TODO Auto-generated method stub
						return;
					}
				});

	}

	protected void init()
	{
		pid.setPID((double) config.get("chassis_DriveByCamera_PID_KP"),
				(double) config.get("chassis_DriveByCamera_PID_KI"),
				(double) config.get("chassis_DriveByCamera_PID_KD"));
    	pid.setOutputRange(-1.0, 1.0);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	protected void fin()
	{
		pid.reset();
		pid.disable();
		Robot.chassis.setMotors(0, 0);
	}
	
	protected void interr()
	{
		fin();
	}

	

}
