package org.usfirst.frc.team3316.robot.sequences;

import org.usfirst.frc.team3316.robot.commands.DBugCommandGroup;
import org.usfirst.frc.team3316.robot.commands.intake.IntakeRollIn;
import org.usfirst.frc.team3316.robot.commands.intake.StopIntake;
import org.usfirst.frc.team3316.robot.commands.intake.WaitForGear;

public class CollectGear extends DBugCommandGroup {
	
	public CollectGear() {
		addParallel(new IntakeRollIn());
		addSequential(new WaitForGear());
		addParallel(new StopIntake());
	}
	
}
