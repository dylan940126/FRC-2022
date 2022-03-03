package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turntable extends SubsystemBase {
    public static final Encoder encoder = new Encoder(0, 1);
    public static final VictorSPX motor = new VictorSPX(Constants.turntable_ID);
    private static double zero_position;

    public Turntable() {
        resetDirection();
    }

    public static void setPower(double power) {
        if ((getDirection() >= 90 && power > 0) || (getDirection() <= -180 && power < 0))
            stop();
        else
            motor.set(ControlMode.PercentOutput, power);
    }

    public static boolean turnTo(double direction) {
        setPower(MyMath.distanceToPower(direction - getDirection()) / 5);
        return direction - getDirection() < 1;
    }

    public static void stop() {
        setPower(0);
    }

    public static double getDirection() {
        return (encoder.getDistance() - zero_position) / Constants.encoder_per_degree;
    }

    public static void resetDirection() {
        zero_position = encoder.getDistance() + 180 * Constants.encoder_per_degree;
    }
}
