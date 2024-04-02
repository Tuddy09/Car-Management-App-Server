package com.mpp.mppbackend;

import com.mpp.mppbackend.Controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
public class ControllerTests {
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
    }

    @Test
    public void getCarsTest() throws Exception {
        mockMvc.perform(get("/getCars"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

}
