// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  private WPI_VictorSPX rakeRight = new WPI_VictorSPX(Constants.RakeRight);
  private WPI_VictorSPX rakeLeft = new WPI_VictorSPX(Constants.RakeLeft);
  private WPI_VictorSPX horizontalRight = new WPI_VictorSPX(Constants.HorizontalRight);
  private WPI_VictorSPX horizontalLeft = new WPI_VictorSPX(Constants.HorizontalLeft);
  private DigitalInput upperLimitSwitch = new DigitalInput(Constants.UpperLimitSwitch);
  private DigitalInput lowerLimitSwitch = new DigitalInput(Constants.LowerLimitSwitch);

  public IntakeSubsystem() {}

  public void dropRake() {
   //This lowers rakeRight and rakeLeft until it hits the lower limit switch.
  }

  public void liftRake() {
    //This raises rakeRight and rakeLeft until it his the upper limit switch.
  }

  public void rakePull() {
    //Starts horizonal motors.
  }

  public void rakeStop() {
    //Stops horzontal motors.
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
