package org.usfirst.frc.team3316.robot.subsystems;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.robotIO.DBugSpeedController;
import org.usfirst.frc.team3316.robot.utils.Utils;

import edu.wpi.first.wpilibj.AnalogInput;

public class Intake extends DBugSubsystem {
    private DBugSpeedController motor;
    private AnalogInput switch1, switch2, switch3;
    private double thresh;

    public Intake() {
	// Actuators
	Robot.actuators.IntakeActuators();

	motor = Robot.actuators.intakeMotor;

	// Sensors
	Robot.sensors.IntakeSensors();

	switch1 = Robot.sensors.intakeSwitch1;
	switch2 = Robot.sensors.intakeSwitch2;
	switch3 = Robot.sensors.intakeSwitch3;

	thresh = (double) Robot.config.get("INTAKE_SWITCH_THRESH");

    }

    @Override
    public void initDefaultCommand() {
    }

    public void setMotor(double v) {
	motor.setMotor(v);
    }

    public boolean isGearIn() {
	return Utils.AnalogToDigitalInput(switch1, thresh) || Utils.AnalogToDigitalInput(switch2, thresh)
		|| Utils.AnalogToDigitalInput(switch3, thresh);
    }

}
