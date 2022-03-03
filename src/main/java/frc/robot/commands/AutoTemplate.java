package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public abstract class AutoTemplate extends CommandBase {
    int state;
    double startTime;

    public AutoTemplate() {
        state = 0;
        startTime = RobotController.getFPGATime();
    }

    @Override
    public abstract void end(boolean interrupted);

    @Override
    public abstract void execute();

    @Override
    public abstract void initialize();

    public void toNextState(int state) {
        this.state = state;
        resetTimer();
    }

    public void NextState() {
        ++state;
        resetTimer();
    }

    public double getTimeMs() {
        return (RobotController.getFPGATime() - startTime) / 1000;
    }

    public void resetTimer() {
        startTime = RobotController.getFPGATime();
    }
}
