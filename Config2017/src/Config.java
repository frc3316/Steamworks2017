
import java.util.Hashtable;

public class Config 
{
	public static Hashtable<String, Object> variablesB;
	public static Hashtable<String, Object> constantsB;

	public static Hashtable<String, Object> variablesA;
	public static Hashtable<String, Object> constantsA;

	private Config() {}

	static 
	{
		variablesB = new Hashtable<String, Object>();
		constantsB = new Hashtable<String, Object>();

		variablesA = new Hashtable<String, Object>();
		constantsA = new Hashtable<String, Object>();

		initConfig();
		IO.initIO();
	}

	public static void addToConstantsA(String key, Object value) 
	{
		System.out.println("Trying to add to constants A: key " + key + " value " + value);

		if (constantsA.containsKey(key)) 
		{
			constantsA.replace(key, value);
		} 
		else 
		{
			constantsA.put(key, value);
		}
	}

	public static void addToVariablesA(String key, Object value) 
	{
		System.out.println("Trying to add to variables A: key " + key + " value " + value);

		if (variablesA.containsKey(key)) 
		{
			variablesA.replace(key, value);
		} 
		else 
		{
			variablesA.put(key, value);
		}
	}

	public static void addToConstantsB(String key, Object value) 
	{
		System.out.println("Trying to add to constants B: key " + key + " value " + value);

		if (constantsB.containsKey(key)) 
		{
			constantsB.replace(key, value);
		} 
		else 
		{
			constantsB.put(key, value);
		}
	}

	public static void addToVariablesB(String key, Object value) 
	{
		System.out.println("Trying to add to variables B: key " + key + " value " + value);

		if (variablesB.containsKey(key)) 
		{
			variablesB.replace(key, value);
		} 
		else 
		{
			variablesB.put(key, value);
		}
	}

	public static void addToConstants(String key, Object value) 
	{
		addToConstantsA(key, value);
		addToConstantsB(key, value);
	}

	public static void addToVariables(String key, Object value) 
	{
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
				
				addToConstants("CHASSIS_LEFT_ENCODER_REVERSE", true);
				addToConstants("CHASSIS_RIGHT_ENCODER_REVERSE", false);
				
				addToConstants("CHASSIS_ENCODERS_DISTANCE_PER_PULSE", 0.00124224); // in meters
				
				addToVariables("chassis_Joystick_Right_Axis", 5);
				addToVariables("chassis_Joystick_Left_Axis", 1);
				
				/*
				 * Intake
				 */
				addToConstantsA("INTAKE_MOTOR_REVERSE", false);
			}
		}

		/*
		 * Chassis
		 */
		{
			/*
			 * Constants
			 */
			{}

			/*
			 * Variables
			 */
			{
				addToVariables("chassis_TankDrive_DeadBand", 0.05);
				
				addToVariables("chassis_SpeedFactor_Medium", -0.8);
				addToVariables("chassis_SpeedFactor_Higher", -1.0);
				addToVariables("chassis_SpeedFactor_Lower", -0.5);

				addToVariables("chassis_TankDrive_InvertX", false);
				addToVariables("chassis_TankDrive_InvertY", true);
			}

			/*
			 * Drive Distance
			 */
			{
				// PID
					// BY CAMERA
					addToVariables("chassis_DriveByCamera_PID_KP", 0.0);
					addToVariables("chassis_DriveByCamera_PID_KI", 0.0);
					addToVariables("chassis_DriveByCamera_PID_KD", 0.0);
					
					// BY ENCODERS
					addToVariables("chassis_DriveDistance_PID_Tolerance", 0.01);
					addToVariables("chassis_DriveDistance_PID_Setpoint", 0.0);
					
					addToVariables("chassis_DriveDistance_PID_KP", 0.0);
					addToVariables("chassis_DriveDistance_PID_KI", 0.0);
					addToVariables("chassis_DriveDistance_PID_KD", 0.0);
			}
			
			/*
			 * Autonomous
			 */
			{
				
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
			{}

			/*
			 * Variables
			 */
			{
				addToVariables("intake_MoveIntake_V", -0.75);
			}
		}
	}
}
