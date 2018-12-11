package frc.robot.actions;

import frc.lib.statemachine.Action;
import frc.robot.subsystems.DriveTemplate;
import frc.robot.subsystems.TazDrive;

public class TazDriveAction extends Action {
    private TazDrive driveInstance = TazDrive.getInstance();
    private double leftPower, rightPower;
    public TazDriveAction(double leftpower, double rightpower){
        leftPower = leftpower;
        rightPower = rightpower;
    }

    @Override
    public void onStart() {
        driveInstance.setDrive(leftPower, rightPower);
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
        driveInstance.setDrive(0,0);
    }
}
