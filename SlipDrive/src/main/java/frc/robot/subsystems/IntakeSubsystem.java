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
  
  private DigitalInput upperLimitSwitch = new DigitalInput(Constants.LimitSwitches.RAKEUP);
  private DigitalInput lowerLimitSwitch = new DigitalInput(Constants.LimitSwitches.RAKEDOWN);
  private DigitalInput shooterLimitSwitch = new DigitalInput(Constants.LimitSwitches.SHOOT);

  private boolean isReversed = false;
  private boolean isStopped = false;

  public IntakeSubsystem() {}

  public void dropRake() {
   //This lowers rakeLifeLower until it hits the lower limit switch.
    rakeLiftLower.set(-0.3);
  }

  public void liftRake() {
    rakeLiftLower.set(0.9);
  }

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

  public void runIfLimitSwitchNotHit(){
    if (isReversed){
      horizontalLeft.set(1);
      horizontalRight.set(-1);
    } else if (shooterLimitSwitch.get() || this.isStopped){
      horizontalLeft.set(0);
      horizontalRight.set(0);
    } else {
      horizontalLeft.set(-1);
      horizontalRight.set(1);
    }
  }

  public void startIntake(){
    //This starts the motor on the rake.
    rakeIntake.set(1);
  } 

  public void stopIntake(){
    //This stops the motor on the rake.
    rakeIntake.set(0);
  } 


  public void setHorizontalMotorsForward(){
    //This starts the motor on the rake.
    this.isStopped = false;
    this.isReversed = false;
  } 
  public void setHorizontalMotorsReverse(){
    this.isStopped = true;
  }

  public boolean isReversed(){
    return this.isReversed;
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
