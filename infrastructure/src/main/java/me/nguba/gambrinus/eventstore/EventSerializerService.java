/**
 * 
 */
package me.nguba.gambrinus.eventstore;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import me.nguba.gambrinus.event.MutatorEvent;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
public final class EventSerializerService {

	private final ObjectMapper mapper;

	private EventSerializerService(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public static EventSerializerService flatFormat() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
		return new EventSerializerService(mapper);
	}

	public <E extends MutatorEvent> String transform(E event) throws IOException {
		return mapper.writeValueAsString(event);
	}
}
