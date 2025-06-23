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
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    void testGetBugById_Found() throws Exception {
        when(bugService.getBugById(1L)).thenReturn(Optional.of(sampleBug));

        mockMvc.perform(get("/api/bugs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void testGetBugById_NotFound() throws Exception {
        when(bugService.getBugById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/bugs/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllBugs() throws Exception {
        when(bugService.getAllBugs()).thenReturn(List.of(sampleBug));

        mockMvc.perform(get("/api/bugs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)));
    }

    @Test
    void testGetBugsByStatus() throws Exception {
        when(bugService.getBugByStatus(Bug.Status.OPEN)).thenReturn(List.of(sampleBug));

        mockMvc.perform(get("/api/bugs/status/OPEN"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status", is("OPEN")));
    }


    @Test
    void testUpdateBug() throws Exception {
        Bug updatedBug = new Bug();
        updatedBug.setTitle("Updated Bug");
        updatedBug.setDescription("Update");
        updatedBug.setStatus(Bug.Status.IN_PROGRESS);

        when(bugService.updateBug(eq(1L), any(Bug.class))).thenReturn(updatedBug);

        mockMvc.perform(put("/api/bugs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBug)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Updated Bug")))
                .andExpect(jsonPath("$.status", is("IN_PROGRESS")));
    }

    @Test
    void testDeleteBug() throws Exception {
        doNothing().when(bugService).deleteBug(1L);

        mockMvc.perform(delete("/api/bugs/1"))
                .andExpect(status().isNoContent());
    }
}
