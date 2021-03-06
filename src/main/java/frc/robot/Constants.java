// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public class Constants {
    public static final int conveyer_ID = 21;
    public static final int left_shooter_ID = 12;
    public static final int left_talon_ID = 2;
    public static final int left_pull_ID = 3;
    public static final int left_victor_ID = 5;
    public static final int turntable_ID = 13;
    public static final int right_shooter_ID = 11;
    public static final int right_talon_ID = 1;
    public static final int right_pull_ID = 4;
    public static final int right_victor_ID = 3;
    public static final int intake_ID = 20;
    public static final double hub_height_cm = 264;// 264
    public static final double camera_height_cm = 58;
    public static final double camera_elevation_degree = 28.248;
    public static final double encoder_per_degree = 5.66;
    public static final double shooter_kp = 1.1;
    public static final double shooter_ki = 0.03;
    public static final double shooter_kd = 2;
    public static final double perfect_shoot_distance = 450;
}