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
    public DigitalInput gearSwitch;
    public DigitalInput pegSwitch;

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

    public void InstallerSensors() {
	gearSwitch = new DigitalInput((int) config.get("GEAR_SWITCH"));
	pegSwitch = new DigitalInput((int) config.get("PEG_SWITCH"));
    }
}
