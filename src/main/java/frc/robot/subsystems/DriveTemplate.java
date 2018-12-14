package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.loops.ILooper;
import frc.lib.loops.Loop;
import frc.lib.util.DriveSignal;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;
public class DriveTemplate extends Subsystem {

    //the global instance used to access all methods below
    private static final DriveTemplate m_instance = new DriveTemplate();

    //motor controllers and other IO related to the subsystem
    private TalonSRX frontLeft, frontRight, rearLeft, rearRight;

    //data objects / variables used for computations
    private double leftSignal = 0, rightSignal = 0;

    //allows outside classes like subsystem manager to access this class
    public static DriveTemplate getInstance(){
        return m_instance;
    }

    //private constructor means the object can only be created inside the class
    //before you can do anything with the cookie jar, you must actually create it
    // the constructor creates the cookie jar in this case
    private DriveTemplate(){
        frontRight = new TalonSRX(Constants.DRIVE_FRONT_RIGHT_ID);
        frontLeft = new TalonSRX(Constants.DRIVE_FRONT_LEFT_ID);
        rearRight = new TalonSRX(Constants.DRIVE_BACK_RIGHT_ID);
        rearLeft = new TalonSRX(Constants.DRIVE_BACK_LEFT_ID);
        frontLeft.setInverted(true);
        rearLeft.setInverted(true);
    }


    private final Loop mloop = new Loop() {
        @Override
        public void onStart(double timestamp) {

        }

        //runs approximately every 20 milliseconds
        //used for calculations after retrieving inputs from sensors
        @Override
        public void onLoop(double timestamp) {
            if (DriverStation.getInstance().isOperatorControl()) {
                double[] operatorStick = HIDHelper.getAdjStick(Constants.MASTER_STICK);
                DriveSignal motorOutput = arcadeDrive(operatorStick[1], operatorStick[2]);
                leftSignal = motorOutput.getLeft();
                rightSignal = motorOutput.getRight();
            }
        }

        @Override
        public void onStop(double timestamp) {

        }
    };

    //does what it says
    //reads inputs from sensors before running the onLoop method above
    @Override
    public void readPeriodicInputs() {

    }

    //does what it says
    //designed for writing outputs to actuators in an orderly manner
    @Override
    public void writePeriodicOutputs() {
        frontRight.set(ControlMode.PercentOutput, rightSignal);
        rearRight.set(ControlMode.Follower, frontRight.getDeviceID());
        frontLeft.set(ControlMode.PercentOutput, leftSignal);
        rearLeft.set(ControlMode.Follower, frontLeft.getDeviceID());
    }

    private DriveSignal arcadeDrive(double xSpeed, double zRotation) {
        double leftMotorOutput;
        double rightMotorOutput;

        double maxInput = Math.copySign(Math.max(Math.abs(xSpeed),
                Math.abs(zRotation)), xSpeed);

        if (xSpeed >= 0.0) {
            // First quadrant, else second quadrant
            if (zRotation >= 0.0) {
                leftMotorOutput = maxInput;
                rightMotorOutput = xSpeed - zRotation;
            } else {
                leftMotorOutput = xSpeed + zRotation;
                rightMotorOutput = maxInput;
            }
        } else {
            // Third quadrant, else fourth quadrant
            if (zRotation >= 0.0) {
                leftMotorOutput = xSpeed + zRotation;
                rightMotorOutput = maxInput;
            } else {
                leftMotorOutput = maxInput;
                rightMotorOutput = xSpeed - zRotation;
            }
        }
        return new DriveSignal(rightMotorOutput, leftMotorOutput);
    }

    //method allowing access to set the drive values for autonomous
    public void setDrive(double left, double right){
        rightSignal = right;
        leftSignal = left;
    }

    //smartdashboard prints go here
    @Override
    public void outputTelemetry() {
        SmartDashboard.putNumber("Left", leftSignal);
        SmartDashboard.putNumber("Right", rightSignal);
    }

    @Override
    public void stop() {
        //no real operations happen here
        //technically for the robot when shutting down
    }

    //when the robot is told to reset, this method is called
    //useful for resetting sensors and internal variables
    @Override
    public void reset() {

    }

    //allows the subsystem manager to control this subsystem
    @Override
    public void registerEnabledLoops(ILooper enabledLooper) {
        enabledLooper.register(mloop);
    }
}
