package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.lib.util.HIDHelper;

public class Constants {
    //Talon IDs
    public static final int DRIVE_FRONT_LEFT_ID = 1;
    public static final int DRIVE_BACK_RIGHT_ID = 2;
    public static final int DRIVE_BACK_LEFT_ID = 3;
    public static final int DRIVE_FRONT_RIGHT_ID = 4;


    //Stick Constants
    public static final Joystick MASTER = new Joystick(0);
    public static final Joystick SECOND = new Joystick(1);
    public static final HIDHelper.HIDConstants MASTER_STICK = new HIDHelper.HIDConstants(MASTER, 0.15, 1.0, 1.0, 0.45, 2);
    public static final HIDHelper.HIDConstants SECOND_STICK = new HIDHelper.HIDConstants(SECOND, 0.05, 1.0, 1.0, 1.0, 2);

    //Startup Constants
    public static final boolean IS_COMP_BOT = true;
    public static final String ROBOT_NAME = "Whatever_you_want";
}



