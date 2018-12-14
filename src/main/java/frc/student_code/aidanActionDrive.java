package frc.student_code;

import frc.lib.statemachine.Action;
import frc.student_code.zuccDrive;

public class aidanActionDrive extends Action {

    private zuccDrive zucC = zuccDrive.getInstance();

    private double leftPower, rightPower;

    public aidanActionDrive(double leftpower, double rightpower) {
        leftPower = leftpower;
        rightPower = rightpower;
    }

    @Override
    public void onStart() {
            zucC.setDrive(leftPower, rightPower);

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
        zucC.setDrive(0,0);
    }


}
