/**
 * Al robot sensors
 */
package org.usfirst.frc.team3316.robot.robotIO;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.config.Config;
import org.usfirst.frc.team3316.robot.logger.DBugLogger;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;

public class Sensors {
	Config config = Robot.config;
	DBugLogger logger = Robot.logger;

	// General
	public PowerDistributionPanel pdp;

	// Chassis
	public AHRS navx;

	// Installer
	public DigitalInput installerSwitch1, installerSwitch2;

	public Sensors() {
	}

	/*
	 * General
	 */
	public void GeneralSensors() {
		pdp = new PowerDistributionPanel();
	}

	/*
	 * Chassis
	 */
	public void ChassisSensors() {
		try {
			navx = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
	}

	/*
	 * Installer
	 */
	public void InstallerSensors() {
		installerSwitch1 = new DigitalInput((int) config.get("INSTALLER_SWITCH1"));
		installerSwitch2 = new DigitalInput((int) config.get("INSTALLER_SWITCH2"));
	}
}
