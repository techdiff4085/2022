// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  private WPI_VictorSPX rakeLiftLower = new WPI_VictorSPX(Constants.Motors.RakeLiftLower);
  private WPI_VictorSPX rakeIntake = new WPI_VictorSPX(Constants.Motors.RakeIntake);
  private WPI_VictorSPX horizontalRight = new WPI_VictorSPX(Constants.Motors.HorizontalRight);
  private WPI_VictorSPX horizontalLeft = new WPI_VictorSPX(Constants.Motors.HorizontalLeft);
  /*
  private DigitalInput upperLimitSwitch = new DigitalInput(Constants.LimitSwitches.RAKEUP);
  private DigitalInput lowerLimitSwitch = new DigitalInput(Constants.LimitSwitches.RAKEDOWN);
  private DigitalInput shooterLimitSwitch = new DigitalInput(Constants.LimitSwitches.SHOOT);
  */

  public IntakeSubsystem() {}

  public void dropRake() {
   //This lowers rakeLifeLower until it hits the lower limit switch.
    rakeLiftLower.set(-0.5);
  }

  public void liftRake() {
    rakeLiftLower.set(0.5);
  }
/*
  public boolean isUpperLimitSwitchHit(){
    if (upperLimitSwitch.get()){
      rakeLiftLower.set(0);
      return true;
    }
    return false;
  }

  public boolean isLowerLimitSwitchHit(){
    if (lowerLimitSwitch.get()){
      rakeLiftLower.set(0);
      return true;
    }
    return false;
  }

  public boolean isShooterLimitSwitchHit(){
    if (shooterLimitSwitch.get()){
      horizontalLeft.set(0);
      horizontalRight.set(0);
      return true;
    } else {
      horizontalLeft.set(0.5);
      horizontalRight.set(0.5);
      return false;
    }
  }
*/
  public void startIntake(){
    //This starts the motor on the rake.
    rakeIntake.set(1);
  } 

  public void stopIntake(){
    //This stops the motor on the rake.
    rakeIntake.set(0);
  } 

  public void startHorizontalMotors(){
    //This starts the motor on the rake.
    horizontalLeft.set(0.5);
    horizontalRight.set(0.5);
  } 

  public void stopHorizontalMotors(){
    //This starts the motor on the rake.
    horizontalLeft.set(0);
    horizontalRight.set(0);
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
