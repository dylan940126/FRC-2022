package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    public static final TalonFX left_shooter = new TalonFX(Constants.left_shooter_ID),
            right_shooter = new TalonFX(Constants.right_shooter_ID);
    private static double I = 0, last_err = 0;

    public Shooter() {
        left_shooter.setInverted(true);
        right_shooter.follow(left_shooter);
    }

    public static boolean fire(double velocity) {
        // double currentVelocity = left_shooter.getSelectedSensorVelocity();
        double err = velocity - left_shooter.getSelectedSensorVelocity();
        SmartDashboard.putNumber("velocity", left_shooter.getSelectedSensorVelocity());
        I += err;
        double kp = SmartDashboard.getNumber("kp", Constants.shooter_kp);
        double ki = SmartDashboard.getNumber("ki", Constants.shooter_ki);
        double kd = SmartDashboard.getNumber("kd", Constants.shooter_kd);
        double power = err * kp + I * ki + (err - last_err) * kd;
        left_shooter.set(ControlMode.PercentOutput, power / 19500);
        last_err = err;
        return Math.abs(err) < 500;
    }

    public static boolean fire() {
        return fire(Constants.velocity);
    }

    public static void stop() {
        left_shooter.set(ControlMode.PercentOutput, 0);
        I = 0;
    }

    public static TalonFX getShooter() {
        return left_shooter;
    }
}
