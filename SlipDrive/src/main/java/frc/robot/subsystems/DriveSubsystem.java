// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  private WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.Motors.LeftFrontWheel);
  private WPI_TalonFX backLeft = new WPI_TalonFX(Constants.Motors.LeftBackWheel);
  private WPI_TalonFX frontRight = new WPI_TalonFX(Constants.Motors.RightFrontWheel);
  private WPI_TalonFX backRight = new WPI_TalonFX(Constants.Motors.RightBackWheel);

  private MecanumDrive mecanumDrive;
  private boolean isFastMode = true;
    /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {
    mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
    frontLeft.setInverted(true);
    backLeft.setInverted(true);

  }

  public void drive(double y, double x, double z){
    mecanumDrive.driveCartesian(y, x, z);
  }
  
  public void toggleFastMode(){
    isFastMode = !isFastMode;
    SmartDashboard.putBoolean("Fastmode on", isFastMode);
  }

  public boolean getisFastMode(){
    return isFastMode;
  }

  public void turnToAngle(double turnToAngle, AHRS navX) {
    mecanumDrive.driveCartesian(0, 0, turnToAngle, navX.getYaw());
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
