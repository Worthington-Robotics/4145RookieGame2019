package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.lib.loops.Loop;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;

public class Lift extends Subsystem{
    private Spark lift1, lift2;

    public Lift () {
        lift1 = new Spark(1);
        lift2 = new Spark(2);
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
