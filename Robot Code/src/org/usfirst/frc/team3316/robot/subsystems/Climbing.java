package org.usfirst.frc.team3316.robot.subsystems;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.climbing.ClimbingAxis;
import org.usfirst.frc.team3316.robot.robotIO.DBugSpeedController;
import org.usfirst.frc.team3316.robot.utils.Utils;

import edu.wpi.first.wpilibj.AnalogInput;

public class Climbing extends DBugSubsystem {
    // Actuators
    private DBugSpeedController motor;

    // Sensors
    private AnalogInput limitSwitch;
    double thresh;

    public Climbing() {
	// Actuators
	Robot.actuators.ClimbingActuators();

	motor = Robot.actuators.climbingMotor;

	// Sensors
	Robot.sensors.ClimbingSensors();

	limitSwitch = Robot.sensors.climbingSwitch;
	thresh = (double) Robot.config.get("INSTALLER_SWITCH_THRESH");
    }

    public void initDefaultCommand() {
    }

    public void setMotor(double v) {
	motor.setMotor(v);
    }

    public boolean isClimbingRaising() {
	return Utils.AnalogToDigitalInput(limitSwitch, thresh);
    }

    public boolean isRobotClimbing() {
	return motor.getCurrent() > (double) config.get("CLIMBING_CURRENT_THRESH");
    }

    public boolean isRollingIn() {
	return motor.getVoltage() != 0.0;
    }
}
