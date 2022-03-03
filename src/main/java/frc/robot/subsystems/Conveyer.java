package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Conveyer extends SubsystemBase {
    public static final VictorSPX conveyer = new VictorSPX(Constants.conveyer_ID);

    public void feed() {
        conveyer.set(ControlMode.PercentOutput, 0.7);
    }

    public void retain() {
        conveyer.set(ControlMode.PercentOutput, -0.4);
    }

    public void stop() {
        conveyer.set(ControlMode.PercentOutput, 0);
    }

    public VictorSPX getConveyer() {
        return conveyer;
    }
}
