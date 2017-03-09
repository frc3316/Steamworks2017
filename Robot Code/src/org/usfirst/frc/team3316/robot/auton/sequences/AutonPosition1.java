package org.usfirst.frc.team3316.robot.auton.sequences;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistance;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistanceLongRange;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistanceShortRange;
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
	addSequential(new TurnByGyro(53.0));
	addParallel(new DriveDistanceShortRange(1.0, 1.0)); // TODO: Change values
	addSequential(new WaitForPeg());
	addSequential(new DriveDistanceShortRange(-0.4, -0.4));
    }
}
