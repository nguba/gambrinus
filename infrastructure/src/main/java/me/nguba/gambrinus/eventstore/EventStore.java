/**
 * 
 */
package me.nguba.gambrinus.eventstore;

import java.io.IOException;

import javax.sql.DataSource;

import me.nguba.gambrinus.event.MutatorEvent;

/**
 * @author nguba
 *
 */
public final class EventStore
{
  private final DataSource       dataSource;
  private EventSerializerService serializer;

  private EventStore(DataSource dataSource, EventSerializerService serializer)
  {
    this.dataSource = dataSource;
    this.serializer = serializer;
  }

  public static final EventStore with(DataSource dataSource)
  {
    return new EventStore(dataSource, EventSerializerService.flatFormat());
  }

  public void record(MutatorEvent event) throws IOException
  {
    System.out.println();
    System.out.println(serializer.transform(event));
    System.out.println();
  }
}
