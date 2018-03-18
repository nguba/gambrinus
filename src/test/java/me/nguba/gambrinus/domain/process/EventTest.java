package me.nguba.gambrinus.domain.process;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EventTest {
 
  private final Event<String> event = Event.valueOf("expected");
  
  @Test
  void toStringContainsValue() {
    assertThat(event.toString()).contains("value=expected");
  }

  @Test
  void returnsWrappedValue() {
    assertThat(event.value()).isEqualTo("expected");
  }

  @Test
  void hashCodeEqualsContract() {
    EqualsVerifier.forClass(Event.class);
  }
}
