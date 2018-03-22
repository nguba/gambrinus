package me.nguba.gambrinus.domain;

/**
 * 
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * @see Entity
 * @see ValueObject
 * @see Service
 */
public interface Aggregate<R> {

  R root();
}
