
package org.usfirst.frc.team3316.robot;

import java.util.Timer;

import org.usfirst.frc.team3316.robot.auton.sequences.AutonPassLine;
import org.usfirst.frc.team3316.robot.auton.sequences.AutonPosition1;
import org.usfirst.frc.team3316.robot.auton.sequences.AutonPosition2;
import org.usfirst.frc.team3316.robot.auton.sequences.AutonPosition3;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;
import org.usfirst.frc.team3316.robot.commands.chassis.ChassisHighSpeed;
import org.usfirst.frc.team3316.robot.commands.chassis.CoastMode;
import org.usfirst.frc.team3316.robot.commands.intake.ReleaseIntakeExtender;
import org.usfirst.frc.team3316.robot.config.Config;
import org.usfirst.frc.team3316.robot.humanIO.Joysticks;
import org.usfirst.frc.team3316.robot.humanIO.SDB;
import org.usfirst.frc.team3316.robot.logger.DBugLogger;
import org.usfirst.frc.team3316.robot.robotIO.Actuators;
import org.usfirst.frc.team3316.robot.robotIO.Sensors;
import org.usfirst.frc.team3316.robot.subsystems.Chassis;
import org.usfirst.frc.team3316.robot.subsystems.Climbing;
import org.usfirst.frc.team3316.robot.subsystems.Intake;

import com.sun.org.apache.bcel.internal.generic.NEW;

import org.usfirst.frc.team3316.robot.subsystems.Installer;

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
	public static Installer installer;
	public static Climbing climbing;
	public static Intake intake;
	/*
	 * Auton
	 */
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
			installer = new Installer();

			/*
			 * Human IO (that requires subsystems)
			 */
			sdb = new SDB();

			/*
			 * Human IO (that requires subsystems)
			 */
			joysticks.initButtons();

			/*
			 * Timer
			 */
			sdb.timerInit();

			/*
			 * Choosers
			 */
			autonChooser = new SendableChooser();
			autonChooser.addObject("Empty Auton", new DBugCommand() {
				protected boolean isFinished() {
					return false;
				}

				protected void interr() {
				}

				protected void init() {
				}

				protected void fin() {
				}

				protected void execute() {
				}
			});
			autonChooser.addObject("Position 1", new AutonPosition1());
			autonChooser.addDefault("Position 2", new AutonPosition2());
			autonChooser.addObject("Position 3", new AutonPosition3());
			autonChooser.addObject("Pass line", new AutonPassLine());
			SmartDashboard.putData("Auton Chooser", autonChooser); // After SDB initialized

		} catch (Exception e) {
			logger.severe(e);
		}
	}

	public void disabledInit() {
		chassis.setBrake(false);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();

		chassis.setBrake(false);
	}

	public void autonomousInit() {
		(new ReleaseIntakeExtender()).start();
		if (autonChooser.getSelected() != null) {
		    ((Command) autonChooser.getSelected()).start();
		}
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		chassis.setBrake(true);
		(new ChassisHighSpeed()).start();
		
		if (autonChooser.getSelected() != null) {
		    ((Command) autonChooser.getSelected()).cancel();
		}
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	public void testInit() {
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
