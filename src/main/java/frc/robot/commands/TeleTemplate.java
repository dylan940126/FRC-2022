package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public abstract class TeleTemplate extends CommandBase {

    @Override
    public abstract void initialize();

    @Override
    public abstract void execute();

    @Override
    public abstract void end(boolean interrupted);

    public class ButtonSwitch {
        private boolean state = false, lastPressed = false;

        public boolean update(boolean pressed) {
            if (!lastPressed && pressed)
                state ^= pressed;
            lastPressed = pressed;
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public boolean getState() {
            return state;
        }
    }
}
