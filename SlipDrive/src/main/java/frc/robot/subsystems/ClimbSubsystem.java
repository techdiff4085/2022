// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
  
 private WPI_VictorSPX climb = new WPI_VictorSPX(Constants.Motors.Climb);

  public ClimbSubsystem(){}

  public void raiseClimb(){
    climb.set(-1);
  }

  public void lowerClimb(){
    climb.set(1);
  }

  public void stopClimb(){
    climb.set(0);
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
