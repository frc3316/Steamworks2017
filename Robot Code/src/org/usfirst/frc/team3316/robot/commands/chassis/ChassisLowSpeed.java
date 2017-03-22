package org.usfirst.frc.team3316.robot.commands.chassis;

import org.usfirst.frc.team3316.robot.commands.DBugCommand;

public class ChassisLowSpeed extends DBugCommand {
    public ChassisLowSpeed() {
    }

    @Override
    protected void init() {
	config.add("chassis_SpeedFactor_Current", (double) config.get("chassis_SpeedFactor_Lower"));
    }

    @Override
    protected void execute() { }

    @Override
    protected boolean isFinished() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    protected void fin() {}

    @Override
    protected void interr() {}
}
