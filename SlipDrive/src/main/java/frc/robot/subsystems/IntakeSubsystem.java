// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  private WPI_TalonFX rakeIntake = new WPI_TalonFX(Constants.Motors.RakeIntake);
  private WPI_VictorSPX horizontalRight = new WPI_VictorSPX(Constants.Motors.HorizontalRight);
  private WPI_VictorSPX horizontalLeft = new WPI_VictorSPX(Constants.Motors.HorizontalLeft);
  
  //private DigitalInput horizantalLimitSwitch = new DigitalInput(Constants.LimitSwitches.HORIZANTALLIMIT);
  private DigitalInput shooterLimitSwitch = new DigitalInput(Constants.LimitSwitches.SHOOT);
  private double intakeSpeed = 0.35;

  public IntakeSubsystem() {}

  public void runIfLimitSwitchNotHit(){
    if (shooterLimitSwitch.get()){
      horizontalLeft.set(0);
      horizontalRight.set(0);
      rakeIntake.set(0);

    } else {
      horizontalLeft.set(-1);
      horizontalRight.set(1);
      rakeIntake.set(intakeSpeed);
    }
  }

  public void startIntake(){
    //This starts the motor on the rake.
    rakeIntake.set(intakeSpeed);
  } 

  public void stopIntake(){
    //This stops the motor on the rake.
    rakeIntake.set(0);
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
