package com.joona.rest.controller;

import com.joona.rest.base.BaseTest;
import com.joona.rest.response.AddBookError;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AddBookControllerTest extends BaseTest {

    private static final String SOURCE_DIRECTORY = "json/add_book/";
    private MockMvc mockMvc;
    private AddBookController addBookController = new AddBookController();


    @Before
    public void setUp(){
      mockMvc = MockMvcBuilders.standaloneSetup(addBookController).build();
    }

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

    @Test
    public void testBookNameIsMandatory() throws Exception {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("author", "Arthur Conan Doyle");
        requestJSON.put("category", "Fiction");
        requestJSON.put("language", "English");
        MvcResult mvcResult = mockMvc
                .perform(post("/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON.toJSONString())
                ).andExpect(status().isBadRequest())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();

        String contentFromFile = getContentFromFile(SOURCE_DIRECTORY.concat("book_name_mandatory_error.json"));

        assertEquals(contentFromFile, responseString);
    }

    @Test
    public void testAuthorNameIsMandatory() throws Exception {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("book", "Sherlock Holmes");
        requestJSON.put("category", "Fiction");
        requestJSON.put("language", "English");
        MvcResult mvcResult = mockMvc
                .perform(post("/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON.toJSONString())
                ).andExpect(status().isBadRequest())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();

        String contentFromFile = getContentFromFile(SOURCE_DIRECTORY.concat("author_name_mandatory_error.json"));;

        assertEquals(contentFromFile, responseString);
    }

    @Test
    public void testAuthorNameIsProper() throws Exception {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("book", "Sherlock Holmes");
        requestJSON.put("author", "Arthur Conan Doyle123");
        requestJSON.put("category", "Fiction");
        requestJSON.put("language", "English");
        MvcResult mvcResult = mockMvc
                .perform(post("/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON.toJSONString())
                ).andExpect(status().isBadRequest())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();

        String contentFromFile = getContentFromFile(SOURCE_DIRECTORY.concat("Improper_author_name_error.json"));

        assertEquals(contentFromFile, responseString);
    }

    @Test
    public void testAuthorNameStartsWithDot() throws Exception {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("book", "Sherlock Holmes");
        requestJSON.put("author", ".Arthur Conan Doyle");
        requestJSON.put("category", "Fiction");
        requestJSON.put("language", "English");
        MvcResult mvcResult = mockMvc
                .perform(post("/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON.toJSONString())
                ).andExpect(status().isBadRequest())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();

        String contentFromFile = getContentFromFile(SOURCE_DIRECTORY.concat("Improper_author_name_error.json"));

        assertEquals(contentFromFile, responseString);
    }

    @Test
    public void testAuthorNameShouldNotStartsWithSpace() throws Exception {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("book", "Sherlock Holmes");
        requestJSON.put("author", " Arthur Conan Doyle");
        requestJSON.put("category", "Fiction");
        requestJSON.put("language", "English");
        MvcResult mvcResult = mockMvc
                .perform(post("/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON.toJSONString())
                ).andExpect(status().isBadRequest())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();

        String contentFromFile = getContentFromFile(SOURCE_DIRECTORY.concat("Improper_author_name_error.json"));

        assertEquals(contentFromFile, responseString);
    }

    @Test
    public void testAuthorNameAllowsDotAndSpace() throws Exception {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("book", "Sherlock Holmes");
        requestJSON.put("author", "Mr.Arthur Conan Doyle");
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

    @Test
    public void testLanguageIsMandatory() throws Exception {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("book", "Sherlock Holmes");
        requestJSON.put("author", "Arthur Conan Doyle");
        requestJSON.put("category", "Fiction");
        MvcResult mvcResult = mockMvc
                .perform(post("/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON.toJSONString())
                ).andExpect(status().isBadRequest())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();

        String contentFromFile = getContentFromFile(SOURCE_DIRECTORY.concat("language_mandatory_error.json"));

        assertEquals(contentFromFile, responseString);
    }

    @Test
    public void testIncorrectLanguage() throws Exception {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("book", "Sherlock Holmes");
        requestJSON.put("author", "Arthur Conan Doyle");
        requestJSON.put("category", "Fiction");
        requestJSON.put("language", "Engl");
        MvcResult mvcResult = mockMvc
                .perform(post("/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON.toJSONString())
                ).andExpect(status().isBadRequest())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();

        String contentFromFile = getContentFromFile(SOURCE_DIRECTORY.concat("Incorrect_language_error.json"));

        assertEquals(contentFromFile, responseString);
    }
    @Test
    public void testIncorrectLanguage2() throws Exception {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("book", "Sherlock Holmes");
        requestJSON.put("author", "Arthur Conan Doyle");
        requestJSON.put("category", "Fiction");
        requestJSON.put("language", ".English");
        MvcResult mvcResult = mockMvc
                .perform(post("/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON.toJSONString())
                ).andExpect(status().isBadRequest())
                .andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();

        String contentFromFile = getContentFromFile(SOURCE_DIRECTORY.concat("Incorrect_language_error.json"));

        assertEquals(contentFromFile, responseString);
    }


}