package me.nguba.gambrinus;

import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        final Vessel[] expected = { Vessel.of(VesselId.of("a")), Vessel.of(VesselId.of("b")) };
        for (final Vessel v : expected) {
            vessels.create(v);
        }

        mvc.perform(get("/api/admin/vessel")).andDo(print())
                .andExpect(status().isOk()).andReturn();

        // assertEquals("[ ]", result.getResponse().getContentAsString());
    }
}
