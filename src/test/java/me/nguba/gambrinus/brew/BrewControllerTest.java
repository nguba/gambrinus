package me.nguba.gambrinus.brew;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import me.nguba.gambrinus.ApplicationMother;
import me.nguba.gambrinus.GambrinusControllerTest;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
@GambrinusControllerTest
class BrewControllerTest
{
  @Autowired
  private MockMvc mvc;

  @Autowired
  private VesselRepository vessels;

  @AfterEach
  void tearDown()
  {
    for (final Vessel v : vessels.findAll())
      vessels.delete(v.getId());
  }

  @Test
  @DisplayName("throws error when vessel not found")
  void changeSetpoint_NoVesselFound() throws Exception
  {
    mvc.perform(put("/api/brew/heat/{name}/{temperature}", "mash", 55.5))
        .andDo(print()).andExpect(status().isConflict());
  }

  @Test
  @DisplayName("throws error when no sensor assigned to vessel")
  void changeSetpoint_Inactive() throws Exception
  {
    vessels.create(Vessel.inactive(VesselId.of("mash")));

    mvc.perform(put("/api/brew/heat/{name}/{temperature}", "mash", 55.5))
        .andDo(print()).andExpect(status().isConflict());
  }

  @Test
  @DisplayName("mutates the setpoint on the vessel")
  void changeSetpoint() throws Exception
  {
    final Vessel mashTun = ApplicationMother.mashTun();
    vessels.create(mashTun);

    assertThat(vessels.read(mashTun.getId()).get().setpoint()).isEqualTo(Temperature.celsius(0));

    mvc.perform(put("/api/brew/heat/{name}/{temperature}", mashTun.getId(), 55.5))
        .andDo(print()).andExpect(status().isOk());

    assertThat(vessels.read(mashTun.getId()).get().setpoint()).isEqualTo(Temperature.celsius(55.5));
  }
}
