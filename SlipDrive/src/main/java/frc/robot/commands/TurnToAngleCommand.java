// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;


/** An example command that uses an example subsystem. */
public class TurnToAngleCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_driveSubsystem;
  private final AHRS m_navX;
  private final double m_turnToAngle;
  private final PIDController m_controller;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TurnToAngleCommand(DriveSubsystem driveSubsystem, AHRS navX, double turnToAngle) {
    
    m_driveSubsystem = driveSubsystem;
    m_navX = navX;
    m_turnToAngle = turnToAngle;
    m_controller = new PIDController(Constants.KP, Constants.KI, Constants.KD);
    m_controller.enableContinuousInput(-180, 180);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double rate = m_controller.calculate(m_navX.getYaw(), m_turnToAngle); 
    m_driveSubsystem.turnToAngle(rate/2, m_navX);  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_navX.getYaw() >= m_turnToAngle;
  }
}
