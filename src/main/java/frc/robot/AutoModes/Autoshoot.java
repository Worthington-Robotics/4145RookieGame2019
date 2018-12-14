package frc.robot.AutoModes;

import frc.lib.statemachine.Action;
import frc.lib.statemachine.StateMachineDescriptor;
import frc.robot.actions.ActionTemplate;
import frc.robot.actions.ForkAction.ShotPower;

public class Autoshoot extends StateMachineDescriptor {

    public Autoshoot(){
        addSequential(new ActionTemplate(0.5,0.5), 2000);
        addParallel(new Action[]{new ActionTemplate(0.5,0.5), new ActionTemplate(0.5, 0.5)}, 1000);
    }

}
