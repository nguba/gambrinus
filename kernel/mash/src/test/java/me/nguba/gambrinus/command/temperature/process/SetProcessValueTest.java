package me.nguba.gambrinus.command.temperature.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;
import nl.jqno.equalsverifier.EqualsVerifier;

class SetProcessValueTest {

	private final SetProcessValue command = SetProcessValue.on(VesselId.of("mash"), Temperature.celsius(66.6));
	
	@Test
	void vesselId() {
		assertThat(command.getId()).isEqualTo(VesselId.of("mash"));
	}

	@Test
	void processValue() {
		assertThat(command.getProcessValue()).isEqualTo(Temperature.celsius(66.6));
	}

	@Test
	void equalityContract() {
		EqualsVerifier.forClass(SetProcessValue.class).usingGetClass().verify();
	}
}
