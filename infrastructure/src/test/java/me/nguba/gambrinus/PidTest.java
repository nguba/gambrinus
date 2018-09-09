/*
    Copyright (C) 2018  Nicolai P. Guba

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package me.nguba.gambrinus;

import org.junit.jupiter.api.Test;

class PidTest
{

    @Test
    void test()
    {

        Pid pid = new Pid(0.25, 0.01, 0.4);
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
