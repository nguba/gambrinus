package me.nguba.gambrinus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * 
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc(print = MockMvcPrint.SYSTEM_OUT)
class MashControllerTest
{
    @Autowired
    private MockMvc mvc;
    
    @Test
    void testGetVessels() throws Exception
    {
        mvc.perform(get("/api/mash/vessel")).andDo(print()).andExpect(status().isOk());
    }

}
