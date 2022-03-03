package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Puller extends SubsystemBase {
    public static final TalonFX left_pull = new TalonFX(Constants.left_pull_ID),
            right_pull = new TalonFX(Constants.right_pull_ID);

    public Puller() {
        left_pull.setInverted(true);
    }

    public void setPower(double power) {
        setLeft(power);
        setRight(power);
    }

    public void resetEncoder() {
        left_pull.setSelectedSensorPosition(0);
        right_pull.setSelectedSensorPosition(0);
    }

    public void stop() {
        setPower(0);
    }

    public void setLeft(double power) {
        // if (left_pull.getSelectedSensorPosition() > 326840)
        left_pull.set(ControlMode.PercentOutput, power);
        SmartDashboard.putNumber("leftPullerEncoder", left_pull.getSelectedSensorPosition());
    }

    public void setRight(double power) {
        right_pull.set(ControlMode.PercentOutput, power);
        SmartDashboard.putNumber("rightPullerEncoder", right_pull.getSelectedSensorPosition());
    }
}
