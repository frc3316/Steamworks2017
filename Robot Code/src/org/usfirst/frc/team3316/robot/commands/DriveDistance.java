package org.usfirst.frc.team3316.robot.commands;

import java.sql.Time;

import org.usfirst.frc.team3316.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class DriveDistance extends DBugCommand {
	
	private PIDController pid;
	
    private double dist,initTime = 0, initDistance = 0;
	
	public DriveDistance(double distance) {
        requires(Robot.chassis);
        pid = new PIDController(0,0,0, new PIDSource()
		{
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource)
			{
				return;
			}
			
			@Override
			public double pidGet()
			{
				double currentDistance = Robot.chassis.getDistance() - initDistance;				
				return currentDistance;
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
				
				return;
			}
		}, 0.02);
        this.dist = distance;
        
    }

    // Called just before this Command runs the first time
    protected void init() {
    	pid.setPID((double) config.get("chassis_DriveDistance_PID_KP"),(double) config.get("chassis_DriveDistance_PID_KI"),(double) config.get("chassis_DriveDistance_PID_KD"));
    	pid.setOutputRange(-1.0, 1.0);
    	pid.setSetpoint(dist);
    	
    	initTime = Timer.getFPGATimestamp();
    	initDistance = Robot.chassis.getDistance();
    	
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double measuredspeed = Robot.chassis.getLeftSpeed() + Robot.chassis.getRightSpeed();
    	return measuredspeed == 0 && pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void fin() {
    	pid.reset();
    	pid.disable();
    	Robot.chassis.setMotors(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interr() {
    	fin();
    }
}
