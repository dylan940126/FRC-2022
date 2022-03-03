// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
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
    public static final Conveyer m_conveyer = new Conveyer();
    public static final DriverBase m_driverBase = new DriverBase();
    public static final GoalDetecter m_goalDetecter = new GoalDetecter();
    public static final Intake m_intake = new Intake();
    public static final Puller m_puller = new Puller();
    public static final Shooter m_shooter = new Shooter();
    public static final Turntable m_turntable = new Turntable();
    public static final Gamepad m_gamepad1 = new Gamepad(0);

    private final SendableChooser<String> m_autoChooser = new SendableChooser<>();
    private Command m_autonomousCommand;
    private Command m_teleopCommand;
    private Command m_testCommand;

    private RobotContainer m_robotContainer;

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
        // RobotContainer.m_DriverBase.restartIMU();
        m_robotContainer = new RobotContainer();
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
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
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
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
        m_teleopCommand = m_robotContainer.getTeleopCommand();
        if (m_teleopCommand != null) {
            m_teleopCommand.schedule();
        }
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        Robot.m_turntable.resetDirection();
        SmartDashboard.putNumber("dir", 0);
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
        Robot.m_turntable.turnTo(SmartDashboard.getNumber("dir", 0));
    }

    public static void stopAll() {
        m_conveyer.stop();
        m_driverBase.stop();
        m_intake.stop();
        m_puller.stop();
        m_shooter.stop();
        m_turntable.stop();
    }
}
