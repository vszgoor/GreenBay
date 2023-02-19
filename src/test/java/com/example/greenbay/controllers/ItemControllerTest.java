package com.example.greenbay.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.greenbay.models.User;
import com.example.greenbay.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"test"})
class ItemControllerTest {


  @Autowired
  UserRepository userRepository;

  @Autowired
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    userRepository.save(new User("testUser", "testPassword", 1000.0));
  }

  @Test
  @WithMockUser(username = "testUser", password = "testPassword")
  void addItemShouldWorkAsExpected() throws Exception {
    mockMvc.perform(post("/item")
            .content("{\n" +
                "   \"name\" : \"item1\",\n" +
                "   \"description\" : \"this is an item\",\n" +
                "   \"photoURL\" : \"https://picture1.com\",\n" +
                "   \"startingPrice\" : 100,\n" +
                "   \"purchasePrice\" : 1000 \n" +
                "}").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json("{\n" +
            "   \"id\": 1,\n" +
            "   \"name\" : \"item1\",\n" +
            "   \"description\" : \"this is an item\",\n" +
            "   \"photoURL\" : \"https://picture1.com\",\n" +
            "   \"startingPrice\" : 100,\n" +
            "   \"purchasePrice\" : 1000 \n" +
            "}"));
  }

  @Test
  @WithMockUser(username = "user1", password = "Pass123")
  void addItemShouldGiveErrorIfInputDataIsNotValid() throws Exception {
    mockMvc.perform(post("/item")
            .content("{\n" +
                "   \"name\" : \"\",\n" +
                "   \"description\" : \"\",\n" +
                "   \"photoURL\" : \"\",\n" +
                "   \"startingPrice\" : -1,\n" +
                "   \"purchasePrice\" : -10 \n" +
                "}").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().json("{\n" +
            "    \"photoURL\": \"must not be blank\",\n" +
            "    \"name\": \"must not be blank\",\n" +
            "    \"description\": \"must not be blank\",\n" +
            "    \"startingPrice\": \"must be greater than 0\",\n" +
            "    \"purchasePrice\": \"must be greater than 0\"\n" +
            "}"));
  }
}