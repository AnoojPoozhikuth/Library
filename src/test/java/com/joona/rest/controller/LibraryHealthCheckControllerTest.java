package com.joona.rest.controller;

import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LibraryHealthCheckController.class)
@AutoConfigureMockMvc
public class LibraryHealthCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void healthCheckTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/library/app/health"))
                .andExpect(status().isOk())
                .andReturn();
        String responseString = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "Green");
        assertEquals(jsonObject.toJSONString(), responseString);
    }

}