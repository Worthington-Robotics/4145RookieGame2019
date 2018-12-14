package frc.lib.statemachine;

import edu.wpi.first.wpilibj.command.Command;

public abstract class Action {

    private boolean hasStopped = false;

    /**
     * code that executes when the action is called to start by the state machine
     */
    public abstract void onStart();

    /**
     * code that is called when the action is called to execute its loop function
     * <p> occurs approximately every 20 milliseconds
     */
    public abstract void onLoop();

    /**
     * method that allows the action to self determin when finished
     * <p> The state machine timeout has final say in what happens to the state
     * @return
     */
    public abstract boolean isFinished();

    /**
     * Internal state machine call to stop an action
     * <p>calls the on stop method if it has not been called before
     */
    public void doStop(){
        if(!hasStopped){
            onStop();
        }
        hasStopped = true;
    }

    /**
     * code that is called whn the state machine has determined that the action needs to terminate
     */
    public abstract void onStop();

    /**
     * converts an action to a wpilib command for buttons
     */
    public static Command toCommand(Action action){
        return new Command() {

            protected void initialize(){
                action.onStart();
            }

            protected void execute(){
                action.onLoop();
            }

            protected boolean isFinished() {
                return action.isFinished();
            }

            protected void end(){
                action.onStop();
            }
        };
    }
}
