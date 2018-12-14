package frc.robot.AutoModes;

import frc.lib.statemachine.Action;
import frc.lib.statemachine.StateMachineDescriptor;
import frc.robot.actions.ActionTemplate;
import frc.robot.subsystems.Lift;

public class TazAuto extends StateMachineDescriptor {
    public TazAuto(){
        addSequential(new , 2000);

        /*creates a single action state
        //useful for waiting on things to happen or doing single tasks
        addSequential(new ActionTemplate(0.5,0.5), 2000);

        //creates a multi action state
        //useful for completing multiple parallel tasks like driving somewhere
        // while moving a lift or spinning up a flywheel
        //DO NOT USE THE SAME COMMAND AS MULTIPLE ACTIONS -- THIS IS JUST AN EXAMPLE
        addParallel(new Action[]{new ActionTemplate(0.5,0.5), new ActionTemplate(0.5, 0.5)}, 1000);*/
    }


}
