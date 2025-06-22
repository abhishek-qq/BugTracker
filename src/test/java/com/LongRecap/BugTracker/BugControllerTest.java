package com.LongRecap.BugTracker;


import com.LongRecap.BugTracker.controller.BugController;
import com.LongRecap.BugTracker.models.Bug;
import com.LongRecap.BugTracker.service.BugService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BugController.class)
public class BugControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private Bug sampleBug;

    @Autowired
    private BugService bugService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public BugService bugService() {
            return Mockito.mock(BugService.class);
        }
    }



    @BeforeEach
    void setup() {
        sampleBug = new Bug();
        sampleBug.setId(1L);
        sampleBug.setTitle("Integration Test Bug");
        sampleBug.setDescription("Occurs on clicking save");
        sampleBug.setStatus(Bug.Status.OPEN);
        sampleBug.setCreatedAt(LocalDateTime.now());
        sampleBug.setUpdatedAt(LocalDateTime.now());
    }


    @Test
    public void testCreateBug() throws Exception {
        when(bugService.createBug(any(Bug.class))).thenReturn(sampleBug);

        mockMvc.perform(post("/api/bugs/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleBug)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Integration Test Bug")));
    }
}
