/**
 *
 */
package me.nguba.gambrinus.query.vessel.find;

import me.nguba.gambrinus.cqrs.query.Query;
import me.nguba.gambrinus.equipment.VesselId;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
public final class FindVessel implements Query
{
  public static FindVessel of(final VesselId id)
  {
    return new FindVessel(id);
  }

  private final VesselId id;

  private FindVessel(final VesselId id)
  {
    this.id = id;
  }

  @Override
  public boolean equals(final Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final FindVessel other = (FindVessel) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public VesselId getId()
  {
    return id;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + (id == null ? 0 : id.hashCode());
    return result;
  }

}
