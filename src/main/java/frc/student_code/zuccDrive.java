package frc.student_code;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.lib.loops.Loop;
import frc.lib.util.DriveSignal;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;
import frc.robot.subsystems.Subsystem;

public class zuccDrive extends Subsystem {


    private static final zuccDrive ZucC = null; //new zuccDrive();

    private TalonSRX frontleft, frontright, rearleft, rearright;
    private double leftsignal = 0, rightsignal = 0;

    public zuccDrive (){
        frontleft = new TalonSRX(Constants.DRIVE_FRONT_LEFT_ID);
        frontright = new TalonSRX(Constants.DRIVE_FRONT_RIGHT_ID);
        rearleft = new TalonSRX(Constants.DRIVE_BACK_LEFT_ID);
        rearright = new TalonSRX(Constants.DRIVE_BACK_RIGHT_ID);
    }

    public static zuccDrive getInstance() {
        return ZucC;
    }

    private final Loop zuccloop = new Loop() {
        @Override
        public void onStart(double timestamp) {


        }


        @Override
        public void onLoop(double timestamp) {
            double [] operatorStick = HIDHelper.getAdjStick(Constants.MASTER_STICK);
            DriveSignal motorOutput = arcadeDrive(operatorStick[1], operatorStick[0]);
            leftsignal = motorOutput.getLeft();
            rightsignal = motorOutput.getRight();

        }

        @Override
        public void onStop(double timestamp) {

        }
    };

    @Override
    public void readPeriodicInputs() {
    }

    @Override
    public void writePeriodicOutputs() {
    frontright.set(ControlMode.PercentOutput, rightsignal);
    rearright.set(ControlMode.Follower, frontright.getDeviceID());
    frontleft.set(ControlMode.PercentOutput, leftsignal);
    rearleft.set(ControlMode.Follower, frontleft.getDeviceID());
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

    public void setDrive(double leftPower, double rightPower) {
        leftsignal = leftPower;
                rightsignal = rightPower;
    }
}

