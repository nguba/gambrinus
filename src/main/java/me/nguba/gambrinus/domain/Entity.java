package me.nguba.gambrinus.domain;

/**
 * An object defined primarily by its identiy and <em>not</em> by their attributes.
 * <p>
 * Entities have special modelling and design considerations. They have lifecycles that can
 * radically change their form or content, but a thread of continuity must be maintained. They
 * represent a thread of identity that runs through time and often across distinct representations.
 * </p>
 * <p>
 * Sometimes such an object must be matched with another object even though their attributes differ.
 * An object must be distinguished from other objects even though they may have the same attributes.
 * </p>
 * <p>
 * Their identities must be defined so that they can be effectively tracked. Their class
 * definitions, responsiblities, atrributes and associations should revolve around who they are,
 * rather than the particular attributes they carry.
 * </p>
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * @param <T>
 * @see Service
 * @see ValueObject
 * @see Aggregate
 */
public interface Entity<T> {

  /**
   * Unique identifier to distinguish objects even though they may have the same attributes.
   *
   * @return the identity
   */
  T id();
}
