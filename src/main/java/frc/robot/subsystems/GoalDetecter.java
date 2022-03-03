package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GoalDetecter extends SubsystemBase {
    private NetworkTable table;
    private double tv;
    private double tx;
    private double ty;
    private double distance;

    public GoalDetecter() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
        update();
    }

    public void update() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tv = table.getEntry("tv").getDouble(0);
        tx = table.getEntry("tx").getDouble(0);
        ty = table.getEntry("ty").getDouble(0);
        if (tv == 0)
            return;
        distance = (Constants.hub_height_cm - Constants.camera_height_cm)
                / Math.tan(Math.toRadians(Constants.camera_elevation_degree + ty));
    }

    public double getDistance() {
        return distance;
    }

    public double getX() {
        return tx;
    }

    public NetworkTable getTable() {
        return table;
    }

    @Override
    public void periodic() {
        update();
    }
}
