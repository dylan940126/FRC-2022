package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriverBase extends SubsystemBase {
    public static final TalonSRX left_talon = new TalonSRX(Constants.left_talon_ID);
    public static final VictorSPX left_victor = new VictorSPX(Constants.left_victor_ID);
    public static final TalonSRX right_talon = new TalonSRX(Constants.right_talon_ID);
    public static final VictorSPX right_victor = new VictorSPX(Constants.right_victor_ID);
    public static final AHRS imu = new AHRS(SPI.Port.kMXP);
    private static double startDirection;

    public DriverBase() {
        left_victor.follow(left_talon);
        left_talon.setInverted(false);
        left_victor.setInverted(false);
        right_victor.follow(right_talon);
        right_victor.setInverted(true);
        right_talon.setInverted(true);
        startDirection = imu.getAngle();
    }

    public static void tankDrive(double left_power, double right_power) {
        double k = Math.max(Math.abs(left_power), Math.abs(right_power));
        if (k > 1) {
            left_power /= k;
            right_power /= k;
        }
        left_talon.set(ControlMode.PercentOutput, left_power);
        right_talon.set(ControlMode.PercentOutput, right_power);
    }

    public static void drive(double forward_power, double turn_power) {
        tankDrive(forward_power + turn_power, forward_power - turn_power);
    }

    public static void follow(double power, double direction) {
        drive(power, MyMath.distanceToPower(direction - getHeading()) / 19);
    }

    public static boolean turnTo(double direction) {
        follow(0, direction);
        return Math.abs(direction - getHeading()) < 2;
    }

    public static void stop() {
        tankDrive(0, 0);
    }

    public static double getHeading() {
        return imu.getAngle() - startDirection;
    }

    public static void setHeading(double direction) {
        startDirection = imu.getAngle() + direction;
    }

    public static void restartIMU() {
        imu.calibrate();
    }
}