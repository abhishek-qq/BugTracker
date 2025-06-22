package com.LongRecap.BugTracker;

import com.LongRecap.BugTracker.models.Bug;
import com.LongRecap.BugTracker.repository.BugRepository;
import com.LongRecap.BugTracker.service.implementation.BugServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BugServiceImplTest {

    @Mock
    private BugRepository bugRepository;

    @InjectMocks
    private BugServiceImpl bugService;

    private Bug sampleBug;

    @BeforeEach
    void setup() {
        sampleBug = new Bug();
        sampleBug.setId(1L);
        sampleBug.setTitle("Null Pointer Exception");
        sampleBug.setDescription("Occurs on clicking save");
        sampleBug.setStatus(Bug.Status.OPEN);
        sampleBug.setCreatedAt(LocalDateTime.now());
        sampleBug.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    public void testCreateBug() {
        when(bugRepository.save(any(Bug.class))).thenReturn(sampleBug);
        Bug created = bugService.createBug(sampleBug);
        assertNotNull(created);
        assertEquals("Null Pointer Exception", created.getTitle());
        verify(bugRepository, times(1)).save(sampleBug);
    }

    @Test
    public void testGetBugById_Found(){

        when(bugRepository.findById(1L)).thenReturn(Optional.of(sampleBug));

        Optional<Bug> result = bugService.getBugById(1L);
        assertTrue(result.isPresent());
        assertEquals(sampleBug.getId(), result.get().getId());
    }

    @Test
    public void testGetBugById_notFound(){

        when(bugRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Bug> result = bugService.getBugById(2L);
        assertFalse(result.isPresent());
    }


    @Test
    public void testGetAllBugs(){

        List<Bug> bugs = List.of(sampleBug);
        when(bugRepository.findAll()).thenReturn(bugs);

        List<Bug> result = bugService.getAllBugs();
        assertEquals(1,result.size());
    }

    @Test
    public void testBugByStatus(){

        List<Bug> bugs = List.of(sampleBug);
        when(bugRepository.findByStatus(Bug.Status.OPEN)).thenReturn(bugs);

        List<Bug> result = bugService.getBugByStatus(Bug.Status.OPEN);
        assertEquals(1,result.size());
    }

    @Test
    public void testUpdateBug_Found(){
        Bug updatedBug = new Bug();
        updatedBug.setTitle("Updated Bug");
        updatedBug.setStatus(Bug.Status.IN_PROGRESS);
        updatedBug.setDescription("Updated Details");

        when(bugRepository.findById(1L)).thenReturn(Optional.of(sampleBug));
        when(bugRepository.save(any(Bug.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Bug result = bugService.updateBug(1L,updatedBug);
        assertEquals("Updated Bug",result.getTitle());
        assertEquals(Bug.Status.IN_PROGRESS, result.getStatus());
    }

    @Test
    public void testUpdateBugNot_Found(){

        when(bugRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,() -> bugService.updateBug(99L,sampleBug));
    }

    @Test
    public void testDeleteBug() {
        doNothing().when(bugRepository).deleteById(1L);

        bugService.deleteBug(1L);

        verify(bugRepository,times(1)).deleteById(1L);
    }




}
