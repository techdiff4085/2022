// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  private WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.LeftFrontWheel);
  private WPI_TalonFX backLeft = new WPI_TalonFX(Constants.LeftBackWheel);
  private WPI_TalonFX frontRight = new WPI_TalonFX(Constants.RightFrontWheel);
  private WPI_TalonFX backRight = new WPI_TalonFX(Constants.RightBackWheel);
  private MecanumDrive mecanumDrive;
    /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {
    mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
    frontRight.setInverted(true);
    backRight.setInverted(true);

  }

  public void drive(double y, double x, double z){
    mecanumDrive.driveCartesian(y, x, z);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
