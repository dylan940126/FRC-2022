package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Conveyer;
import frc.robot.subsystems.DriverBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Puller;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turntable;

public class TeleF2 extends TeleTemplate {
    ButtonSwitch intakeSwitch = new ButtonSwitch();
    ButtonSwitch aimSwitch = new ButtonSwitch();

    @Override
    public void initialize() {
        aimSwitch.setState(true);
    }

    @Override
    public void execute() {
        SmartDashboard.putBoolean("auto", aimSwitch.getState());
        DriverBase.drive(Robot.m_gamepad1.left_stick_y * 0.8,
                Robot.m_gamepad1.right_stick_x * 0.6);
        if (Robot.m_gamepad1.y || Robot.m_gamepad2.left_bumper) {
            Intake.discharge();
            Conveyer.retain();
        } else if (intakeSwitch.update(Robot.m_gamepad1.a || Robot.m_gamepad2.right_bumper)) {
            Intake.charge();
        } else {
            Intake.stop();
        }
        if (Robot.m_gamepad1.right_bumper) {
            boolean ready = false;
            if (aimSwitch.update(Robot.m_gamepad1.right_trigger > 0.5))
                ready = DriverBase.autoAim(false);
            else
                Shooter.fire();
            if (ready || Robot.m_gamepad1.left_bumper) {
                Intake.charge();
                Conveyer.feed();
            }
        } else {
            Turntable.turnTo(-180);
            Conveyer.stop();
            Shooter.stop();
        }
        if (Robot.m_gamepad2.y)
            Puller.setPower(0.7);
        else if (Robot.m_gamepad2.a)
            Puller.setPower(-0.7);
        else {
            Puller.setLeft(Robot.m_gamepad2.left_stick_y * 0.3);
            Puller.setRight(Robot.m_gamepad2.right_stick_y * 0.7);
        }
        if (Robot.m_gamepad2.pov == 270)
            Turntable.addDirection(-10);
        else if (Robot.m_gamepad2.pov == 90)
            Turntable.addDirection(10);
        else if (Robot.m_gamepad2.pov == 0)
            Constants.velocity += 100;
        else if (Robot.m_gamepad2.pov == 180)
            Constants.velocity -= 100;
    }

    @Override
    public void end(boolean interrupted) {
    }
}
