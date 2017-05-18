package org.usfirst.frc.team3316.robot.vision;

public class AlignShooter {

    public static double getTowerAngle() {
	double towerAngle = VisionServer.Data.get("AA"); // AA = Azimuthal Angle
	return (towerAngle);
    }

    /**
     * This returns the DFC
     */

    public static double getDistanceFromTower() {
	double distance = VisionServer.Data.get("DFC"); // DFC = Distance From
	// Camera
	return distance;
    }

    public static boolean isObjectDetected() {
	try {
	    return VisionServer.Data.get("IOD") == 1.0;
	} catch (Exception e) {
	    // logger.severe(e);
	    return false;
	}
    }
}
