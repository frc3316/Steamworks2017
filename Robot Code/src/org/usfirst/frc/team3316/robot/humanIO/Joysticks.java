/**
 * Class for joysticks and joystick buttons
 */
package org.usfirst.frc.team3316.robot.humanIO;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.intake.IntakeEmptyCommand;
import org.usfirst.frc.team3316.robot.commands.chassis.BrakeMode;
import org.usfirst.frc.team3316.robot.commands.chassis.CoastMode;
import org.usfirst.frc.team3316.robot.commands.climbing.ClimbingStop;
import org.usfirst.frc.team3316.robot.commands.climbing.ClimbingUp;
import org.usfirst.frc.team3316.robot.commands.intake.MoveIntake;
import org.usfirst.frc.team3316.robot.config.Config;
import org.usfirst.frc.team3316.robot.logger.DBugLogger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class Joysticks
{
	/*
	 * Defines a button in a gamepad POV for an array of angles
	 */
	private class POVButton extends Button
	{
		Joystick m_joystick;
		int m_deg;

		public POVButton(Joystick joystick, int deg)
		{
			m_joystick = joystick;
			m_deg = deg;
		}

		public boolean get()
		{
			if (m_joystick.getPOV() == m_deg)
			{
				return true;
			}
			return false;
		}
	}

	Config config = Robot.config;
	DBugLogger logger = Robot.logger;

	public Joystick joystickLeft, joystickRight, joystickOperator;
	public DBugJoystickButton lowerSpeedBtn, higherSpeedBtn;

	/**
	 * Initializes the joysticks.
	 */
	public Joysticks()
	{
		joystickLeft = new Joystick((int) Robot.config.get("JOYSTICK_LEFT"));
		joystickRight = new Joystick((int) Robot.config.get("JOYSTICK_RIGHT"));
		joystickOperator = new Joystick((int) Robot.config.get("JOYSTICK_OPERATOR"));
	}

	/**
	 * Initializes the joystick buttons. This is done separately because they
	 * usually require the subsystems to be already instantiated.
	 */
	public void initButtons()
	{
		// TODO: Add buttons after creating subsystems
		
		DBugJoystickButton toggleIntakeBtn = new DBugJoystickButton(joystickOperator, "button_Intake_Toggle");
		toggleIntakeBtn.whenPressed(new DBugToggleCommand(new MoveIntake(), new IntakeEmptyCommand()));
		
		DBugJoystickButton toggleChassisBrakeMode = new DBugJoystickButton(joystickOperator, "button_Chassis_Break_Toggle");

		toggleChassisBrakeMode.whenPressed(new DBugToggleCommand(new BrakeMode(), new CoastMode()));
		
		lowerSpeedBtn = new DBugJoystickButton(joystickOperator, "button_Chassis_LowerSpeed");
		higherSpeedBtn = new DBugJoystickButton(joystickOperator, "button_Chassis_HigherSpeed");
		
		DBugJoystickButton toggleClimbingButton = new DBugJoystickButton(joystickOperator, "button_Climbing_Toggle");
		toggleClimbingButton.whenPressed(new DBugToggleCommand(new ClimbingUp(), new ClimbingStop()));
	}
}
