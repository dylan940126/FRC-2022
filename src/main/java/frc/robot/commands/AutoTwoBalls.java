package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Conveyer;
import frc.robot.subsystems.DriverBase;
import frc.robot.subsystems.GoalDetecter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turntable;

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
                DriverBase.drive(-0.5, 0);
                Intake.charge();
                Shooter.fire(0.85);
                if (getTimeMs() > 1670)
                    NextState();
                break;
            case 1:
                Intake.stop();
                DriverBase.drive(0, 0);
                Conveyer.feed();
                if (getTimeMs() > 1000)
                    NextState();
                break;
            case 2:
                Shooter.stop();
                Turntable.setPower(0);
                Conveyer.stop();
                DriverBase.drive(0, -0.3);
                if (getTimeMs() > 555)
                    NextState();
                break;
            case 3:
                Intake.charge();
                DriverBase.drive(0.3, 0);
                Turntable.resetDirection();
                if (getTimeMs() > 6000)
                    NextState();
                break;
            case 4:
                Shooter.fire(0.82);
                DriverBase.drive(0, 0);
                Turntable.setPower(1);
                if (getTimeMs() > 800)
                    NextState();
                break;
            case 5:
                Turntable.setPower(0);
                Conveyer.feed();
                GoalDetecter.update();
                Turntable.setPower(GoalDetecter.getX() / 20);
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
