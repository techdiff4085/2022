# 2022

Components on the robots and supporting details

Total:
6 Never Rest, 6 Falcons, 1 CIM, 1 Snowblower = 14 total

8 Motor Controllers, 2 limit switches

| Component | Subsystem | Motor Type | Limit Switch | CAN Port | DIO | PWM | Notes |
| --------- | ---------- | ---------- | ------------ | -------- | ----| --- | ----- |
| Drive - Front Left    | Drive | Falcon WPI_TalonFX 
| Drive - Front Right    | Drive | Falcon WPI_TalonFX 
| Drive - Rear Left    | Drive | Falcon WPI_TalonFX 
| Drive - Rear Right    | Drive | Falcon WPI_TalonFX 
| Rake - Right (Lift) | Intake | snowblower motor + Motor Controller (WPI_VictorSPX)  | 2 limit switches |||| 
| Rake - Left (Intake Rollers)  | Intake |NeveRest  + Motor Controller (WPI_VictorSPX)
| Horizontal Intake - Left   | Intake |NeveRest  + Motor Controllers (WPI_VictorSPX)
| Horizontal Intake - Right  | Intake |NeveRest  + Motor Controllers (WPI_VictorSPX)
| Elevator - Middle  | Shoot | NeveRest  + Motor Controller (WPI_VictorSPX)
| Elevator - Left | Shoot| NeveRest + Motor Controller (WPI_VictorSPX)
| Elevator - Right | Shoot | NeveRest + Motor Controller (WPI_VictorSPX)
| Shooter  | Shoot | Falcon WPI_TalonFX 
| Climb   | Climb|  CIM motor + Motor Controller (WPI_VictorSPX)

Buttons
| Remote Type | Button Function | Xbox button |
| ----------- | --------------- | ----------- |  
| Driver | Toggle Drive Speed | ? |
| Driver | Turn 180 degrees | ? |
| Driver | extend arms | ? |
| Driver | lower arms | ? |
| Shooter | Shoot motors on | ? |
| Shooter | Shoot motors off | ? |
| Shooter | drop rake & turn ON rake intake motor + horizontal motors | ? |
| Shooter | lift rake & turn OFF rake intake motor + horizontal motors | ? |  
  
Instructions:
1) Turn on upper shooter motor at beginning of teleop
2) Rake down turns on rake intake motor
3) While corner limit switch IS NOT triggered and rake is down, run horizontal intake motors.
4) When corner limit switch IS triggered, then turn off horizontal intake motors.
5) There are no xbox buttons for the horizontal intake motors.
6) Shoot button triggers left,middle,right shoot motors. 

  
