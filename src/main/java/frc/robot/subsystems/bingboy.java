package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.lib.loops.Loop;
import frc.lib.util.DriveSignal;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;

public class bingboy extends Subsystem{

    private static final bingboy m_instance = null;

    private TalonSRX backleft, backright, frontleft, frontright;
    private double leftsignal = 0, rightsignal = 0;

    public static bingboy getinstance() {
        return m_instance;
    }
    public bingboy ()
    {
        frontright = new TalonSRX(Constants.DRIVE_FRONT_RIGHT_ID);
        frontleft = new TalonSRX(Constants.DRIVE_FRONT_LEFT_ID);
        backright = new TalonSRX(Constants.DRIVE_BACK_RIGHT_ID);
        backleft = new TalonSRX(Constants.DRIVE_BACK_LEFT_ID);
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
    private final Loop bingLoop = new Loop() {
        @Override
        public void onStart(double timestamp) {

        }

        @Override
        public void onLoop(double timestamp) {
            double [] operatorStick = HIDHelper.getAdjStick(Constants.MASTER_STICK);
            DriveSignal moterOutput = arcadeDrive(operatorStick[1], operatorStick[0]);
            leftsignal = moterOutput.getLeft();
            rightsignal = moterOutput.getRight();
        }

        @Override
        public void onStop(double timestamp) {

        }
    };
    @java.lang.Override
    public void readPeriodicInputs() {

    }

    @java.lang.Override
    public void writePeriodicOutputs() {
        frontleft.set(ControlMode.PercentOutput, leftsignal);
        backleft.set(ControlMode.Follower, frontleft.getDeviceID());
        frontright.set(ControlMode.PercentOutput, rightsignal);
        backright.set(ControlMode.Follower, frontright.getDeviceID());
    }

    @java.lang.Override
    public void outputTelemetry() {

    }

    @java.lang.Override
    public void stop() {

    }

    @java.lang.Override
    public void reset() {

    }
}

