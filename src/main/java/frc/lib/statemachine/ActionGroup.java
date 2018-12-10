package frc.lib.statemachine;

import edu.wpi.first.wpilibj.Timer;

import java.util.LinkedList;

public class ActionGroup {
    private LinkedList<Action> group;
    private double t_Start, t_Timeout;

    ActionGroup(Action[] actions, long timeout_ms) {
        t_Timeout = (double) timeout_ms / 1000.000;
        group = new LinkedList<>();
        for (Action action : actions) {
            group.add(action);
        }

    }

    ActionGroup(Action action, long timeout_ms) {
        t_Timeout = (double) timeout_ms / 1000.00000000;
        group = new LinkedList<>();
        group.add(action);
    }

    public void onStart() {
        t_Start = Timer.getFPGATimestamp();
        group.forEach(action -> action.onStart());
    }

    public void onLoop() {
        group.forEach(action -> action.onLoop());
    }

    public boolean isFinished() {
        //enforces state timeout
        if (t_Start + t_Timeout <= Timer.getFPGATimestamp()) return true;

        //check each action individually to see if it is complete
        boolean temp = true;
        for (Action action : group) {
            if(action.isFinished()) {
                //allows actions that have self completed
                //to finish when ready, not when the state is ready
                action.onStop();
            }
            else{
                //one or more actions is not finished so the state is not complete
                temp = false;
            }
        }
        return temp;
    }

    public void onStop() {
        group.forEach(action -> action.onStop());
    }

}

