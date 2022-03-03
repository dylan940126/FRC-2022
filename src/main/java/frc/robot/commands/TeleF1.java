package frc.robot.commands;

import frc.robot.Robot;

public class TeleF1 extends TeleTemplate {
    ButtonSwitch intakeSwitch = new ButtonSwitch();

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        Robot.m_driverBase.drive(Robot.m_gamepad1.left_stick_y * 0.6,
                -Robot.m_gamepad1.left_stick_x * 0.45);
        if (Robot.m_gamepad1.a) {
            Robot.m_conveyer.feed();
        } else if (Robot.m_gamepad1.y) {
            Robot.m_conveyer.retain();
        } else {
            Robot.m_conveyer.stop();
        }
        if (Robot.m_gamepad1.x) {
            Robot.m_intake.discharge();
        } else if (intakeSwitch.update(Robot.m_gamepad1.b)) {
            Robot.m_intake.charge();
        } else {
            Robot.m_intake.stop();
        }
        Robot.m_goalDetecter.update();
        if (Robot.m_gamepad1.right_bumper) {
            // Robot.m_turntable.setPower(Robot.m_goalDetecter.getX() / 15);
            Robot.m_shooter.fire(5000);
            // Robot.m_driverBase.follow((Constants.perfect_shoot_distance -
            // Robot.m_goalDetecter.getDistance()) / 200,
            // Robot.m_turntable.getDirection() / 15);
        } else
            Robot.m_shooter.stop();
        if (Robot.m_gamepad1.left_bumper)
            Robot.m_conveyer.feed();
    }

    @Override
    public void end(boolean interrupted) {
    }
}
