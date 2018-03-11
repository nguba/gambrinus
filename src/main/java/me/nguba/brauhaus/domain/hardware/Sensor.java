package me.nguba.brauhaus.domain.hardware;

import me.nguba.brauhaus.domain.Entity;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * @param <K>
 * @param <T>
 */
public interface Sensor<K, T> extends Entity<K> {

  T read();

  void update(T value);
}
