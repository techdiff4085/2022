// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
  private final RunHorizontalMotors m_runHorizMotorsCommand = new RunHorizontalMotors(m_intakeSubsystem);
  //private final AHRS navX = new AHRS(SPI.Port.kMXP);

  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_driveSubsystem.setDefaultCommand(m_DriveCommand);
    m_intakeSubsystem.setDefaultCommand(m_runHorizMotorsCommand);
    SmartDashboard.putBoolean("Fastmode on", m_driveSubsystem.getisFastMode());
    SmartDashboard.putBoolean("Horizontal Motors on", m_intakeSubsystem.isReversed());
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    //////////////////////////////
    //Driver

    //Toggle Fast Mode
    JoystickButton driverButtonX = new JoystickButton(m_driverController, XboxController.Button.kX.value);
    driverButtonX.whenActive(new ToggleFastModeCommand(m_driveSubsystem));

    //180 degree spin
    JoystickButton driverButtonB = new JoystickButton(m_driverController, XboxController.Button.kB.value);
    driverButtonB.whenActive(new DriveAutonomousCommand(m_driveSubsystem, 0, 0, 1, 700));

    //Raise climb arms
    JoystickButton driverLeftBump = new JoystickButton(m_driverController, XboxController.Button.kLeftBumper.value);
    driverLeftBump.whenHeld(new StartEndCommand(m_climbSubsystem::raiseClimb, m_climbSubsystem::stopClimb))
    .whenPressed(new InstantCommand(m_intakeSubsystem::stopHorizontalMotors))
    .whenPressed(new InstantCommand(m_driveSubsystem::setToSlowMode));

    //Lower Climb arms
    JoystickButton driverRightBump = new JoystickButton(m_driverController, XboxController.Button.kRightBumper.value);
    driverRightBump.whenHeld(new StartEndCommand(m_climbSubsystem::lowerClimb, m_climbSubsystem::stopClimb));

    //////////////////////////////
    //Shooter

    //Raise Rake
    JoystickButton shooterButtonY = new JoystickButton(m_shooterController, XboxController.Button.kY.value);
    shooterButtonY.whenActive(new RaiseRakeCommand(m_intakeSubsystem));

    //Lower Rake
    JoystickButton shooterButtonA = new JoystickButton(m_shooterController, XboxController.Button.kA.value);
    shooterButtonA.whenActive(new DropRakeCommand(m_intakeSubsystem))
    .whenActive(new InstantCommand(m_shooterSubsystem::startShooterMotor));

    //Start Horiz Motors (normal direction)
    JoystickButton shooterButtonB = new JoystickButton(m_shooterController, XboxController.Button.kB.value);
    shooterButtonB.whenPressed(new InstantCommand(m_intakeSubsystem::setHorizontalMotorsForward));
  
    //Pop the Rake
    JoystickButton shooterButtonX = new JoystickButton(m_shooterController, XboxController.Button.kX.value);
    shooterButtonX.whenPressed(new RaiseRakeCommand(m_intakeSubsystem))
    .whenReleased(new DropRakeCommand(m_intakeSubsystem));

    //Index the ball to shoot
    JoystickButton shooterRightBump = new JoystickButton(m_shooterController, XboxController.Button.kRightBumper.value);
    shooterRightBump.whenPressed(new InstantCommand(m_shooterSubsystem::shoot))
    .whenReleased(new InstantCommand(m_shooterSubsystem::stop));

    //Decrease Motor Speed
    JoystickButton shooterBackButton = new JoystickButton(m_shooterController, XboxController.Button.kBack.value);
    shooterBackButton.whenPressed(new InstantCommand(m_shooterSubsystem::decreaseMotorSpeed));

    //Increase Motor Speed
    JoystickButton shooterStartButton = new JoystickButton(m_shooterController, XboxController.Button.kStart.value);
    shooterStartButton.whenPressed(new InstantCommand(m_shooterSubsystem::increaseMotorSpeed));
 }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new SequentialCommandGroup(
      new DropRakeCommand(m_intakeSubsystem),
      new InstantCommand(m_shooterSubsystem::setMotorSpeedForTarmac),
      new WaitCommand(4),
      new InstantCommand(m_shooterSubsystem::shoot),
      new WaitCommand(3),
      new InstantCommand(m_shooterSubsystem::stop),
      new DriveAutonomousCommand(m_driveSubsystem, 0.2, 0, 0, 1100)
    );
  }
}
