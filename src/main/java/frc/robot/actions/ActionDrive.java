package frc.robot.actions;

import frc.lib.statemachine.Action;
import frc.robot.subsystems.bingboy;

public class ActionDrive extends Action{

    private bingboy drive = bingboy.getinstance();


    private double leftpower, rightpower;

    public ActionDrive(double leftPower, double rightPower){
        leftpower = leftPower;
        rightpower = rightPower;
    }

    @Override
    public void onStart() {
        drive.setdrive(leftpower, rightpower);
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
        drive.setdrive(0, 0);
    }
}
