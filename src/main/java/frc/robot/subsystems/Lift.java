package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
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
        lowerLimit = new DigitalInput(Constants.LOWER_LIFT_LIMIT);
        upperLimit = new DigitalInput(Constants.UPPER_LIFT_LIMIT);
    }

    private final Loop liftloop;

    {
        liftloop = new Loop() {

            @Override
            public void onStart(double timestamp) {

            }

            @Override
            public void onLoop(double timestamp) {
                if(DriverStation.getInstance().isOperatorControl()){
                    elevatorpower = HIDHelper.getAdjStick(Constants.SECOND_STICK)[0];
                }
                elevatorpower = (lowerLimit.get() && elevatorpower < 0) ? 0 : elevatorpower;
                elevatorpower = (upperLimit.get() && elevatorpower > 0) ? 0 : elevatorpower;
            }

            @Override
            public void onStop(double timestamp) {

            }
        };
    }
    private void setElevatorpower(double newpower){
        elevatorpower = newpower;
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
