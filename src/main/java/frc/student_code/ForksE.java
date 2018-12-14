package frc.student_code;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;
import frc.robot.subsystems.Subsystem;

public class ForksE extends Subsystem {

    private static final ForksE m_instance = null;

    private TalonSRX LeftMotor, RightMotor;

    private double LeftSignal = 0, RightSignal = 0;

    public static ForksE getInstance() {
        return m_instance;
    }

    public ForksE() {
        LeftMotor = new TalonSRX(Constants.FORKS_LEFT_ID);
        RightMotor = new TalonSRX(Constants.FORKS_RIGHT_ID);
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