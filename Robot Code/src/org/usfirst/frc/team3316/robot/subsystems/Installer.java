package org.usfirst.frc.team3316.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;

// TODO Find a new name for this subsystem
public class Installer extends DBugSubsystem {

    private DigitalInput gearSwitch;

    // TODO Finish class

    public Installer() {
	// TODO Finish

    }

    @Override
    public void initDefaultCommand() {
	// TODO ?
    }

    // TODO Finish method
    public boolean gearIsReadyToBeInstalled() {
	return gearSwitch.get();
    }
}
