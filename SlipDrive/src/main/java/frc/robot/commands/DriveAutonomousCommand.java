// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DriveAutonomousCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_driveSubsystem;
  private double m_x;
  private double m_y;
  private double m_z;
  private int m_numMilliseconds;
  private double m_startTime;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveAutonomousCommand(DriveSubsystem subsystem, double y, double x, double z, int numMilliseconds) {
    m_driveSubsystem = subsystem;
    m_y = y;
    m_x = x;
    m_z = z;
    m_numMilliseconds = numMilliseconds;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_startTime = System.currentTimeMillis();
    while (System.currentTimeMillis() - m_startTime < m_numMilliseconds){
      m_driveSubsystem.drive(m_y, m_x, m_z);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
