/**
 * 
 */
package me.nguba.gambrinus.command.temperature.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * 
 */
class SetProcessValueHandlerTest {

	private SetProcessValueHandler handler;

	private VesselRepository repo = new VesselRepository();
	
	private VesselId id = VesselId.of("bk");
	
	private Temperature processValue = Temperature.celsius(68.0);

	@BeforeEach
	void setUp() {
		handler = SetProcessValueHandler.from(repo);
	}
	
	@Test
	void mutateNonExistingVessel() {
		assertThrows(IllegalArgumentException.class, () -> handler.changeStateFor(SetProcessValue.on(id, processValue)));
	}

	@Test
    void mutateNullCommand()
    {
        assertThrows(IllegalArgumentException.class, ()-> handler.changeStateFor(null));
    }
	
	@Test
	void mutateMofifiesAggregate() {
		repo.create(Vessel.inactive(id));

		handler.changeStateFor(SetProcessValue.on(id, processValue));

		assertThat(repo.read(id).get().processValue()).isEqualTo(processValue);
	}
	
	@Test
	void validation() throws Exception {
		final Errors results = Errors.empty();

		handler.validate(SetProcessValue.on(null, null), results);

		final ValidationFailed exception = assertThrows(ValidationFailed.class, () -> results.verify());

		assertThat(exception.getErrors().has(Reason.from("No vesselId"))).isTrue();
		assertThat(exception.getErrors().has(Reason.from("No processValue"))).isTrue();
	}
	
	@Test
	void validateVesselExists() throws Exception {
		final Errors results = Errors.empty();

		handler.validate(SetProcessValue.on(id, processValue), results);

		final ValidationFailed exception = assertThrows(ValidationFailed.class, () -> results.verify());

		assertThat(exception.getErrors().has(Reason.from("Vessel not found: bk"))).isTrue();
	}
}
