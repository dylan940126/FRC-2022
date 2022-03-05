package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GoalDetecter extends SubsystemBase {
    private static NetworkTable table;
    private static double tv;
    private static double tx;
    private static double ty;
    private static double distance;

    public GoalDetecter() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
        update();
    }

    public static void update() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tv = table.getEntry("tv").getDouble(0);
        tx = table.getEntry("tx").getDouble(0);
        ty = table.getEntry("ty").getDouble(0);
        if (tv == 0)
            return;
        distance = (Constants.hub_height_cm - Constants.camera_height_cm)
                / Math.tan(Math.toRadians(Constants.camera_elevation_degree + ty));
    }

    public static boolean isDetected() {
        return tv != 0;
    }

    public static double getDistance() {
        return distance;
    }

    public static double getX() {
        return tx;
    }

    public static NetworkTable getTable() {
        return table;
    }

    @Override
    public void periodic() {
        update();
    }
}
