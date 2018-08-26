package me.nguba.gambrinus.eventstore;

import static org.assertj.core.api.Assertions.assertThat;

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
  void transforToUnindentedString() throws Exception
  {
    final String transform = EventSerializerService.flatFormat().transform(this);
    assertThat(transform)
        .isEqualTo(String.format("{\"EventSerializerServiceTest\":{\"timestamp\":%d,\"one\":\"One value\",\"two\":2}}", timestamp));
  }

}
