package me.nguba.gambrinus.domain;

/**
 * Services model important domain operations that can't find a natural home in an {@link Entity} or
 * {@link ValueObject}.
 * <p>
 * A Service is an operation offered as an interface that stands alone in the model, without
 * encapsulating state (stateless), as {@link Entity} or {@link ValueObject} do.
 * </p>
 * <p>
 * Services emphasise the relationship with other objects and are defined purely in terms of what
 * they can do for a client. A service tends to be named for an <em>activity</em>, rather than an
 * entity--a verb rather than a noun.
 * </p>
 * <p>
 * Operation names should come from the <em>Ubiquitous Language</em> or be introduced into it.
 * Parameters and results should be domain objects.
 * </p>
 * <p>
 * A good service has the following characteristics
 * <ol>
 * <li>The operation relates to a domain concept that is not a natural part of an {@link Entity} or
 * {@link ValueObject}.
 * <li>The interface is defined in terms of other elements of the domain model.
 * <li>The operation is stateless.
 * </ol>
 * </p>
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * @see Entity
 * @see ValueObject
 * @see Aggregate
 */
public interface Service
{

}
