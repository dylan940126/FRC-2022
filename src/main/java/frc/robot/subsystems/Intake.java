package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    public static final VictorSPX intake = new VictorSPX(Constants.intake_ID);

    public static void charge() {
        intake.set(ControlMode.PercentOutput, 1);
    }

    public static void discharge() {
        intake.set(ControlMode.PercentOutput, -1);
    }

    public static void stop() {
        intake.set(ControlMode.PercentOutput, 0);
    }

}
