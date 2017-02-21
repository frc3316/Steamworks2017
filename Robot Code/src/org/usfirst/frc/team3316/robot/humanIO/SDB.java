/**
 * Class for managing the SmartDashboard data
 */
package org.usfirst.frc.team3316.robot.humanIO;

import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimerTask;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistanceLongRange;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistanceShortRange;
import org.usfirst.frc.team3316.robot.auton.sequences.AutonPosition2;
import org.usfirst.frc.team3316.robot.commands.StartCompressor;
import org.usfirst.frc.team3316.robot.commands.StopCompressor;
import org.usfirst.frc.team3316.robot.config.Config;
import org.usfirst.frc.team3316.robot.logger.DBugLogger;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SDB {
    /*
     * Runnable that periodically updates values from the robot into the
     * SmartDashboard This is the place where all of the robot data should be
     * displayed from
     */
    private class UpdateSDBTask extends TimerTask {
	public UpdateSDBTask() {
	    logger.info("Created UpdateSDBTask");
	}

	public void run() {
	    logger.info("SDB is running");
	    /*
	     * Insert put methods here
	     */

	    // For drivers


	    put("Distance Right a", Robot.chassis.getRightDistance());
	    put("Distance Left a", Robot.chassis.getLeftDistance());
	    put("Yaw Angle a", Robot.chassis.getYaw());
	    
	    // Chassis
	    put("Brake mode", ((CANTalon) Robot.actuators.chassisLeft1SC).getBrakeEnableDuringNeutral());

	    // // Intake
	     put("Is Gear In", Robot.intake.isGearIn());
	    //
	    // // Installer
	     put("Is Peg Pushing", Robot.installer.isPegPushing());
	    //
	     // Climbing
	     put("Climbing Current",
	     Robot.actuators.climbingMotor.getCurrent());
	     put("Climbing Voltage",
	     Robot.actuators.climbingMotor.getVoltage());
	    
	     // Other
	     put("Battery Voltage", Robot.sensors.pdp.getVoltage());
	    
//	    
//	    put("Pulses difference", Robot.sensors.chassisLeftEncoder.getRaw() - Robot.sensors.chassisRightEncoder.getRaw());
	}

	private void put(String name, double d) {
	    SmartDashboard.putNumber(name, d);
	}

	private void put(String name, int i) {
	    SmartDashboard.putNumber(name, i);
	}

	private void put(String name, boolean b) {
	    SmartDashboard.putBoolean(name, b);
	}

	private void put(String name, String s) {
	    SmartDashboard.putString(name, s);
	}
    }

    DBugLogger logger = Robot.logger;
    Config config = Robot.config;

    private UpdateSDBTask updateSDBTask;

    private Hashtable<String, Class<?>> variablesInSDB;

    public SDB() {
	variablesInSDB = new Hashtable<String, Class<?>>();

	// initLiveWindow();
	initSDB();
	// initDriverCamera();
    }

    public void timerInit() {
	updateSDBTask = new UpdateSDBTask();
	Robot.timer.schedule(updateSDBTask, 0, 10);
    }

    /**
     * Adds a certain key in the config to the SmartDashboard
     * 
     * @param key
     *            the key required
     * @return whether the value was put in the SmartDashboard
     */
    public boolean putConfigVariableInSDB(String key) {
	Object value = config.get(key);
	if (value != null) {
	    Class<?> type = value.getClass();

	    boolean constant = Character.isUpperCase(key.codePointAt(0))
		    && Character.isUpperCase(key.codePointAt(key.length() - 1));

	    if (type == Double.class) {
		SmartDashboard.putNumber(key, (double) value);
	    } else if (type == Integer.class) {
		SmartDashboard.putNumber(key, (int) value);
	    } else if (type == Boolean.class) {
		SmartDashboard.putBoolean(key, (boolean) value);
	    }

	    if (!constant) {
		variablesInSDB.put(key, type);
		logger.info("Added to SDB " + key + " of type " + type + " and allows for its modification");
	    } else {
		logger.info("Added to SDB " + key + " of type " + type + " BUT DOES NOT ALLOW for its modification");
	    }

	    return true;
	}

	return false;
    }

    public Set<Entry<String, Class<?>>> getVariablesInSDB() {
	return variablesInSDB.entrySet();
    }

    private void initSDB() {
	SmartDashboard.putData(new UpdateVariablesInConfig()); // NEVER REMOVE
							       // THIS COMMAND

	SmartDashboard.putData(new StartCompressor());
	SmartDashboard.putData(new StopCompressor());

	// // Intake
	// putConfigVariableInSDB("intake_MoveIntake_V");
	//
	// // Cameras
	 CameraServer.getInstance().startAutomaticCapture("cam0", 0);
	 CameraServer.getInstance().startAutomaticCapture("cam1", 1);

	// Autonomous

//	SmartDashboard.putData("Drive 1.5m", new DriveDistanceOvershoot(1.5));
//	SmartDashboard.putData("Set Angle to 50 deg", new SetAngle(50.0));
//	
	
//	SmartDashboard.putData(new ClimbingDown());
	
	SmartDashboard.putData("Drive 1.95m", new DriveDistanceLongRange(1.95, 1.95));
	SmartDashboard.putData("Drive -0.4m", new DriveDistanceShortRange(-0.4, -0.4));
	
	SmartDashboard.putData("position2", new AutonPosition2());

	logger.info("Finished initSDB()");
    }

    /**
     * This method puts in the live window of the test mode all of the robot's
     * actuators and sensors. It is disgusting.
     */
    public void initLiveWindow() {
	initLiveWindowActuators();
	initLiveWindowSensors();

	logger.info("Finished initLiveWindow()");
    }

    private void initLiveWindowActuators() {
	// TODO: Check if casting to LiveWindowSendable actually works
	// General
	LiveWindow.addActuator("General", "compressor", Robot.actuators.compressor);
	// Chassis
	LiveWindow.addActuator("Chassis", "chassisLeft1SC", (LiveWindowSendable) Robot.actuators.chassisLeft1SC);
	LiveWindow.addActuator("Chassis", "chassisLeft2SC", (LiveWindowSendable) Robot.actuators.chassisLeft2SC);
	LiveWindow.addActuator("Chassis", "chassisRight1SC", (LiveWindowSendable) Robot.actuators.chassisRight1SC);
	LiveWindow.addActuator("Chassis", "chassisRight2SC", (LiveWindowSendable) Robot.actuators.chassisRight2SC);
	// Intake
	LiveWindow.addActuator("Intake", "intakeMotorSC", (LiveWindowSendable) Robot.actuators.intakeMotorSC);
	// Climbing
	LiveWindow.addActuator("Climbing", "climbingMotorSC", (LiveWindowSendable) Robot.actuators.climbingMotorSC);
	logger.info("Finished initLiveWindowActuators()");
    }

    private void initLiveWindowSensors() {
	// General
	LiveWindow.addSensor("General", "pdp", Robot.sensors.pdp);
	// Chassis
	LiveWindow.addSensor("Chassis", "navx", Robot.sensors.navx);
	// TODO: Add encoders
	// Intake
	LiveWindow.addSensor("Intake", "intakeSwitch1", Robot.sensors.intakeSwitch1);
	LiveWindow.addSensor("Intake", "intakeSwitch2", Robot.sensors.intakeSwitch2);
	LiveWindow.addSensor("Intake", "intakeSwitch3", Robot.sensors.intakeSwitch3);
	// Installer
	LiveWindow.addSensor("Installer", "installerSwitch1", Robot.sensors.installerSwitch1);
	LiveWindow.addSensor("Installer", "installerSwitch2", Robot.sensors.installerSwitch2);
	// Climbing
	LiveWindow.addSensor("Climbing", "climbingSwitch", Robot.sensors.climbingSwitch);
	logger.info("Finished initLiveWindowSensors()");
    }
}