package me.nguba.gambrinus.eventstore;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.Instant;

import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.event.MutatorEvent;

public class EventSerializerServiceTest extends MutatorEvent {

	public EventSerializerServiceTest() {
		super(Instant.now());
	}

	public String one = "One value";

	public Integer two = Integer.valueOf(2);

	@Test
	void test() throws Exception {
		String transform = EventSerializerService.flatFormat().transform(this);
		System.out.println(transform);
	}

}
