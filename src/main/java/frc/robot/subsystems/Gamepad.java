package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gamepad extends SubsystemBase {
    public final Joystick gamepad;
    public double left_stick_x, left_stick_y,
            right_stick_x, right_stick_y,
            left_trigger, right_trigger;
    public boolean left_stick_button, right_stick_button,
            left_bumper, right_bumper,
            a, b, x, y,
            back, start;
    public int pov;

    public Gamepad(int port) {
        gamepad = new Joystick(port);
    }

    public void update() {
        left_stick_x = gamepad.getRawAxis(0);
        left_stick_y = -gamepad.getRawAxis(1);
        left_trigger = gamepad.getRawAxis(2);
        right_trigger = gamepad.getRawAxis(3);
        right_stick_x = gamepad.getRawAxis(4);
        right_stick_y = -gamepad.getRawAxis(5);
        a = gamepad.getRawButton(1);
        b = gamepad.getRawButton(2);
        x = gamepad.getRawButton(3);
        y = gamepad.getRawButton(4);
        left_bumper = gamepad.getRawButton(5);
        right_bumper = gamepad.getRawButton(6);
        back = gamepad.getRawButton(7);
        start = gamepad.getRawButton(8);
        left_stick_button = gamepad.getRawButton(9);
        right_stick_button = gamepad.getRawButton(10);
        pov = gamepad.getPOV();
    }

    @Override
    public void periodic() {
        update();
    }
}
