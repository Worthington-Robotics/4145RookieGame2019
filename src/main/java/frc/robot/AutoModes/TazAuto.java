package frc.robot.AutoModes;

import frc.lib.statemachine.Action;
import frc.lib.statemachine.StateMachineDescriptor;
import frc.robot.actions.ActionTemplate;
import frc.robot.actions.DriveAction;
import frc.robot.actions.ForkAction;
import frc.robot.actions.LiftAction;
import frc.robot.subsystems.Lift;

public class TazAuto extends StateMachineDescriptor {
    public TazAuto(){
        addSequential(new LiftAction(1), 1000);
        addParallel(new Action [] {new ActionTemplate(1, 1), new LiftAction(1)} , 1000); // TODO Tune
        addSequential(new DriveAction(.5,0), 2000); //TODO Tune
        addSequential(new DriveAction(1,1), 2000); //TODO Tune
        addSequential(new ForkAction(ForkAction.ShotPower.Shoot),1000); // TODO Tune

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
