package frc.student_code;

import frc.lib.statemachine.Action;
import frc.student_code.meineke_Drive;

public class meineke_action extends Action
{
    private meineke_Drive driver = new meineke_Drive();

    private double leftPower, rightPower;

    public meineke_action(double powerLeft, double powerRight)
    {
        leftPower = powerLeft;
        rightPower = powerRight;
    }

    @Override
    public void onStart()
    {
        driver.setDrive(leftPower, rightPower);
    }

    @Override
    public void onLoop()
    {

    }

    @Override
    public boolean isFinished()
    {
        return false;
    }

    @Override
    public void onStop()
    {
        driver.setDrive(0,0);
    }
}
