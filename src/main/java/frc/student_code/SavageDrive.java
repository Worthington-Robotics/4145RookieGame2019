package frc.student_code;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.lib.loops.Loop;
import frc.lib.util.DriveSignal;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;
import frc.robot.subsystems.Subsystem;

public class SavageDrive extends Subsystem {

        private static final SavageDrive m_instance = null;

        private TalonSRX frontleft, backleft, frontright, backright;
        private double leftsignal=0, rightsignal=0;

        public static SavageDrive getInstance() {
            return m_instance;
        }

        public SavageDrive() {
            frontright = new TalonSRX(Constants.DRIVE_FRONT_RIGHT_ID);
            frontleft =  new TalonSRX(Constants.DRIVE_FRONT_RIGHT_ID);
            backleft = new TalonSRX (Constants.DRIVE_BACK_LEFT_ID);
            backright = new TalonSRX(Constants .DRIVE_BACK_RIGHT_ID);
        }

        private final Loop mloop= new Loop() {

            public void onStart(double timestamp){

            }

            @Override
            public void onLoop(double timestamp) {
                double [] operatorstick = HIDHelper.getAdjStick(Constants.MASTER_STICK);
                DriveSignal motorOutput = arcadeDrive(operatorstick[1], operatorstick[0]);
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
        backright.set(ControlMode.Follower, frontright.getDeviceID());
        frontleft.set(ControlMode.PercentOutput, leftsignal);
        backleft.set(ControlMode.Follower, frontleft. getDeviceID());
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
