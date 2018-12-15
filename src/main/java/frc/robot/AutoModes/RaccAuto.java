package frc.robot.AutoModes;

import frc.lib.statemachine.Action;
import frc.lib.statemachine.StateMachineDescriptor;
import frc.robot.actions.DriveAction;
import frc.robot.actions.ForkAction;
import frc.robot.actions.LiftAction;

public class RaccAuto extends StateMachineDescriptor {

    public RaccAuto(){

        addSequential(new DriveAction(0.40  , 0.35),5080);
        addParallel(new Action[] {new DriveAction(0.25, 0.25),new LiftAction(0.75)}, 4010);
        addSequential(new ForkAction(ForkAction.ShotPower.Shoot), 500);
        //three right wheels on right tape line of blue square, flush against wall

    }
}
