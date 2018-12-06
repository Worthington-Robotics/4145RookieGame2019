package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.lib.loops.Loop;
import frc.lib.util.DriveSignal;
 import frc.lib.util.HIDHelper;
import frc.robot.Constants;
/*
    public class meineke_Drive extends Subsystem
    {
        private static final meineke_Drive m_instance = new meineke_Drive();
    private TalonSRX topLeft, topRight, backLeft, backRight;
    private double leftSignal = 0, rightSignal = 0;

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
        topRight.set(ControlMode.PercentOutput, rightSignal);
        backRight.set(ControlMode.Follower, topRight.getDeviceID());
        topLeft.set(ControlMode.PercentOutput, leftSignal);
        backLeft.set(ControlMode.Follower, topLeft.getDeviceID());
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
}
*/