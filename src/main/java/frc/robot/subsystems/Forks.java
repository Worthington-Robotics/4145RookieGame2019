package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.lib.loops.ILooper;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;

public class Forks extends Subsystem {
    private static final Forks m_instance = new Forks();
    private PeriodicIO periodic;
    private Spark leftMotor, rightMotor;

    private Forks() {
        periodic = new PeriodicIO();
        leftMotor = new Spark(Constants.LEFT_SHOOTER_ID);
        rightMotor = new Spark(Constants.RIGHT_SHOOTER_ID);
    }

    public static Forks getInstance() {
        return m_instance;
    }

    public void setShotPower(double Power) {
        periodic.ShotPower = Power;
    }

    @Override
    public void readPeriodicInputs() {
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
