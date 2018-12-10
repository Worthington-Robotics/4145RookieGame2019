package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.lib.loops.Loop;
import frc.lib.util.DriveSignal;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;

public class RaccDrive extends Subsystem {
private static final RaccDrive r_instance = null;

private TalonSRX fR, fL, rR, rL;
private double leftSignal = 0, rightSignal = 0;

public static RaccDrive getInstance() {return r_instance; }

private DriveSignal arcadeDrive(double xSpeed, double zRotation) {
double leftMotorOutput;
double rightMotorOutput;

double maxInput = Math.copySign(Math.max(Math.abs(xSpeed),
        Math.abs(zRotation)), xSpeed);

    if (xSpeed >= 0.0) {
        if (zRotation >= 0.0) {
            leftMotorOutput = maxInput;
            rightMotorOutput = xSpeed - zRotation;
        } else {
            leftMotorOutput = xSpeed + zRotation;
            rightMotorOutput = maxInput;
        }
    } else {
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
private final Loop mLoop = new Loop() {
    @Override
    public void onStart (double timestamp) {

    }

    @Override
    public void onLoop (double timestamp) {
        double[] operatorStick = HIDHelper.getAdjStick(Constants.MASTER_STICK);
        DriveSignal motorOutput = arcadeDrive(operatorStick[1],operatorStick[2]);
        leftSignal = motorOutput.getLeft();
        rightSignal = motorOutput.getRight();
    }

    @Override
    public void onStop (double timestamp) {

    }
};

    @java.lang.Override
    public void readPeriodicInputs() {

    }

    @java.lang.Override
    public void writePeriodicOutputs() {
    fR.set(ControlMode.PercentOutput, rightSignal);
    rR.set(ControlMode.Follower, fR.getDeviceID());
    fL.set(ControlMode.PercentOutput, leftSignal);
    rL.set(ControlMode.Follower, fL.getDeviceID());
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
