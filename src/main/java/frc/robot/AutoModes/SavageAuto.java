package frc.robot.AutoModes;

import frc.lib.statemachine.Action;
import frc.lib.statemachine.StateMachine;
import frc.lib.statemachine.StateMachineDescriptor;
import frc.robot.actions.ActionTemplate;
import frc.robot.actions.DriveAction;
import frc.robot.actions.ForkAction;
import frc.robot.actions.LiftAction;

public class SavageAuto extends StateMachineDescriptor {

    public SavageAuto() {
        addParallel(new Action[]{new LiftAction(1), new DriveAction(.75, .75)},1000);
        addSequential(new DriveAction(.5, 0), 1000);
        addSequential(new DriveAction (.5,.5),1000 );
        addSequential(new ForkAction(ForkAction.ShotPower.Shoot),1000);



    }
}
