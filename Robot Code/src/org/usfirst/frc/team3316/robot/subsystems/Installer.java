package org.usfirst.frc.team3316.robot.subsystems;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.utils.Utils;

import edu.wpi.first.wpilibj.AnalogInput;

// TODO Find a new name for this subsystem
public class Installer extends DBugSubsystem {

    private AnalogInput switch1, switch2;
    private double thresh;

    public Installer() {
	Robot.sensors.InstallerSensors();

	switch1 = Robot.sensors.installerSwitch1;
	switch2 = Robot.sensors.installerSwitch2;

	thresh = (double) Robot.config.get("INSTALLER_SWITCH_THRESH");
    }

    @Override
    public void initDefaultCommand() {
    }

    public boolean PegPushes() {
	return Utils.AnalogToDigitalInput(switch1, thresh) || Utils.AnalogToDigitalInput(switch2, thresh);
    }
}
