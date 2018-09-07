package me.nguba.gambrinus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PIDControllerTest
{

    @Test
    void test()
    {

        PIDController pid = new PIDController(0.25, 0.01, 0.4);
        //pid.setOutputLimits(1);
        // miniPID.setMaxIOutput(2);
        // miniPID.setOutputRampRate(3);
        //miniPID.setOutputFilter(.3);
        pid.setSetpointRange(100);

        double target = 100;

        double actual = 0;
        double output = 0;

        pid.setSetpoint(0);
        pid.setSetpoint(target);

        System.err.printf("Target\tActual\tOutput\tError\n");
        // System.err.printf("Output\tP\tI\tD\n");

        // Position based test code
        for (int i = 0; i < 100; i++) {

            // if(i==50)miniPID.setI(.05);

            if (i == 60)
                target = 50;

            // if(i==75)target=(100);
            // if(i>50 && i%4==0)target=target+(Math.random()-.5)*50;

            output = pid.getOutput(actual, target);
            actual = actual + output;

            // System.out.println("==========================");
            // System.out.printf("Current: %3.2f , Actual: %3.2f, Error: %3.2f\n",actual, output,
            // (target-actual));
            System.err.printf("%3.2f\t%3.2f\t%3.2f\t%3.2f\n",
                              target,
                              actual,
                              output,
                              (target - actual));

            // if(i>80 && i%5==0)actual+=(Math.random()-.5)*20;
        }
    }

}
