package com.LongRecap.BugTracker.service;

import com.LongRecap.BugTracker.models.Bug;
import com.LongRecap.BugTracker.repository.BugRepository;

import java.util.List;
import java.util.Optional;

public interface BugService {

    Bug createBug(Bug bug);

    Optional<Bug> getBugById(Long id);

    List<Bug> getAllBugs();

    List<Bug> getBugByStatus(Bug.Status status);

    Bug updateBug(Long id, Bug bug);

    void deleteBug( Long id);



}
