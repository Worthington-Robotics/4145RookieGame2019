package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;

public class Lift extends Subsystem{
    private Spark lift1, lift2;
    public Lift () {
        lift1 = new Spark(1);
        lift2 = new Spark(2);
    }

    @Override
    public void readPeriodicInputs() {

    }

    @Override
    public void writePeriodicOutputs() {

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
