package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj.ADIS16448_IMU.IMUAxis;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriverBase extends SubsystemBase {
    public static final TalonSRX left_talon = new TalonSRX(Constants.left_talon_ID);
    public static final VictorSPX left_victor = new VictorSPX(Constants.left_victor_ID);
    public static final TalonSRX right_talon = new TalonSRX(Constants.right_talon_ID);
    public static final VictorSPX right_victor = new VictorSPX(Constants.right_victor_ID);
    public static final ADIS16448_IMU imu = new ADIS16448_IMU();
    private double startDirection;

    public DriverBase() {
        left_victor.follow(left_talon);
        left_talon.setInverted(false);
        left_victor.setInverted(false);
        right_victor.follow(right_talon);
        right_victor.setInverted(true);
        right_talon.setInverted(true);
        imu.setYawAxis(IMUAxis.kZ);
        startDirection = imu.getAngle();
    }

    public void tankDrive(double left_power, double right_power) {
        double k = Math.max(Math.abs(left_power), Math.abs(right_power));
        if (k > 1) {
            left_power /= k;
            right_power /= k;
        }
        left_talon.set(ControlMode.PercentOutput, left_power);
        right_talon.set(ControlMode.PercentOutput, right_power);
    }

    public void drive(double forward_power, double turn_power) {
        tankDrive(forward_power - turn_power, forward_power + turn_power);
    }

    public void follow(double power, double turn_power) {
        if (turn_power > 0.4)
            power = Math.min(power, turn_power * 0.7);
        drive(power, turn_power);
    }

    public boolean turnTo(double direction) {
        drive(0, (getHeading() - direction) / 100);
        return Math.abs(direction - getHeading()) < 2;
    }

    public void stop() {
        tankDrive(0, 0);
    }

    public double getHeading() {
        return -(imu.getAngle() - startDirection);
    }

    public void resetHeading(double direction) {
        startDirection = imu.getAngle() + direction;
    }

    public void restartIMU() {
        imu.calibrate();
    }

    public TalonSRX getLeftTalon() {
        return left_talon;
    }

    public TalonSRX getRightTalon() {
        return right_talon;
    }
}