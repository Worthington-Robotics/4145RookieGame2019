package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.lib.loops.ILooper;
import frc.lib.loops.Loop;
import frc.lib.util.DriveSignal;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;

public class ASmithDrive extends Subsystem {

    private static final ASmithDrive m_instance = null;

    private TalonSRX frontleft, frontright, rearleft, rearright;
    private double leftSignal = 0, rightSignal = 0;

    public static ASmithDrive getInstance() {return m_instance;}

    public ASmithDrive(){

        frontleft = new TalonSRX(1);
        frontright = new TalonSRX(4);
        rearleft = new TalonSRX(3);
        rearright = new TalonSRX(2);
        rearleft.setInverted(true);
        frontleft.setInverted(true);

    }

    private final Loop mloop = new Loop()   {
        @Override
        public void onStart(double timestamp) {

        }
        @Override
        public void onLoop(double timestamp)    {
            double [] operatorStick = HIDHelper.getAdjStick(Constants.MASTER_STICK);
            DriveSignal motorOutput = arcadeDrive(operatorStick[1], operatorStick[0]);
            leftSignal = motorOutput.getLeft();
            rightSignal = motorOutput.getRight();
        }

        @Override
        public void onStop(double timestamp)    {
            leftSignal = 0;
            rightSignal =0;
        }
    };

    @Override
    public void readPeriodicInputs() {

    }

    @Override
    public void writePeriodicOutputs() {
        frontright.set(ControlMode.PercentOutput, rightSignal);
        rearright.set(ControlMode.Follower, frontright.getDeviceID());
        frontleft.set(ControlMode.PercentOutput, leftSignal);
        rearleft.set(ControlMode.Follower, frontleft.getDeviceID());
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

    }
    @Override
    public void registerEnabledLoops(ILooper enabledLooper) {
        enabledLooper.register(mloop);
    }

}
