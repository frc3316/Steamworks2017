package org.usfirst.frc.team3316.robot.sequences;

import org.usfirst.frc.team3316.robot.commands.DBugCommandGroup;
import org.usfirst.frc.team3316.robot.commands.chassis.auton.DriveDistance;
import org.usfirst.frc.team3316.robot.commands.chassis.auton.DriveToLiftPID;
import org.usfirst.frc.team3316.robot.commands.chassis.auton.DrivetrainHeadingPIDCamera;
import org.usfirst.frc.team3316.robot.commands.chassis.auton.DrivetrainHeadingPIDGyro;

public class DriveToLiftSequence extends DBugCommandGroup {
	public DriveToLiftSequence() {
//		addSequential(new DriveDistance((double) config.get("chassis_DriveDistance_Distance")));
		addSequential(new DriveToLiftPID());
//		addSequential(new DrivetrainHeadingPIDGyro());
		addSequential(new DrivetrainHeadingPIDCamera());
	}
}
