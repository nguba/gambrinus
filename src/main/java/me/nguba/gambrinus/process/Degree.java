package me.nguba.gambrinus.process;

import me.nguba.gambrinus.domain.ValueObject;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public enum Degree implements ValueObject {
  CELSIUS("C"), KELVIN("K"), FARENHEIT("F");

  String symbol;

  Degree(final String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return symbol;
  }
}
