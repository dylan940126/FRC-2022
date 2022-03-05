// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutoThreeBalls;
import frc.robot.commands.TeleF2;
import frc.robot.subsystems.Conveyer;
import frc.robot.subsystems.DriverBase;
import frc.robot.subsystems.Gamepad;
import frc.robot.subsystems.GoalDetecter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Puller;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turntable;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static final Gamepad m_gamepad1 = new Gamepad(0);
    public static final Gamepad m_gamepad2 = new Gamepad(1);

    /**
     * This function is run when the robot is first started up and should be used
     * for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer. This will perform all our button bindings,
        // and put our
        // autonomous chooser on the dashboard.
        CommandScheduler.getInstance().cancelAll();
        RobotContainer.m_autoChooser.setDefaultOption("3balls", "3balls");
        RobotContainer.m_autoChooser.addOption("back", "back");
        RobotContainer.m_autoChooser.addOption("back&take", "back&take");
        SmartDashboard.putData(RobotContainer.m_autoChooser);
        initAll();
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and
     * test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled
        // commands, running already-scheduled commands, removing finished or
        // interrupted commands,
        // and running subsystem periodic() methods. This must be called from the
        // robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
        SmartDashboard.putNumber("shoot velocity", Constants.velocity);
    }

    /** This function is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {
        stopAll();
    }

    @Override
    public void disabledPeriodic() {
    }

    /**
     * This autonomous runs the autonomous command selected by your
     * {@link class.
     */

    @Override
    public void autonomousInit() {
        RobotContainer.startCommand(new AutoThreeBalls());

        // schedule the autonomous command (example)
        if (RobotContainer.m_command != null) {
            RobotContainer.m_command.schedule();
        }
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        RobotContainer.startCommand(new TeleF2());
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        // RobotContainer.setCommand(new TestPuller());
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
    }

    public static void stopAll() {
        Conveyer.stop();
        DriverBase.stop();
        Intake.stop();
        Puller.stop();
        Shooter.stop();
        Turntable.stop();
    }

    public static void initAll() {
        new Conveyer();
        new DriverBase();
        new GoalDetecter();
        new Intake();
        new Puller();
        new Shooter();
        new Turntable();
    }
}
