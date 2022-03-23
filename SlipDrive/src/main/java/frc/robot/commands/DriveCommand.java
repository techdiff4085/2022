// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.SpeedMode;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_drivesubsystem;
  private final Joystick m_joystick;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveCommand(DriveSubsystem subsystem, Joystick m_driverController) {
    m_drivesubsystem = subsystem;
    m_joystick = m_driverController;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double y = m_joystick.getY();
    double x = m_joystick.getX();
    double z = m_joystick.getRawAxis(3)*-1;

    if (m_drivesubsystem.getSpeedMode() == SpeedMode.FAST){
      m_drivesubsystem.drive(Math.abs(y)*y/1.4, Math.abs(x)*x/1.4, Math.abs(z)*z/2.5);
    }
    else if (m_drivesubsystem.getSpeedMode() == SpeedMode.MEDIUM){
      m_drivesubsystem.drive(Math.abs(y)*y/2, Math.abs(x)*x/2, Math.abs(z)*z/3.25);
    }
    else {
      m_drivesubsystem.drive(Math.abs(y)*y/8, Math.abs(x)*x/4, Math.abs(z)*z/4);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
