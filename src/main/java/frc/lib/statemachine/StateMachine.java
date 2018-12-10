package frc.lib.statemachine;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.concurrent.ConcurrentLinkedQueue;

public class StateMachine {

    private volatile static int state = -1;
    private volatile static ConcurrentLinkedQueue<ActionGroup> queuedStates;
    private volatile static ActionGroup currentState;
    private volatile static double t_start;

    private static Runnable Man = () -> {
        try {
            state = 0;
            SmartDashboard.putNumber("StateMachine/ state", state);
            //smart dash stuff here.
            if (queuedStates == null) {
                state = -2;
                SmartDashboard.putNumber("StateMachine/ state", state);
            } else {
                while (!queuedStates.isEmpty()) {
                    SmartDashboard.putNumber("StateMachine/ state", state);
                    currentState = queuedStates.poll(); //retrieve
                    currentState.onStart(); // start all actions in the current state
                    while (!currentState.isFinished()) {
                        t_start = Timer.getFPGATimestamp(); //time just before all actions get onLoop-ed
                        currentState.onLoop(); // executes the onloop of all actions in the current state
                        Timer.delay(0.02 - (Timer.getFPGATimestamp() - t_start)); //variable state machine delay
                    }
                    currentState.onStop(); //forcibly call all action's onStop method
                    state++; //advance state counter

                }
            }
        }
        catch (Exception e){
            state = -3;
            SmartDashboard.putNumber("StateMachine/ state", state);
        }
    };

    public static void runMan(StateMachineDescriptor stateMachineDescriptor) {
        queuedStates = stateMachineDescriptor.getStates();
        Thread thread = new Thread(Man);
        thread.start();

    }


}
