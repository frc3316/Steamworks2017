package org.usfirst.frc.team3316.robot.auton.sequences;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistanceOvershoot;
import org.usfirst.frc.team3316.robot.commands.DBugCommandGroup;
import org.usfirst.frc.team3316.robot.commands.chassis.MoveChassis;
import org.usfirst.frc.team3316.robot.commands.chassis.StopChassis;
import org.usfirst.frc.team3316.robot.commands.installer.WaitForPeg;

public class AutonPosition2 extends DBugCommandGroup {
    public AutonPosition2() {
	addSequential(new DriveDistanceOvershoot(1.7));
	addParallel(new MoveChassis(0.35, 0.35));
	addSequential(new WaitForPeg());
	addParallel(new StopChassis());
	addSequential(new DriveDistanceOvershoot(-0.8));
    }
}
