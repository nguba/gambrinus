package me.nguba.gambrinus.ddd;

/**
 * Value Objects are object that <em>describe things</em>.
 * <p>
 * In contrast to {@link Entity} objects, Value Objects have no identity and are immutable. They
 * represent elements of the design that we care about only for <em>what</em> they are, not
 * <em>who</em> or <em>which</em> they are.
 * </p>
 * <p>
 * Value Objects are often passed as parameters in messages (methods) between objects. They are
 * frequently transient and are often used as attributes of {@link Entity} objects and sometimes
 * even other Value Objects. Sometimes they even reference other {@link Entity} objects.
 * </p>
 * <p>
 * When required, Value Objects are often part of a shared kernel to encourage software re-use and
 * consistency across projects. This sharing should happen judiciously.
 * </p>
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * @see Entity
 * @see Service
 * @see Aggregate
 */
public interface ValueObject
{
}
