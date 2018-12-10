package frc.robot.actions;

import frc.lib.statemachine.Action;
import frc.robot.subsystems.DriveTemplate;

public class ActionTemplate extends Action {

    //local variable of the subsystems you intend to use in the command
    private DriveTemplate driveInstance = DriveTemplate.getInstance();

    //local variables used to pass to on start, on loop and on stop
    //methods from the constructor. keep in mind the constructor runs when the robot starts, not when the state machine begins
    private double leftPower, rightPower;

    //action constructor. adding arguments allows for actions to be more versatile
    public ActionTemplate(double leftpower, double rightpower){
        leftPower = leftpower;
        rightPower = rightpower;
    }

    //method that runs when the action is started by the state machine
    @Override
    public void onStart() {
        //apply power to drive by using set method
        driveInstance.setDrive(leftPower, rightPower);
    }

    @Override
    public void onLoop() {
        //no-op
    }

    //if the action should end on its own,
    //this should return true when ready to end
    @Override
    public boolean isFinished() {
        //wait for forcible timeout by state machine
        return false;
    }

    //method that runs when the action has been called to stop
    //can either end on its own or the state machine forced it to end
    @Override
    public void onStop() {
        //set drive train back to neutral
        driveInstance.setDrive(0,0);

    }
}
