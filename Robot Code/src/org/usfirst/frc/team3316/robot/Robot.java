
package org.usfirst.frc.team3316.robot;

import java.util.Timer;

import org.usfirst.frc.team3316.robot.commands.DBugCommand;
import org.usfirst.frc.team3316.robot.config.Config;
import org.usfirst.frc.team3316.robot.humanIO.Joysticks;
import org.usfirst.frc.team3316.robot.humanIO.SDB;
import org.usfirst.frc.team3316.robot.logger.DBugLogger;
import org.usfirst.frc.team3316.robot.robotIO.Actuators;
import org.usfirst.frc.team3316.robot.robotIO.Sensors;
import org.usfirst.frc.team3316.robot.subsystems.Chassis;
import org.usfirst.frc.team3316.robot.subsystems.Climbing;
import org.usfirst.frc.team3316.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static Config config;
	public static DBugLogger logger;
	public static Timer timer;

	/*
	 * Human IO
	 */
	public static Joysticks joysticks;
	public static SDB sdb;
	/*
	 * Robot IO
	 */
	public static Actuators actuators;
	public static Sensors sensors;
	/*
	 * Subsystems
	 */
	public static Chassis chassis;
	public static Intake intake;
	public static Climbing climbing;

	Command autonomousCommand;
	SendableChooser autonChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		/*
		 * Above all else
		 */
		try {
			logger = new DBugLogger();
			timer = new Timer();
			config = new Config();
			
			/*
			 * Human IO (that does not require subsystems)
			 */
			joysticks = new Joysticks();
			sdb = new SDB();

			/*
			 * Robot IO
			 */
			actuators = new Actuators();
			sensors = new Sensors();

			Robot.actuators.GeneralActuators();
			Robot.sensors.GeneralSensors();

			/*
			 * Subsystems
			 */
			chassis = new Chassis();
			intake = new Intake();
			climbing = new Climbing();
			
			/*
			 * Human IO (that requires subsystems)
			 */
			joysticks.initButtons();

			/*
			 * Timer
			 */
			sdb.timerInit();
		} catch (Exception e) {
			logger.severe(e);
		}
	}

	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	public void testIniwt() {
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
