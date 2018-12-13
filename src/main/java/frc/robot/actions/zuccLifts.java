package frc.robot.actions;

import frc.lib.statemachine.Action;
import frc.robot.subsystems.Lift;

public class zuccLifts extends Action{

    private Lift instance = Lift.getInstance();
    private double liftpower;

    public zuccLifts(double liftPower){
            liftpower=liftPower;
    }

    @Override
    public void onStart() {
            instance.setElevatorpower(liftpower);
    }

    @Override
    public void onLoop() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void onStop() {
            instance.setElevatorpower(0);
    }
}
