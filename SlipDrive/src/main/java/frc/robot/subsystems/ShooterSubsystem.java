// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//Initial Shooter Subsytem
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private WPI_VictorSPX elevatorLeft = new WPI_VictorSPX(Constants.Motors.elevatorLeft);
  private WPI_VictorSPX elevatorRight = new WPI_VictorSPX(Constants.Motors.elevatorRight);
  private WPI_VictorSPX elevatorMiddle = new WPI_VictorSPX(Constants.Motors.elevatorMiddle);
  private WPI_TalonFX shooter = new WPI_TalonFX(Constants.Motors.shooter);
  private double speed = 0.4;

  public ShooterSubsystem() {
  //Start shooter motor
  }

  public void increaseMotorSpeed(){
    speed+= 0.1;
    if (speed > 1){
      speed = 1;
    }
    shooter.set(speed); 
    SmartDashboard.putNumber("Shooter Speed", speed*100);
  }


  public void decreaseMotorSpeed(){
    speed-= 0.1;
    if (speed < 0.3){
      speed = 0.3;
    }
    shooter.set(speed);
    SmartDashboard.putNumber("Shooter Speed", speed*100);
  }
// used for autonomous
  public void setMotorSpeedForTarmacLow(){
    speed = 0.40; // dont ever change this 
    shooter.set(speed);
    SmartDashboard.putNumber("Shooter Speed", speed*100);
  }
//used for autonomous
  public void setMotorSpeedForTarmacHigh(){
    speed = 0.90;// was .95 (on target but bounces out), trying .90
    shooter.set(speed);
    SmartDashboard.putNumber("Shooter Speed", speed*100);
  }

  public void startShooterMotor(){
    shooter.set(speed);
    SmartDashboard.putNumber("Shooter Speed", speed*100);
  }

  public void shoot(){
    //Start three elevator motors
    elevatorLeft.set(-0.7);
    elevatorRight.set(0.7);
    elevatorMiddle.set(-0.7);
  }

  public void stop(){
    elevatorLeft.set(0);
    elevatorRight.set(0);
    elevatorMiddle.set(0);
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
