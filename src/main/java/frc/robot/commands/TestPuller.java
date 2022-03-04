package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Puller;

public class TestPuller extends CommandBase {

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        SmartDashboard.putNumber("time", RobotController.getFPGATime());
        SmartDashboard.putBoolean("y", Robot.m_gamepad1.y);
        if (Robot.m_gamepad1.y)
            Puller.setPower(0.5);
        else if (Robot.m_gamepad1.a)
            Puller.setPower(-0.5);
        else {
            Puller.setLeft(Robot.m_gamepad1.left_stick_y * -0.5);
            Puller.setRight(Robot.m_gamepad1.right_stick_y * -0.5);
        }
    }

    @Override
    public void end(boolean interrupted) {
    }
}
