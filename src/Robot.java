package frc.robot;


//import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/* importlarımız */

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;



/* importlarımız */


public class Robot extends TimedRobot {


public static Timer at = new Timer();

public static NetworkTableEntry X;
public static double nX = 0;
public static double nnX = 0;


  /* motor sürücülerimiz */

  public static PWMVictorSPX sagon = new PWMVictorSPX(1);
  public static VictorSP sagarka = new VictorSP(0);
  public static PWMVictorSPX solon = new PWMVictorSPX(3);
  public static VictorSP solarka = new VictorSP(2);

  /* motor sürücülerimiz */

  /* SpeedController Groups */

  public static SpeedControllerGroup sol = new SpeedControllerGroup(solon, solarka);
  public static SpeedControllerGroup sag = new SpeedControllerGroup(sagon, sagarka);

  /* SpeedController Groups */


  /* Sopa ve lil'sopa */

  public static Joystick sopa = new Joystick(0);
  public static Joystick lil_sopa = new Joystick(1);

  /* Sopa ve lil'sopa */


  /* DriveBase */

  public static DifferentialDrive arcade = new DifferentialDrive(sol, sag);

  /* DriveBase */

 
  @Override
  public void robotInit() { // Robot başladıktaktan sonra 1 kere çalışır
    //CameraServer.getInstance().startAutomaticCapture();

    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable masa = inst.getTable("idris_ustam");

    X = masa.getEntry("X");

    
  }

  @Override
  public void robotPeriodic() { // Robot çalıştığı süre boyunca çalışır

  
  nX = (double) X.getNumber(0);
  nnX = (int) nX;
  
  
    
  }

  @Override
  public void autonomousInit() { // Otonom başladıktaktan sonra 1 kere çalışır


    
  }

  @Override
  public void autonomousPeriodic() { // Otonom süresi boyunca çalışır



  if (nnX > 0   && nnX <=  70){

    System.out.println("ROBOT SOLA GIDIYOR");
    arcade.arcadeDrive(0.0, -0.4);

  }
  else if  (nnX > 70 && nnX <= 110){
    
    System.out.println("ROBOT DUZ GIDIYOR");
    arcade.arcadeDrive(0.0, 0.0);
    
  }
  else if (nnX > 110 &&  nnX <= 180){
    
    System.out.println("ROBOT SAGA GIDIYOR");
    arcade.arcadeDrive(0.0, 0.4);
  
  }
  else{
    System.out.println("NEREDE BU ROBOT");
    arcade.arcadeDrive(0.0, 0.0);
  }
   
   }


  @Override
  public void teleopInit() { // Teleop başladıktan sonra 1 kere çalışır
    
  }
  
  @Override
  public void teleopPeriodic() { // Teleop süresi boyunca çalışır
  

    arcade.arcadeDrive(sopa.getY() * -1, sopa.getZ());
    
    if(sopa.getRawButton(7)){

      at.reset();
      at.start();

      while (true){

        if (at.get() <= 3){

          arcade.arcadeDrive(0.5, 0);
          
         }
         
        else if (at.get() <= 4.5 && at.get() > 3) {
      
          arcade.arcadeDrive(0.0, -0.5);
        }
      
        else if (at.get() <= 6.5 && at.get() > 4.5) {   
          
          arcade.arcadeDrive(0.5, 0);
 
        }
        else{
          break;
        }

        if(sopa.getRawButton(8)){

          break;

        }
      }
    }

    


    // önce Build sonra Deploy
  }
}
