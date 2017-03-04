
import java.util.Hashtable;

public class Config {
	public static Hashtable<String, Object> variablesB;
	public static Hashtable<String, Object> constantsB;

	public static Hashtable<String, Object> variablesA;
	public static Hashtable<String, Object> constantsA;

	private Config() {
	}

	static {
		variablesB = new Hashtable<String, Object>();
		constantsB = new Hashtable<String, Object>();

		variablesA = new Hashtable<String, Object>();
		constantsA = new Hashtable<String, Object>();

		initConfig();
		IO.initIO();
	}

	public static void addToConstantsA(String key, Object value) {
		System.out.println("Trying to add to constants A: key " + key + " value " + value);

		if (constantsA.containsKey(key)) {
			constantsA.replace(key, value);
		} else {
			constantsA.put(key, value);
		}
	}

	public static void addToVariablesA(String key, Object value) {
		System.out.println("Trying to add to variables A: key " + key + " value " + value);

		if (variablesA.containsKey(key)) {
			variablesA.replace(key, value);
		} else {
			variablesA.put(key, value);
		}
	}

	public static void addToConstantsB(String key, Object value) {
		System.out.println("Trying to add to constants B: key " + key + " value " + value);

		if (constantsB.containsKey(key)) {
			constantsB.replace(key, value);
		} else {
			constantsB.put(key, value);
		}
	}

	public static void addToVariablesB(String key, Object value) {
		System.out.println("Trying to add to variables B: key " + key + " value " + value);

		if (variablesB.containsKey(key)) {
			variablesB.replace(key, value);
		} else {
			variablesB.put(key, value);
		}
	}

	public static void addToConstants(String key, Object value) {
		addToConstantsA(key, value);
		addToConstantsB(key, value);
	}

	public static void addToVariables(String key, Object value) {
		addToVariablesA(key, value);
		addToVariablesB(key, value);
	}

