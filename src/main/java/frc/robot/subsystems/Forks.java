package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Spark;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;

public class Forks extends Subsystem {
        private PeriodicIO periodic;

        private static final Forks m_instance = new Forks ();

        private Spark leftMotor, rightMotor;

        public static Forks getInstance () { return m_instance;}

        public void setShotPower(double Power) {periodic.ShotPower = Power; }

         private Forks () {
            periodic = new PeriodicIO();
            leftMotor = new Spark(Constants.FORKS_LEFT_ID);
            rightMotor = new Spark(Constants. FORKS_RIGHT_ID);
        }


    @Override
    public void readPeriodicInputs() {
        double [] operatorstick = HIDHelper.getAdjStick(Constants.SECOND_STICK);
    }

    @Override
    public void writePeriodicOutputs() {
    leftMotor.set(periodic.ShotPower);
    rightMotor.set(-periodic.ShotPower);
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
    public static class PeriodicIO {
            public double ShotPower = 0.0;
        }



}
