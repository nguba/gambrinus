package me.nguba.gambrinus.process;

import me.nguba.gambrinus.process.Step;
import me.nguba.gambrinus.process.Temperature;

import java.time.Duration;

public enum ProcessMother {
  ;

  public static Step doughIn() {
    return Step.valueOf("Einmaischen", Temperature.valueOf(58.0), Duration.ofMinutes(15));
  }

  public static Step betaAmylase() {
    return Step.valueOf("Maltose Rast", Temperature.valueOf(63.0), Duration.ofMinutes(15));
  }

  public static Step alphaAmylase() {
    return Step.valueOf("Verzuckerungs Rast", Temperature.valueOf(72.0), Duration.ofMinutes(15));
  }
}
