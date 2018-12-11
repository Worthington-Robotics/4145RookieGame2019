package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import frc.lib.loops.Loop;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;

public class Lift extends Subsystem{
    private Spark lift1, lift2;

    private DigitalInput lowerLimit, upperLimit;

    private double elevatorpower;

    public Lift () {
        lift1 = new Spark(Constants.LIFT_1_ID);
        lift2 = new Spark(Constants.LIFT_2_ID);
    }

    private final Loop liftloop;

    {
        liftloop = new Loop() {

            @Override
            public void onStart(double timestamp) {

            }

            @Override
            public void onLoop(double timestamp) {
                double[] liftStick = HIDHelper.getAdjStick(Constants.SECOND_STICK);
                elevatorpower = liftStick[0];
            }

            @Override
            public void onStop(double timestamp) {

            }
        };
    }

    @Override
    public void readPeriodicInputs() {

    }

    @Override
    public void writePeriodicOutputs() {
        lift1.set(elevatorpower);
        lift2.set(elevatorpower);
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
