package me.nguba.gambrinus.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import me.nguba.gambrinus.GambrinusControllerTest;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsRoot;
import me.nguba.gambrinus.owfs.OwfsSensor;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@GambrinusControllerTest
class AdminControllerTest
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
  void emptyVessels() throws Exception
  {
    final MvcResult result = mvc.perform(get("/api/admin/vessel")).andDo(print())
        .andExpect(status().isOk()).andReturn();

    assertEquals("[ ]", result.getResponse().getContentAsString());
  }

  @Test
  void getVessels() throws Exception
  {
    final Vessel[] expected = { Vessel.inactive(VesselId.of("a")),
        Vessel.inactive(VesselId.of("b")) };
    for (final Vessel v : expected)
      vessels.create(v);

    mvc.perform(get("/api/admin/vessel")).andDo(print())
        .andExpect(status().isOk()).andReturn();
  }

  @Test
  void getVesselNotFound() throws Exception
  {
    mvc.perform(get("/api/admin/vessel/{name}", "mash")).andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  void getVessel() throws Exception
  {
    final Vessel vessel = Vessel.of(VesselId.of("mash"),
                                    OwfsSensor.from(OwfsRoot.test(),
                                                    OneWireAddress.of("28.273B5D070000")));
    vessels.create(vessel);

    mvc.perform(get("/api/admin/vessel/{name}", "mash")).andDo(print())
        .andExpect(status().isOk()).andReturn();
  }

  @Test
  void getSensors() throws Exception
  {
    mvc.perform(get("/api/admin/sensor")).andDo(print())
        .andExpect(status().isOk()).andReturn();

  }

  @Test
  void createVessel() throws Exception
  {
    mvc.perform(post("/api/admin/vessel/{name}/{address}", "mash", "28.273B5D070000"))
        .andDo(print())
        .andExpect(status().isCreated()).andReturn();
  }
}
