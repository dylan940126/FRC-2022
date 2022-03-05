package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Conveyer;
import frc.robot.subsystems.DriverBase;
import frc.robot.subsystems.GoalDetecter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Turntable;

public class AutoThreeBalls extends AutoTemplate {

    @Override
    public void initialize() {
        state = -3;
        DriverBase.setHeading(-22.5);
    }

    @Override
    public void execute() {
        switch (state) {
            case -3:
                Intake.charge();
                DriverBase.drive(0.7, 0);
                if (getTimeMs() > 300)
                    toNextState(-4);
                break;
            case -4:
                DriverBase.drive(-0.7, 0);
                if (getTimeMs() > 300)
                    toNextState(0);
                break;
            case 0:
                Turntable.turnTo(0);
                DriverBase.drive(0.5, 0);
                if (getTimeMs() > 1500) {
                    DriverBase.stop();
                    NextState();
                }
                break;
            case 1:
                DriverBase.autoAim(true);
                if (GoalDetecter.isDetected() && getTimeMs() > 4000) {
                    NextState();
                }
                break;
            case 2:
                DriverBase.autoAim(true);
                Conveyer.feed();
                if (getTimeMs() > 2000) {
                    toNextState(-1);
                }
                break;
            case -1:
                Turntable.stop();
                Conveyer.stop();
                if (DriverBase.turnTo(0))
                    toNextState(3);
                break;
            case 3:
                DriverBase.drive(0.5, 0);
                if (getTimeMs() > 1800) {
                    NextState();
                }
                break;
            case 4:
                if (DriverBase.autoAim(true) || getTimeMs() > 5000) {
                    NextState();
                }
                break;
            case 5:
                DriverBase.autoAim(true);
                Conveyer.feed();
                if (getTimeMs() > 5000) {
                    NextState();
                }
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
