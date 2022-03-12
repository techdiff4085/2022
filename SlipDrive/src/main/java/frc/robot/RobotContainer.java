// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveAutonomousCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DropRakeCommand;
import frc.robot.commands.RaiseRakeCommand;
import frc.robot.commands.RunHorizontalMotors;
import frc.robot.commands.ToggleFastModeCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final XboxController m_driverController = new XboxController(0);
  private final XboxController m_shooterController = new XboxController(1);
  private final DriveCommand m_DriveCommand = new DriveCommand(m_driveSubsystem, m_driverController);
  private final AHRS navX = new AHRS(SPI.Port.kMXP);

  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_driveSubsystem.setDefaultCommand(m_DriveCommand);
    SmartDashboard.putBoolean("Fastmode on", m_driveSubsystem.getisFastMode());
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton buttonX = new JoystickButton(m_driverController, XboxController.Button.kX.value);
    buttonX.whenActive(new ToggleFastModeCommand(m_driveSubsystem));

    JoystickButton buttonB = new JoystickButton(m_driverController, XboxController.Button.kB.value);
    buttonB.whenActive(new DriveAutonomousCommand(m_driveSubsystem, 0, 0, 1, 700));

    JoystickButton leftBumb = new JoystickButton(m_driverController, XboxController.Button.kLeftBumper.value);
    leftBumb.whenHeld(new StartEndCommand(m_climbSubsystem::raiseClimb, m_climbSubsystem::stopClimb));

    JoystickButton rightBumb = new JoystickButton(m_driverController, XboxController.Button.kRightBumper.value);
    rightBumb.whenHeld(new StartEndCommand(m_climbSubsystem::lowerClimb, m_climbSubsystem::stopClimb));
  //Confirm buttons with Caitlyn
  //Driver - X button - toggle drive speed (SLOW / FAST)
  //Driver - left and/or right xbox toggle joysticks - Caitlyn's choice
  //Driver - turn 180 degrees
  //Driver - extend arm button
  //Driver - retract arm button

  JoystickButton buttonY = new JoystickButton(m_shooterController, XboxController.Button.kY.value);
  buttonY.whenActive(new RaiseRakeCommand(m_intakeSubsystem));

  JoystickButton buttonA = new JoystickButton(m_shooterController, XboxController.Button.kA.value);
  buttonA.whenActive(new DropRakeCommand(m_intakeSubsystem))
  .whenActive(new InstantCommand(m_shooterSubsystem::setUpperMotorSlow));

  JoystickButton shooterButtonB = new JoystickButton(m_shooterController, XboxController.Button.kB.value);
  shooterButtonB.whenPressed(new RunHorizontalMotors(m_intakeSubsystem));

  JoystickButton Xbutton = new JoystickButton(m_shooterController, XboxController.Button.kX.value);
  Xbutton.whenActive(
    new SequentialCommandGroup(
      new InstantCommand(m_intakeSubsystem::stopHorizontalMotors),
      new WaitCommand(2),
      new InstantCommand(m_intakeSubsystem::invertHorizontalMotors))
    );

  JoystickButton rightShoot = new JoystickButton(m_shooterController, XboxController.Button.kRightBumper.value);
    rightShoot.whenPressed(new InstantCommand(m_shooterSubsystem::shoot))
    .whenReleased(new InstantCommand(m_shooterSubsystem::stop));

    JoystickButton leftShoot = new JoystickButton(m_shooterController, XboxController.Axis.kLeftTrigger.value);
    leftShoot.whenPressed(new InstantCommand(m_shooterSubsystem::setUpperMotorSlow));

    JoystickButton shootRight = new JoystickButton(m_shooterController, XboxController.Axis.kRightTrigger.value);
    shootRight.whenPressed(new InstantCommand(m_shooterSubsystem::setUpperMotorFast));
  //Confirm buttons with Brendon
  //Shooter - shoot button - motors ON (left & right & middle & top motor)
  //Shooter - shoot button - motors OFF (left & right & middle & top motor)
  //Shooter - button to drop rake & start Rake Intake motors + 2 Horizontal Intake motors
  //Shooter - button to turn off the 3 Rake & Horizontal motors & lift rake (may be needed during 'Hang"')
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new SequentialCommandGroup(
      new DriveAutonomousCommand(m_driveSubsystem, -0.5, 0, 0, 500),
      new DriveAutonomousCommand(m_driveSubsystem, 0.5, 0, 0, 500)
    );
  }
}
