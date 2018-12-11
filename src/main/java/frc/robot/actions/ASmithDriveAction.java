package frc.robot.actions;

import frc.lib.statemachine.Action;
import frc.robot.subsystems.ASmithDrive;

public class ASmithDriveAction extends Action {

    private ASmithDrive driveInstance = ASmithDrive.getInstance();


    private double leftPower, rightPower;

    public ASmithDriveAction(double leftPower, double rightPower){
        if(leftPower > 1 && leftPower <= 10 || rightPower > 1 && rightPower <= 10){
            leftPower /= 10;
            rightPower /= 10;
        }
        if(leftPower > 10 && leftPower <= 100 || rightPower > 10 && rightPower <= 100){
            leftPower /= 100;
            rightPower /= 100;
        }
        this.leftPower = leftPower;
        this.rightPower = rightPower;
    }

    @Override
    public void onStart() {
        driveInstance.setDrive(leftPower, rightPower);
    }

    @Override
    public void onLoop() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void onStop() {
        driveInstance.setDrive(0,0);
    }
}
