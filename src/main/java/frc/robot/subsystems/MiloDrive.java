package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.lib.loops.Loop;
import frc.lib.util.HIDHelper;

import static frc.robot.Constants.MASTER_STICK;

public class MiloDrive extends Subsystem{

    public static final MiloDrive m_MiloDrive = new MiloDrive();

    private TalonSRX FrontLeft, FrontRight, BackLeft, BackRight;
//creates a TalonSRX for each TalonSRX on the drive train

    private final Loop ChickenFingersLoop = new Loop();
//creates loop ChickenFingersLoop

    public void onLoop (double TimeStamp) {
        OI = HIDHelper.HIDConstants MASTER_STICK;
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