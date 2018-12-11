package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.lib.loops.Loop;
import frc.lib.util.DriveSignal;
 import frc.lib.util.HIDHelper;
import frc.robot.Constants;

public class meineke_Drive extends Subsystem
{
    private static final meineke_Drive m_instance = null;
    private TalonSRX frontLeft, frontRight, backLeft, backRight;
    private double leftSignal = 0, rightSignal = 0;

    public meineke_Drive()
    {
        frontLeft = new TalonSRX(Constants.DRIVE_FRONT_LEFT_ID);
        frontRight = new TalonSRX(Constants.DRIVE_FRONT_RIGHT_ID);
        backLeft = new TalonSRX(Constants.DRIVE_BACK_LEFT_ID);
        backRight = new TalonSRX(Constants.DRIVE_BACK_RIGHT_ID);
    }

    public static meineke_Drive getInstance()
    {
        return m_instance;
    }

    private final Loop myLoop = new Loop()
    {
        @Override
       public void onStart(double timestamp)
       {

       }

        @Override
        public void onLoop(double timestamp)
        {
            double[] operatorStick = HIDHelper.getAdjStick(Constants.MASTER_STICK);
            DriveSignal motorOutput = arcadeDrive(operatorStick[1], operatorStick[0]);
            leftSignal = motorOutput.getLeft();
            rightSignal = motorOutput.getRight();
        }

        @Override
        public void onStop(double timestamp)
        {
            rightSignal = 0;
            leftSignal = 0;
        }


    };

    public void readPeriodicInputs()
    {

    }

    public void writePeriodicOutputs()
    {
        frontRight.set(ControlMode.PercentOutput, rightSignal);
        backRight.set(ControlMode.Follower, frontRight.getDeviceID());
        frontLeft.set(ControlMode.PercentOutput, leftSignal);
        backLeft.set(ControlMode.Follower, frontLeft.getDeviceID());
    }

    @Override
    public void outputTelemetry() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void reset() {

    }

    private DriveSignal arcadeDrive(double xSpeed, double zRotation)
    {
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

    public void setDrive(double left, double right)
    {
        leftSignal = left;
        rightSignal = right;
    }

}
