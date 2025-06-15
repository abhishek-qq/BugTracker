package com.LongRecap.BugTracker;

import com.LongRecap.BugTracker.models.Bug;
import com.LongRecap.BugTracker.repository.BugRepository;
import com.LongRecap.BugTracker.service.implementation.BugServiceImpl;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BugServiceImplTest {

    @Mock
    private BugRepository bugRepository;

    @InjectMocks
    private BugServiceImpl bugService;

    private Bug sampleBug;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);

        sampleBug = Bug.builder()
                .id(1L)
                .title("Null Pointer Exception")
                .description("Occurs on clicking save")
                .status(Bug.Status.OPEN)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    /*
    let's write our first test case
     */

    @Test
    public void testCreateBug(){

        when(bugRepository.save(any(Bug.class))).thenReturn(sampleBug);
        // this is known as Mockito stubbing - so that actual DB call are ignored

        Bug created = bugService.createBug(sampleBug);

        assertNotNull(created);
        assertEquals("Null Pointer Exception",created.getTitle());

        verify(bugRepository,times(1)).save(sampleBug);
        // to verify that .save method is only called once

    }


}
