package org.usfirst.frc.team3316.robot.commands.chassis;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class DriveBySpeed extends DBugCommand {

    private Joystick joystickOperator;
    private boolean invertY;
    private double deadBand = 0.0, voltageLeft = 0, voltageRight = 0, maxSpeedLeft = 0, maxSpeedRight = 0;
    private PIDController pidSpeedLeft, pidSpeedRight;

    public DriveBySpeed() {
	requires(Robot.chassis);

	joystickOperator = Robot.joysticks.joystickOperator;
    }

    private void initPIDSpeedLeft() {
	pidSpeedLeft = new PIDController(0, 0, 0, new PIDSource() {
	    public void setPIDSourceType(PIDSourceType pidSource) {
		return;
	    }

	    public double pidGet() {
		return Robot.chassis.getLeftSpeed();
	    }

	    public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kRate;
	    }
	}, new PIDOutput() {

	    public void pidWrite(double output) {
		voltageLeft = output;
	    }
	}, 0.02);
    }

    private void initPIDSpeedRight() {
	pidSpeedRight = new PIDController(0, 0, 0, new PIDSource() {
	    public void setPIDSourceType(PIDSourceType pidSource) {
		return;
	    }

	    public double pidGet() {
		return Robot.chassis.getRightSpeed();
	    }

	    public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kRate;
	    }
	}, new PIDOutput() {

	    public void pidWrite(double output) {
		voltageRight = output;
	    }
	}, 0.02);
    }

    @Override
    protected void init() {
	maxSpeedLeft = (double) config.get("chassis_DriveBySpeed_HighestSpeedLeft");
	maxSpeedRight = (double) config.get("chassis_DriveBySpeed_HighestSpeedRight");

	initPIDSpeedLeft();
	initPIDSpeedRight();

	pidSpeedLeft.setOutputRange(-1, 1);
	pidSpeedLeft.setInputRange(-maxSpeedLeft, maxSpeedLeft);

	pidSpeedRight.setOutputRange(-1, 1);
	pidSpeedRight.setInputRange(-maxSpeedRight, maxSpeedRight);

	pidSpeedLeft.setPID((double) config.get("chassis_DriveBySpeed_PID_Left_KP") / 1000,
		(double) config.get("chassis_DriveBySpeed_PID_Left_KI") / 1000,
		(double) config.get("chassis_DriveBySpeed_PID_Left_KD") / 1000);

	pidSpeedRight.setPID((double) config.get("chassis_DriveBySpeed_PID_Right_KP") / 1000,
		(double) config.get("chassis_DriveBySpeed_PID_Right_KI") / 1000,
		(double) config.get("chassis_DriveBySpeed_PID_Right_KD") / 1000);

	pidSpeedLeft.setSetpoint(0.0);
	pidSpeedRight.setSetpoint(0.0);

	pidSpeedLeft.enable();
	pidSpeedRight.enable();
    }

    protected double getYLeft() {
	updateConfigVariables();
	double y = deadBand(joystickOperator.getRawAxis((int) config.get("chassis_Joystick_Left_Axis")));
	if (invertY) {
	    return -y;
	}
	return y;
    }

    protected double getYRight() {
	updateConfigVariables();
	double y = deadBand(joystickOperator.getRawAxis((int) config.get("chassis_Joystick_Right_Axis")));
	if (invertY) {
	    return -y;
	}
	return y;
    }

    @Override
    protected void execute() {
	// TODO Auto-generated method stub
	double leftValue = getYLeft();
	double rightValue = getYRight();

	pidSpeedLeft.setSetpoint(leftValue * maxSpeedLeft);
	pidSpeedRight.setSetpoint(rightValue * maxSpeedRight);

	Robot.chassis.setMotors(voltageLeft, voltageRight);
    }

    @Override
    protected boolean isFinished() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    protected void fin() {
	pidSpeedLeft.reset();
	pidSpeedLeft.disable();

	pidSpeedRight.reset();
	pidSpeedRight.disable();

	Robot.chassis.setMotors(0, 0);
    }

    @Override
    protected void interr() {
	fin();
    }

    // Utils

    /*
     * Here we call the get method of the config every execute because we want
     * the variables to update without needing to cancel the commands.
     */
    private void updateConfigVariables() {
	deadBand = (double) config.get("chassis_TankDrive_DeadBand");

	invertY = (boolean) config.get("chassis_TankDrive_InvertY");
    }

    private double deadBand(double x) {
	if (Math.abs(x) < deadBand) {
	    return 0;
	}
	return x;
    }
}
