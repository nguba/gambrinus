package me.nguba.brauhaus.brewpi.domain;

import me.nguba.brauhaus.domain.process.Temperature;

/**
 * Allows for unit testing behaviour which needs the output from the BrewPi spark protocol.
 * 
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class BrewPiSimulator {

  private Temperature processValue = Temperature.valueOf(0);
  
  public void processValue(Temperature value) {
    processValue = value;
  }
}
