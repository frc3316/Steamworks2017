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

public class AutonPosition3 extends DBugCommandGroup {
    public AutonPosition3() {
	addSequential(new ResetGyro());
	addSequential(new DriveDistanceLongRange(1.92, 1.92));
	addSequential(new TurnByGyro(53.0));
	addParallel(new DriveDistanceLongRange(1.9, 1.9));
	addSequential(new WaitForPeg());
	addSequential(new DriveDistanceShortRange(-0.4, -0.4));
    }
}
