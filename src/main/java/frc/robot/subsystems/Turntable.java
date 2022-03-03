package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turntable extends SubsystemBase {
    public static final Encoder encoder = new Encoder(0, 1);
    public static final VictorSPX motor = new VictorSPX(Constants.turntable_ID);
    private double zero_position;

    public Turntable() {
        resetDirection();
    }

    public void setPower(double power) {
        if ((getDirection() >= 90 && power > 0) || (getDirection() <= -180 && power < 0))
            stop();
        else
            motor.set(ControlMode.PercentOutput, power);
    }

    public boolean turnTo(double direction) {
        setPower((direction - getDirection()) / 20);
        return direction - getDirection() < 1;
    }

    public void stop() {
        setPower(0);
    }

    public double getDirection() {
        return (encoder.getDistance() - zero_position) / Constants.encoder_per_degree;
    }

    public void resetDirection() {
        zero_position = encoder.getDistance() + 180 * Constants.encoder_per_degree;
    }
}
