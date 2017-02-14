package org.usfirst.frc.team3316.robot.subsystems;

import org.usfirst.frc.team3316.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;

// TODO Find a new name for this subsystem
public class Installer extends DBugSubsystem {

    private DigitalInput switch1, switch2;

    public Installer() {
    	Robot.sensors.InstallerSensors();
    	
		switch1 = Robot.sensors.installerSwitch1;
		switch2 = Robot.sensors.installerSwitch2;
    }

    @Override
    public void initDefaultCommand() {}

    public boolean PegPushes() {
    	return switch1.get() || switch2.get();
    }
}
