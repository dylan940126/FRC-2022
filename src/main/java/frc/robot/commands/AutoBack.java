package frc.robot.commands;

import java.sql.Driver;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Conveyer;
import frc.robot.subsystems.DriverBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Turntable;

public class AutoBack extends AutoTemplate {
    @Override
    public void initialize() {
        DriverBase.setHeading(-22.5);
    }

    @Override
    public void execute() {
        switch (state) {
            case 0:
                Turntable.turnTo(0);
                DriverBase.drive(0.5, 0);
                Intake.discharge();
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
                    NextState();
                }
                break;
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.stopAll();
    }

}
