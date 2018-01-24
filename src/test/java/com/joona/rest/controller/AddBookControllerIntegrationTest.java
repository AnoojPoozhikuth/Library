package com.joona.rest.controller;

import com.joona.config.LibraryApplication;
import com.joona.rest.base.BaseTest;
import com.joona.rest.response.AddBookError;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = LibraryApplication.class)
@AutoConfigureMockMvc
public class AddBookControllerIntegrationTest extends BaseTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldAddBookToLibrary() throws Exception {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("book", "Sherlock Holmes");
        requestJSON.put("author", "Arthur Conan Doyle");
        requestJSON.put("category", "Fiction");
        requestJSON.put("language", "English");
        MvcResult mvcResult = mockMvc
                .perform(post("/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON.toJSONString())
                ).andExpect(status().isCreated())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("status", "success");
        assertEquals(expectedResult.toJSONString(), responseString);
    }


}