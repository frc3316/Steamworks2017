package org.usfirst.frc.team3316.robot.auton.sequences;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistance;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistanceLongRange;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistanceShortRange;
import org.usfirst.frc.team3316.robot.commands.DBugCommandGroup;
import org.usfirst.frc.team3316.robot.commands.chassis.MoveChassis;
import org.usfirst.frc.team3316.robot.commands.chassis.StopChassis;
import org.usfirst.frc.team3316.robot.commands.installer.WaitForPeg;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonPosition2 extends DBugCommandGroup {
    public AutonPosition2() {
	addParallel(new DriveDistanceLongRange(2.3, 2.3));
	addSequential(new WaitForPeg());
	addParallel(new StopChassis());
	addParallel(new DriveDistanceShortRange(-0.65, -0.65));
    }
}
