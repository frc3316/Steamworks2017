package org.usfirst.frc.team3316.robot.commands.installation;

import org.usfirst.frc.team3316.robot.Robot;
import org.usfirst.frc.team3316.robot.commands.DBugCommand;

public class ResetMechanism extends DBugCommand {

    @Override
    protected void init() {
	// TODO Auto-generated method stub

    }

    @Override
    protected void execute() {
	// TODO Auto-generated method stub

    }

    @Override
    protected boolean isFinished() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    protected void fin() {
	Robot.installer.setMechanism(true);
    }

    @Override
    protected void interr() {
	// TODO Auto-generated method stub
    }

}
