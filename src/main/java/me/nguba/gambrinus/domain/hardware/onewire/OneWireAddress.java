package me.nguba.gambrinus.domain.hardware.onewire;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OneWireAddress {

  private final String value;

  private OneWireAddress(final String value) {
    this.value = value;
  }

  public static OneWireAddress valueOf(final String address) {
    return new OneWireAddress(address);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final OneWireAddress other = (OneWireAddress) obj;
    if (value == null) {
      if (other.value != null) {
        return false;
      }
    } else if (!value.equals(other.value)) {
      return false;
    }
    return true;
  }

  public boolean isValid() {
    if (value != null && !value.isEmpty()) {
      return value.startsWith("28");
    }
    return false;
  }

  @Override
  public String toString() {
    return value;
  }

}
