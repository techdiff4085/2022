// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//Initial Shooter Subsytem
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private WPI_VictorSPX elevatorLeft = new WPI_VictorSPX(Constants.elevatorLeft);
  private WPI_VictorSPX elevatorRight = new WPI_VictorSPX(Constants.elevatorRight);
  private WPI_VictorSPX elevatorMiddle = new WPI_VictorSPX(Constants.elevatorMiddle);
  private WPI_VictorSPX shooter = new WPI_VictorSPX(Constants.shooter);

  public ShooterSubsystem() {
  //Start shooter motor
  }

  private void shoot(){
    //Start three elevator motors

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
