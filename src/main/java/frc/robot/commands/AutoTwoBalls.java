package frc.robot.commands;

import frc.robot.Robot;

public class AutoTwoBalls extends AutoTemplate {

    public AutoTwoBalls() {
        super();
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        switch (state) {
            case 0:
                Robot.m_driverBase.drive(-0.5, 0);
                Robot.m_intake.charge();
                Robot.m_shooter.fire(0.85);
                if (getTimeMs() > 1670)
                    NextState();
                break;
            case 1:
                Robot.m_intake.stop();
                Robot.m_driverBase.drive(0, 0);
                Robot.m_conveyer.feed();
                if (getTimeMs() > 1000)
                    NextState();
                break;
            case 2:
                Robot.m_shooter.stop();
                Robot.m_turntable.setPower(0);
                Robot.m_conveyer.stop();
                Robot.m_driverBase.drive(0, -0.3);
                if (getTimeMs() > 555)
                    NextState();
                break;
            case 3:
                Robot.m_intake.charge();
                Robot.m_driverBase.drive(0.3, 0);
                Robot.m_turntable.resetDirection();
                if (getTimeMs() > 6000)
                    NextState();
                break;
            case 4:
                Robot.m_shooter.fire(0.82);
                Robot.m_driverBase.drive(0, 0);
                Robot.m_turntable.setPower(1);
                if (getTimeMs() > 800)
                    NextState();
                break;
            case 5:
                Robot.m_turntable.setPower(0);
                Robot.m_conveyer.feed();
                Robot.m_goalDetecter.update();
                Robot.m_turntable.setPower(Robot.m_goalDetecter.getX() / 20);
                if (getTimeMs() > 5000)
                    NextState();
                break;
            default:
                Robot.stopAll();
                break;
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.stopAll();
    }

}
