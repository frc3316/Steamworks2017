package org.usfirst.frc.team3316.robot.subsystems;

import org.usfirst.frc.team3316.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;

// TODO Find a new name for this subsystem
public class Installer extends DBugSubsystem {

    private boolean mechanismIsSet;
    private DigitalInput gearSwitch, pegSwitch;

    // TODO Finish class

    public Installer() {
	// TODO Finish
	gearSwitch = Robot.sensors.gearSwitch;
	pegSwitch = Robot.sensors.pegSwitch;
    }

    @Override
    public void initDefaultCommand() {
    }

    public boolean gearIsInTheRightPosition() {
	return gearSwitch.get();
    }

    public boolean pegIsPushed() {
	return pegSwitch.get();
    }

    public boolean isMechanismSet() {
	return mechanismIsSet;
    }

    public void setMechanism(boolean isSet) {
	mechanismIsSet = isSet;
    }

    // TODO Finish method
    public boolean gearIsReadyToBeInstalled() {
	return gearIsInTheRightPosition();
    }
}
