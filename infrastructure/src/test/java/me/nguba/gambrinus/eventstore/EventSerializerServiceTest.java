package me.nguba.gambrinus.eventstore;

import java.time.Instant;

import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.event.MutatorEvent;

public class EventSerializerServiceTest extends MutatorEvent
{

  public EventSerializerServiceTest()
  {
    super(Instant.now());
  }

  public String one = "One value";

  public Integer two = Integer.valueOf(2);

  @Test
  void test() throws Exception
  {
    final String transform = EventSerializerService.flatFormat().transform(this);
    System.out.println(transform);
  }

}
