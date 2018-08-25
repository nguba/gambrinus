package me.nguba.gambrinus.equipment;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import me.nguba.gambrinus.ddd.Repository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class VesselRepository implements Repository<VesselId, Vessel>
{
  private final ConcurrentMap<VesselId, Vessel> store = new ConcurrentHashMap<>();

  @Override
  public Optional<VesselId> create(final Vessel vessel)
  {
    if (vessel == null)
      return Optional.empty();

    store.put(vessel.getId(), vessel);
    return Optional.of(vessel.getId());
  }

  @Override
  public Optional<Vessel> read(final VesselId identifier)
  {
    if (identifier == null)
      return Optional.empty();

    return Optional.ofNullable(store.get(identifier));
  }

  @Override
  public void update(final Vessel aggregate)
  {
    throw new UnsupportedOperationException("Vessels cannot be updated");
  }

  @Override
  public void delete(final VesselId identifier)
  {
    if (identifier == null)
      return;
    store.remove(identifier);
  }

  public Vessel[] findAll()
  {
    return store.values().toArray(new Vessel[store.size()]);
  }
}
