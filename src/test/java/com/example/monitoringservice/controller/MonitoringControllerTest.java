package com.example.monitoringservice.controller;

import com.example.monitoringservice.model.MessageData;
import com.example.monitoringservice.repository.MonitoringRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MonitoringControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MonitoringRepository monitoringRepository;

    @BeforeEach
    void setUp() {
        MessageData messageData = new MessageData();
        messageData.setClassName("TestClass");
        messageData.setId(1L);
        messageData.setUserEmail("user@example.com");
        messageData.setMethodName("testMethod");
        monitoringRepository.save(messageData);
    }


    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/v1/logs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andReturn();
    }

    @Test
    public void testFindAllByClassName() throws Exception {
        String className = "TestClass";

        mockMvc.perform(get("/api/v1/logs").param("className", className))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].className", is(className)))
                .andReturn();
    }

    @Test
    public void testFindAllByMethodName() throws Exception {
        String methodName = "testMethod";

        mockMvc.perform(get("/api/v1/logs").param("methodName", methodName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].methodName", is(methodName)))
                .andReturn();
    }

    @Test
    public void testFindAllByUserEmail() throws Exception {
        String userEmail = "user@example.com";

        mockMvc.perform(get("/api/v1/logs").param("userEmail", userEmail))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].userEmail", is(userEmail)))
                .andReturn();
    }
}