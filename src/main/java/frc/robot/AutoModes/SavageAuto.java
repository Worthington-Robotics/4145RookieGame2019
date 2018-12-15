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
        addSequential(new LiftAction(.75), 100);
        addSequential(new DriveAction(.35,.35),2500);
        addSequential(new LiftAction(.5),100);
        addSequential(new ForkAction(ForkAction.ShotPower.Shoot),100);




    }
}
