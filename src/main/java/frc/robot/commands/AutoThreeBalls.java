package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Conveyer;
import frc.robot.subsystems.DriverBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Turntable;

public class AutoThreeBalls extends AutoTemplate {

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        switch (state) {
            case 0:
                Turntable.turnTo(0);
                DriverBase.drive(0.5, 0);
                Intake.charge();
                if (getTimeMs() > 1800)
                    NextState();
                break;
            case 1:
                if (DriverBase.autoAim(true) || getTimeMs() > 5000) {
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
