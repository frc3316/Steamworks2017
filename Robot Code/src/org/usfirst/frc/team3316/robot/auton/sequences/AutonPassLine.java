package org.usfirst.frc.team3316.robot.auton.sequences;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistance;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistanceLongRange;
import org.usfirst.frc.team3316.robot.auton.commands.DriveDistanceShortRange;
import org.usfirst.frc.team3316.robot.commands.DBugCommandGroup;
import org.usfirst.frc.team3316.robot.commands.chassis.MoveChassis;
import org.usfirst.frc.team3316.robot.commands.chassis.StopChassis;
import org.usfirst.frc.team3316.robot.commands.installer.WaitForPeg;

public class AutonPassLine extends DBugCommandGroup {
    public AutonPassLine() {
    	addParallel(new DriveDistanceLongRange(2.15, 2.15));
    }
}