	/*
	 * NOTE: constants and variables that are common to both robot A and robot B
	 * should be added with addToConstants() or addToVariables()
	 * 
	 * Use different constants and variables for the two robots only if there is
	 * a difference. TestModeStuff
	 */
	private static void initConfig() {
		/*
		 * Human IO
		 */
		{
			/*
			 * Constants
			 */
			{
				/*
				 * Joysticks
				 */
				{
					addToConstants("JOYSTICK_LEFT", 0);
					addToConstants("JOYSTICK_RIGHT", 1);
					addToConstants("JOYSTICK_OPERATOR", 2);
				}

				/*
				 * Buttons
				 */
				{
					// Joystick operator

					addToVariables("button_Intake_Toggle", 5);

					addToVariables("button_Climbing_Toggle", 6);

					addToVariables("button_Chassis_Break_Toggle", 1);
					addToVariables("button_Chassis_LowerSpeed", 7);
					addToVariables("button_Chassis_HigherSpeed", 8);
				}
			}
		}

		/*
		 * RobotIO
		 */
		{
			/*
			 * Constants
			 */
			addToConstants("CURRENT_CONTROL_COUNTER", 10);

			{
				/*
				 * Chassis
				 */
				addToConstantsA("CHASSIS_MOTOR_LEFT_REVERSE", false);
				addToConstantsA("CHASSIS_MOTOR_RIGHT_REVERSE", true);

				addToVariables("chassis_Joystick_Right_Axis", 1);
				addToVariables("chassis_Joystick_Left_Axis", 5);

				addToConstants("CHASSIS_LEFT_ENCODER_REVERSE", false);
				addToConstants("CHASSIS_RIGHT_ENCODER_REVERSE", true);

				addToConstants("CHASSIS_ENCODERS_DISTANCE_PER_PULSE", 0.00124224); // in
				// meters

				/*
				 * Intake
				 */
				addToConstantsA("INTAKE_MOTOR_REVERSE", false);

				/*
				 * Climbing
				 */
				addToConstantsA("CLIMBING_MOTOR_REVERSE", false);
			}
		}

		/*
		 * Chassis
		 */
		{
			/*
			 * Constants
			 */
			{
			}

			/*
			 * Variables
			 */
			{
				addToVariables("chassis_TankDrive_DeadBand", 0.05);

				addToVariables("chassis_SpeedFactor_Medium", -0.9);
				addToVariables("chassis_SpeedFactor_Higher", -1.0);
				addToVariables("chassis_SpeedFactor_Lower", -0.5);

				addToVariables("chassis_TankDrive_InvertX", true);
				addToVariables("chassis_TankDrive_InvertY", false);
			}

			/*
			 * Drive Distance
			 */
			{
				addToVariables("chassis_DriveDistance_PID_Tolerance", 0.025);

				// PID
				// BY CAMERA
				addToVariables("chassis_DriveByCamera_PID_KP", 0.0);
				addToVariables("chassis_DriveByCamera_PID_KI", 0.0);
				addToVariables("chassis_DriveByCamera_PID_KD", 0.0);

				// BY ENCODERS
				{
					// Right
					addToVariables("chassis_DriveDistance_PID_RIGHT_KP", 0.0);
					addToVariables("chassis_DriveDistance_PID_RIGHT_KI", 0.0);
					addToVariables("chassis_DriveDistance_PID_RIGHT_KD", 0.0);
					// Left
					addToVariables("chassis_DriveDistance_PID_LEFT_KP", 0.0);
					addToVariables("chassis_DriveDistance_PID_LEFT_KI", 0.0);
					addToVariables("chassis_DriveDistance_PID_LEFT_KD", 0.0);
					
					// Yaw
					addToVariables("chassis_DriveDistance_PID_YAW_KP", 95.0);
					addToVariables("chassis_DriveDistance_PID_YAW_KI", 0.0);
					addToVariables("chassis_DriveDistance_PID_YAW_KD", 0.0);
					
					{
						// Long range

						// Right
						addToVariables("chassis_DriveDistanceLongRange_PID_RIGHT_KP", 125.0);
						addToVariables("chassis_DriveDistanceLongRange_PID_RIGHT_KI", 1.0);
						addToVariables("chassis_DriveDistanceLongRange_PID_RIGHT_KD", 3.0);
						// Left
						addToVariables("chassis_DriveDistanceLongRange_PID_LEFT_KP", 55.0);
						addToVariables("chassis_DriveDistanceLongRange_PID_LEFT_KI", 1.0);
						addToVariables("chassis_DriveDistanceLongRange_PID_LEFT_KD", 3.0);

						// Short range

						// Right
						addToVariables("chassis_DriveDistanceShortRange_PID_RIGHT_KP", 850.0);
						addToVariables("chassis_DriveDistanceShortRange_PID_RIGHT_KI", 1.0);
						addToVariables("chassis_DriveDistanceShortRange_PID_RIGHT_KD", 0.0);
						// Left
						addToVariables("chassis_DriveDistanceShortRange_PID_LEFT_KP", 850.0);
						addToVariables("chassis_DriveDistanceShortRange_PID_LEFT_KI", 1.0);
						addToVariables("chassis_DriveDistanceShortRange_PID_LEFT_KD", 0.0);
					}
				}
			}

			/*
			 * TURN BY GYRO
			 */
			{
				// PID
				addToVariables("chassis_TurnByGyro_PID_Tolerance", 1.0);

				addToVariables("chassis_TurnByGyro_PID_KP", 75.0);
				addToVariables("chassis_TurnByGyro_PID_KI", 0.7);
				addToVariables("chassis_TurnByGyro_PID_KD", 5.0);
			}

			/*
			 * Set Constant Voltage
			 */
			{
				addToVariables("chassis_SetConstantVoltage_Voltage", 0.0);
			}

		}

		/*
		 * Intake
		 */
		{
			/*
			 * Constants
			 */
			{
				addToConstants("INTAKE_SWITCH_THRESH", 100.0);
				addToConstants("INTAKE_SERVO_ANGLE", 10.0);
			}

			/*
			 * Variables
			 */
			{
				addToVariables("intake_MoveIntake_V", 1.0);
				addToVariables("intake_RollIn_Voltage", 1.0);
			}
		}

		/*
		 * Installer
		 */
		{
			/*
			 * Constants
			 */
			{
				addToConstants("INSTALLER_SWITCH_THRESH", 100.0);
			}

			/*
			 * Variables
			 */
			{
			}
		}

		/*
		 * Climbing
		 */
		{
			/*
			 * Constants
			 */
			{
				addToConstants("CLIMBING_SWITCH_THRESH", 100.0); // SWITCH DOES
				// NOT
				// EXIST
				addToConstants("CLIMBING_CURRENT_THRESH", 0.0); // TODO: Measure
																// the current
			}

			/*
			 * Variables
			 */
			{
				addToVariables("climbing_Up_Voltage", 0.8);
				addToVariables("climbing_Down_Voltage", -0.8);
			}
		}
	}
}
