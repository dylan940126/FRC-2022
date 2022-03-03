package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Conveyer;
import frc.robot.subsystems.DriverBase;
import frc.robot.subsystems.GoalDetecter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class TeleF1 extends TeleTemplate {
    ButtonSwitch intakeSwitch = new ButtonSwitch();

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        DriverBase.drive(Robot.m_gamepad1.left_stick_y * 0.6,
                -Robot.m_gamepad1.left_stick_x * 0.45);
        if (Robot.m_gamepad1.a) {
            Conveyer.feed();
        } else if (Robot.m_gamepad1.y) {
            Conveyer.retain();
        } else {
            Conveyer.stop();
        }
        if (Robot.m_gamepad1.x) {
            Intake.discharge();
        } else if (intakeSwitch.update(Robot.m_gamepad1.b)) {
            Intake.charge();
        } else {
            Intake.stop();
        }
        GoalDetecter.update();
        if (Robot.m_gamepad1.right_bumper) {
            // Robot.m_turntable.setPower(GoalDetecter.getX() / 15);
            Shooter.fire(5000);
            // DriverBase.follow((Constants.perfect_shoot_distance -
            // GoalDetecter.getDistance()) / 200,
            // Robot.m_turntable.getDirection() / 15);
        } else
            Shooter.stop();
        if (Robot.m_gamepad1.left_bumper)
            Conveyer.feed();
    }

    @Override
    public void end(boolean interrupted) {
    }
}
