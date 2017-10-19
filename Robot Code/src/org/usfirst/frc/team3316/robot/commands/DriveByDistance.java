package org.usfirst.frc.team3316.robot.commands;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.config.Config;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveByDistance extends DBugCommand {
    private PIDController pidController;
    private double distance;
    private double initDist;

    public DriveByDistance(double dist) {
	distance = dist;
	initDist = 0.0;
	
        pidController = new PIDController(0, 0, 0, new PIDSource() {
	    @Override
	    public void setPIDSourceType(PIDSourceType pidSource) {
		return;
	    }

	    @Override
	    public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	    }

	    @Override
	    public double pidGet() {
		double distance = Robot.chassis.getDistance();
		return distance - initDist;
	    }
        }, new PIDOutput() {
	    @Override
	    public void pidWrite(double output) {
		Robot.chassis.setMotors(output, output);
	    }
        });
    }

    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.pidController.onTarget();
    }

    @Override
    protected void init() {
	this.pidController.setSetpoint(this.distance);
	double p = (double) config.get("chassis_DriveByDistance_PID_KP") / 1000;
	double i = (double) config.get("chassis_DriveByDistance_PID_KI") / 1000;
	double d = (double) config.get("chassis_DriveByDistance_PID_KD") / 1000;
	
	this.pidController.setAbsoluteTolerance(0.01);
	
	initDist = Robot.chassis.getDistance();
	
	this.pidController.setPID(p, i, d);
	this.pidController.enable();
    }

    @Override
    protected void fin() {
	this.pidController.disable();
	Robot.chassis.setMotors(0, 0);
    }

    @Override
    protected void interr() {
	fin();	
    }
}
