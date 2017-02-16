package org.usfirst.frc.team3316.robot.commands.intake;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;
import org.usfirst.frc.team3316.robot.commands.DBugCommandGroup;

public class CollectGear extends DBugCommandGroup {
    public CollectGear() {
	addParallel(new IntakeRollIn());
	addSequential(new WaitForGear());
	addParallel(new StopIntake());
    }

    protected void fin() {
    }

    protected void interr() {
	(new StopIntake()).start();
    }
}
