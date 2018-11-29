package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.loops.Loop;
import frc.lib.util.DriveSignal;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;

public class DriveTylerZupfer extends Subsystem {
    private static final DriveTylerZupfer m_DRIVE_TYLER_ZUPFER = new DriveTylerZupfer();
    private DriveSignal TazDrive(double xSpeed, double zRotation) {
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
    public static DriveTylerZupfer getInstance() {
        return m_DRIVE_TYLER_ZUPFER;
    }
    private TalonSRX Frontleft, frontright, rearleft, rearright;
    double zero = 0;
    private final Loop mloop = new Loop() {
        @Override
        public void onStart(double timestamp) {

        }

        @Override
        public void onLoop(double timestamp) {
             double [] operatormaster = HIDHelper.getAdjStick(Constants.MASTER_STICK);
             DriveSignal MotorInput = TazDrive(operatormaster[1], operatormaster[0]);
        }

        @Override
        public void onStop(double timestamp) {

        }
    };
    @Override
    public void outputTelemetry() {
        SmartDashboard.putNumber("Zero", zero);
    }

    @Override
    public void stop() {

    }

    @Override
    public void reset() {

    }
}
