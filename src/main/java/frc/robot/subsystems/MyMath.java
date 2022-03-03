package frc.robot.subsystems;

public class MyMath {
    public static double distanceToPower(double distance) {
        return Math.copySign(Math.sqrt(Math.abs(distance)), distance);
    }
}
