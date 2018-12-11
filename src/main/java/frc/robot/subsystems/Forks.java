package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;

public class Forks extends Subsystem {

        private static final Forks m_instance = new Forks ();

        private TalonSRX leftMotor, rightMotor;

        private double leftsignal = 0, rightsignal = 0;

        public static Forks getInstance () { return m_instance;}

        private Forks () {
            leftMotor = new TalonSRX(Constants.FORKS_LEFT_ID);
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
