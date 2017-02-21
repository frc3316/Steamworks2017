package org.usfirst.frc.team3316.robot.auton.sequences;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistance;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistanceLongRange;
import org.usfirst.frc.team3316.robot.auton.commands.TurnByGyro;
import org.usfirst.frc.team3316.robot.commands.DBugCommandGroup;
import org.usfirst.frc.team3316.robot.commands.chassis.MoveChassis;
import org.usfirst.frc.team3316.robot.commands.chassis.ResetGyro;
import org.usfirst.frc.team3316.robot.commands.chassis.StopChassis;
import org.usfirst.frc.team3316.robot.commands.installer.WaitForPeg;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonPosition1 extends DBugCommandGroup {
    public AutonPosition1() {
	addSequential(new ResetGyro());
	addSequential(new DriveDistanceLongRange(2.0, 2.0));
	addSequential(new TurnByGyro(60.0));
//	addSequential(new WaitCommand(1.0));
//	addSequential(new WaitForPeg());
//	addParallel(new StopChassis());
//	addSequential(new DriveDistance(-0.3));
//	addSequential(new TurnByGyro(-60.0));
    }
}
