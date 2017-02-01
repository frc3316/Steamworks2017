package org.usfirst.frc.team3316.robot.humanIO;

import org.usfirst.frc.team3316.robot.commands.DBugCommand;

import edu.wpi.first.wpilibj.command.Command;

public class DBugToggleCommand extends DBugCommand {
	/**
	 * First pressing activates cmd 1 and the second activates cmd 2 Note: This
	 * does not work on whileHeld
	 */

	private Command cmd1, cmd2;
	private static boolean toggle = false; // What command will run next

	public DBugToggleCommand(Command cmd1, Command cmd2) {
		this.cmd1 = cmd1;
		this.cmd2 = cmd2;
	}

	protected void init() {
		if (toggle) {
			toggle = false;
			cmd1.cancel();
			cmd2.start();
		} else {
			toggle = true;
			cmd2.cancel();
			cmd1.start();
		}
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return (!cmd1.isRunning() && !cmd2.isRunning());
	}

	protected void fin() {
		cmd1.cancel();
		cmd2.cancel();
	}

	protected void interr() {
		fin();
	}
}
