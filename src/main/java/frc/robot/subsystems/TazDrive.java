package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.lib.loops.Loop;
import frc.lib.util.DriveSignal;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;

public class TazDrive extends Subsystem {

    private static final TazDrive m_instance = null;
    private TalonSRX frontLeft, frontRight, rearLeft, rearRight;

    public TazDrive() {
        frontRight = new TalonSRX(Constants.DRIVE_FRONT_RIGHT_ID);
        frontLeft = new TalonSRX(Constants.DRIVE_FRONT_LEFT_ID);
        rearRight = new TalonSRX(Constants.DRIVE_BACK_RIGHT_ID);
        rearLeft = new TalonSRX(Constants.DRIVE_BACK_LEFT_ID);
    }

    private double leftSignal = 0, rightSignal = 0;
    public static TazDrive getInstance(){
        return m_instance;
    }

    private final Loop LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOP = new Loop() {
        @Override
        public void onStart(double timestamp) {

        }

        @Override
        public void onLoop(double timestamp) {
            double [] operatorStick = HIDHelper.getAdjStick(Constants.MASTER_STICK);
            DriveSignal motorOutput = arcadeDrive(operatorStick[1], operatorStick[0]);
            leftSignal = motorOutput.getLeft();
            rightSignal = motorOutput.getRight();
        }

        @Override
        public void onStop(double timestamp) {
        leftSignal = rightSignal = 0;
        }
    };
    @Override
    public void readPeriodicInputs() {

    }

    @Override
    public void writePeriodicOutputs() {
        frontRight.set(ControlMode.PercentOutput, rightSignal);
        rearRight.set(ControlMode.Follower, frontRight.getDeviceID());
        frontLeft.set(ControlMode.PercentOutput, leftSignal);
        rearLeft.set(ControlMode.Follower, rearRight.getDeviceID());
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

    public void setDrive(double left, double right){
        rightSignal = right;
        leftSignal = left;
    }

    @Override
    public void outputTelemetry() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void reset() {
        leftSignal = rightSignal = 0;
    }
}
