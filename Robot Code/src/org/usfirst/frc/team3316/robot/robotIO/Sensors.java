/**
 * All robot sensors
 */
package org.usfirst.frc.team3316.robot.robotIO;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.config.Config;
import org.usfirst.frc.team3316.robot.logger.DBugLogger;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
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

    // Intake
    public AnalogInput intakeSwitch1, intakeSwitch2, intakeSwitch3;

    // Installer
    public AnalogInput installerSwitch1, installerSwitch2;

    // Climbing
    public AnalogInput climbingSwitch;

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
	installerSwitch1 = new AnalogInput((int) config.get("INSTALLER_SWITCH_1"));
	installerSwitch2 = new AnalogInput((int) config.get("INSTALLER_SWITCH_2"));
    }

    /*
     * Climbing
     */
    public void ClimbingSensors() {
	climbingSwitch = new AnalogInput((int) config.get("CLIMBING_SWITCH"));
    }

    /*
     * Intake
     */
    public void IntakeSensors() {
	intakeSwitch1 = new AnalogInput((int) config.get("INTAKE_SWITCH_1"));
	intakeSwitch2 = new AnalogInput((int) config.get("INTAKE_SWITCH_2"));
	intakeSwitch3 = new AnalogInput((int) config.get("INTAKE_SWITCH_3"));
    }
}
