// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.commands.DriveAutonomousCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.RunHorizontalMotors;
import frc.robot.commands.StartShooterMotorCommand;
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
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final Joystick m_driverController = new Joystick(0);
  private final XboxController m_shooterController = new XboxController(1);
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final DriveCommand m_DriveCommand = new DriveCommand(m_driveSubsystem, m_driverController);
  private final RunHorizontalMotors m_runHorizMotorsCommand = new RunHorizontalMotors(m_intakeSubsystem);
  private final StartShooterMotorCommand m_startShooterMotorCommand = new StartShooterMotorCommand(m_shooterSubsystem);
  private final Spark m_lights = new Spark(Constants.Extra.LIGHTS);
  //private final AHRS navX = new AHRS(SPI.Port.kMXP);

  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_driveSubsystem.setDefaultCommand(m_DriveCommand);
    m_intakeSubsystem.setDefaultCommand(m_runHorizMotorsCommand);
   
//3/24
    m_shooterSubsystem.setDefaultCommand(m_startShooterMotorCommand);
    m_lights.set(0.61);//.61 red blue .87
    
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

    //Set Slow Mode
    JoystickButton slowModeButton = new JoystickButton(m_driverController,5);
    slowModeButton.whenActive(new InstantCommand(m_driveSubsystem::setSlowMode));
    
    //Set Medium Mode
    JoystickButton medModeButton = new JoystickButton(m_driverController,2);
    medModeButton.whenActive(new InstantCommand(m_driveSubsystem::setMediumMode));

    //Set Fast Mode
    JoystickButton fastModeButton = new JoystickButton(m_driverController,6);
    fastModeButton.whenActive(new InstantCommand(m_driveSubsystem::setFastMode));

    //Raise climb arms
    JoystickButton driverLeftBump = new JoystickButton(m_driverController, 3);
    driverLeftBump.whenHeld(new StartEndCommand(m_climbSubsystem::raiseClimb, m_climbSubsystem::stopClimb))
    .whenPressed(new InstantCommand(m_driveSubsystem::setSlowMode));

    //Lower Climb arms
    JoystickButton driverRightBump = new JoystickButton(m_driverController, 4);
    driverRightBump.whenHeld(new StartEndCommand(m_climbSubsystem::lowerClimb, m_climbSubsystem::stopClimb));

    //Drive forward a little bit (3/24)
    JoystickButton driveForward = new JoystickButton(m_driverController, 1);
     driveForward.whenActive(new DriveAutonomousCommand(m_driveSubsystem, -0.2, 0.0, 0, 300));// autonomous is 1100
    //driveForward.whenHeld(new StartEndCommand(m_driveSubsystem::drive(0.2, 0.0, 0.0)));

    //////////////////////////////
    //Shooter

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

// To change between atonomous modes, commen/uncomment the corresponding code below

    // Autonomous - LOW HUB - 1 ball only
     
      new InstantCommand(m_shooterSubsystem::setMotorSpeedForTarmacLow),   
      new WaitCommand(4),
      new InstantCommand(m_shooterSubsystem::shoot),
      new WaitCommand(3),
      new InstantCommand(m_shooterSubsystem::stop),
      new DriveAutonomousCommand(m_driveSubsystem, 0.2, 0, 0, 1100)
    
    // Autonomous - LOW HUB - 2 balls only
     /* untested autonomous 
      new InstantCommand(m_shooterSubsystem::setMotorSpeedForTarmacLow),   
      new WaitCommand(3),
      new InstantCommand(m_shooterSubsystem::shoot),
      new WaitCommand(1),
      // turn 180
      new DriveAutonomousCommand(m_driveSubsystem, 0, 0, 0.6, 260),
      // drive forward (setting X should drive forward)
      new DriveAutonomousCommand(m_driveSubsystem, 0.0, 0.2, 0, 1100),
      new DriveAutonomousCommand(m_driveSubsystem, 0.2, 0.0, 0, 500),
      // turn 180
      new DriveAutonomousCommand(m_driveSubsystem, 0, 0, 0.6, 260),
      //shoot
      new InstantCommand(m_shooterSubsystem::shoot),
      new WaitCommand(1),
      new InstantCommand(m_shooterSubsystem::stop)
      */

    // Autonomous - HIGH HUB - don't use, distance isn't correct.
    /*
    new InstantCommand(m_shooterSubsystem::setMotorSpeedForTarmacHigh),
    new DriveAutonomousCommand(m_driveSubsystem, 0.2, 0, 0, 3400), // was 3500 back up a bit more
    new WaitCommand(3),
    new InstantCommand(m_shooterSubsystem::shoot),
    new WaitCommand(3),
    //3/24 added
    new InstantCommand(m_shooterSubsystem::stop)
    */
    );
  }
}
