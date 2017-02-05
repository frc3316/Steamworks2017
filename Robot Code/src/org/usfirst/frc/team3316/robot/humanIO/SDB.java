/**
 * Class for managing the SmartDashboard data
 */
package org.usfirst.frc.team3316.robot.humanIO;

import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimerTask;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DriveDistance;
import org.usfirst.frc.team3316.robot.commands.StartCompressor;
import org.usfirst.frc.team3316.robot.commands.StopCompressor;
import org.usfirst.frc.team3316.robot.commands.intake.MoveIntake;
import org.usfirst.frc.team3316.robot.config.Config;
import org.usfirst.frc.team3316.robot.logger.DBugLogger;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.CameraServer;
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
			/*
			 * Insert put methods here
			 */
			put("Left Distance", Robot.chassis.getLeftDistance());
			put("Right Distance", Robot.chassis.getRightDistance());
			
			SmartDashboard.putBoolean("Brake Mode", ((CANTalon) Robot.actuators.chassisLeft1SC).getBrakeEnableDuringNeutral());
			
			// For drivers
			// TODO: Add "for-drivers" indications
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

	private CameraServer server;

	public SDB() {
		variablesInSDB = new Hashtable<String, Class<?>>();

		initLiveWindow();
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
		System.out.println("SDB is running");
		logger.info("SDB is running");

		SmartDashboard.putData(new UpdateVariablesInConfig()); // NEVER REMOVE
																// THIS COMMAND

		SmartDashboard.putData(new StartCompressor());
		SmartDashboard.putData(new StopCompressor());

		// Chassis

		putConfigVariableInSDB("chassis_SpeedFactor_Medium");
		putConfigVariableInSDB("chassis_SpeedFactor_Higher");
		putConfigVariableInSDB("chassis_SpeedFactor_Lower");
		
			// Drive-Distance PID
			putConfigVariableInSDB("chassis_DriveDistance_PID_Tolerance");
			putConfigVariableInSDB("chassis_DriveDistance_PID_KP");
			putConfigVariableInSDB("chassis_DriveDistance_PID_KI");
			putConfigVariableInSDB("chassis_DriveDistance_PID_KD");
			
			SmartDashboard.putData("Drive 1 meter", new DriveDistance(1.0));
			SmartDashboard.putData("Drive 3 meter", new DriveDistance(3.0));
		
		// Intake

		putConfigVariableInSDB("intake_MoveIntake_V");

		logger.info("Finished initSDB()");
	}

	/**
	 * This method puts in the live window of the test mode all of the robot's
	 * actuators and sensors. It is disgusting.
	 */
	public void initLiveWindow() {
		// logger.info("Finished initLiveWindow()");
	}
}