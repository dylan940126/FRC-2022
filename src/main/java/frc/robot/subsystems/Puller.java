package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Puller extends SubsystemBase {
    public static final TalonFX left_pull = new TalonFX(Constants.left_pull_ID),
            right_pull = new TalonFX(Constants.right_pull_ID);
    private static double error;

    public Puller() {
        error = getError();
        right_pull.setInverted(true);
        right_pull.setInverted(true);
    }

    private static double getError() {
        return left_pull.getSelectedSensorPosition() - right_pull.getSelectedSensorPosition();
    }

    public static void setPower(double power) {
        setLeft(power);
        setRight(power);
    }

    public static void setPowerSync(double power) {
        double err = error - getError();
        err /= 2000;
        setLeft(power + err);
        setRight(power - err);
    }

    public static void stop() {
        setPower(0);
    }

    public static void setLeft(double power) {
        // if (left_pull.getSelectedSensorPosition() > 326840)
        left_pull.set(ControlMode.PercentOutput, power);
        SmartDashboard.putNumber("leftPullerEncoder", left_pull.getSelectedSensorPosition());
        error = getError();
    }

    public static void setRight(double power) {
        right_pull.set(ControlMode.PercentOutput, power);
        SmartDashboard.putNumber("rightPullerEncoder", right_pull.getSelectedSensorPosition());
        error = getError();
    }
}
