package org.usfirst.frc.team3316.robot.commands.chassis;

import org.usfirst.frc.team3316.robot.commands.DBugCommand;

public class ChassisHighSpeed extends DBugCommand {
    public ChassisHighSpeed() {
    }

    @Override
    protected void init() {
	config.add("chassis_SpeedFactor_Current", (double) config.get("chassis_SpeedFactor_Higher"));
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
