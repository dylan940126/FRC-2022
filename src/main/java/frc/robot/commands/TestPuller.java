package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class TestPuller extends CommandBase {

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        Robot.m_gamepad1.update();
        SmartDashboard.putBoolean("y", Robot.m_gamepad1.y);
        if (Robot.m_gamepad1.y)
            Robot.m_puller.setPower(0.5);
        else if (Robot.m_gamepad1.a)
            Robot.m_puller.setPower(-0.5);
        else {
            Robot.m_puller.setLeft(Robot.m_gamepad1.left_stick_y * -0.5);
            Robot.m_puller.setRight(Robot.m_gamepad1.right_stick_y * -0.5);
        }
    }

    @Override
    public void end(boolean interrupted) {
    }
}
