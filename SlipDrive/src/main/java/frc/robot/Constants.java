// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class Motors {
        public static final int LeftFrontWheel = 14;
        public static final int LeftBackWheel = 15;
        public static final int RightBackWheel = 1;
        public static final int RightFrontWheel = 0;
        public static final int elevatorLeft = 11;
        public static final int elevatorMiddle = 9;
        public static final int elevatorRight = 4;
        public static final int shooter = 12;
        public static final int RakeLiftLower = 2;
        public static final int RakeIntake = 10;
        public static final int HorizontalRight = 7;
        public static final int HorizontalLeft = 8;
        public static final int Climb = 3;
        public static final double KP = 0.05f;
        public static final double KI = 0;
        public static final double KD = 0.001;
    }   
    public static final class LimitSwitches{
        public static final int RAKEUP = 7;
        public static final int RAKEDOWN = 8;
        public static final int SHOOT = 9;
    }




}
