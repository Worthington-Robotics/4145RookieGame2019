package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.loops.ILooper;
import frc.lib.loops.Loop;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;

public class Lift extends Subsystem{

    private static final Lift m_instance = new Lift();

    private Spark lift1, lift2;

    private DigitalInput lowerLimit, upperLimit, lowerupperlimit, lowerlowerlimit;

    private double upperelevatorpower;
    private double lowerelevatorpower;

    private Lift () {
        lift1 = new Spark(Constants.LIFT_1_ID);
        lift2 = new Spark(Constants.LIFT_2_ID);
        lowerLimit = new DigitalInput(Constants.UPPER_LOWER_LIMIT);
        upperLimit = new DigitalInput(Constants.UPPER_UPPER_LIMIT);
        lowerupperlimit = new DigitalInput(Constants.LOWER_UPPER_LIMIT);
        lowerlowerlimit = new DigitalInput(Constants.LOWER_LOWER_LIMIT);
    }

    public static Lift getInstance() {
        return m_instance;

    }

    private final Loop liftloop = new Loop() {

        @Override
        public void onStart(double timestamp) {

        }

        @Override
        public void onLoop(double timestamp) {
            if(DriverStation.getInstance().isOperatorControl()){
                upperelevatorpower = HIDHelper.getAdjStick(Constants.SECOND_STICK)[1];
            }
            upperelevatorpower = (lowerLimit.get() && upperelevatorpower < 0) ? 0 : upperelevatorpower;
            upperelevatorpower = (upperLimit.get() && upperelevatorpower > 0) ? 0 : upperelevatorpower;

            if(DriverStation.getInstance().isOperatorControl()){
                lowerelevatorpower = HIDHelper.getAdjStick(Constants.SECOND_STICK)[1];
            }
            lowerelevatorpower = (lowerlowerlimit.get() && lowerelevatorpower < 0) ? 0 : lowerelevatorpower;
            lowerelevatorpower = (lowerupperlimit.get() && lowerelevatorpower > 0) ? 0 : lowerelevatorpower;
        }

        @Override
        public void onStop(double timestamp) {

        }
    };

    public void setElevatorpower(double newpower){
        upperelevatorpower = lowerelevatorpower = newpower;
    }

    @Override
    public void readPeriodicInputs() {

    }

    @Override
    public void writePeriodicOutputs() {
        lift1.set(-upperelevatorpower);
        lift2.set(-lowerelevatorpower);
    }

    @Override
    public void outputTelemetry() {
        SmartDashboard.putNumber("Lift 1", upperelevatorpower);
        SmartDashboard.putBoolean("UpperLowerLimit", lowerLimit.get());
        SmartDashboard.putBoolean("UpperUpperLimit", upperLimit.get());

        SmartDashboard.putNumber("Lift 2", lowerelevatorpower);
        SmartDashboard.putBoolean("LowerLowerLimit", lowerlowerlimit.get());
        SmartDashboard.putBoolean("LowerUpperLimit", lowerupperlimit.get());
    }

    @Override
    public void stop() {

    }

    @Override
    public void reset() {

    }
    public void registerEnabledLoops(ILooper enabledLooper) {
        enabledLooper.register(liftloop);
    }
}
