package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.lib.loops.Loop;
import frc.lib.util.DriveSignal;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;
public class exe extends Subsystem {

    private static final exe m_instance = new exe();

    private TalonSRX frontRight, frontLeft, rearRight, rearLeft;
    private double leftSignal = 0, rightSignal = 0;

    public static exe getInstance() { return m_instance; }


    private final Loop mLoop = new Loop() {

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

        }
    };

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

    @Override
    public void readPeriodicInputs() {

    }

    @Override
    public void writePeriodicOutputs() {
        frontRight.set(ControlMode.PercentOutput, rightSignal);
        rearRight.set(ControlMode.Follower, frontRight.getDeviceID());
        frontLeft.set(ControlMode.PercentOutput, leftSignal);
        rearLeft.set(ControlMode.Follower, frontLeft.getDeviceID());

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
}
