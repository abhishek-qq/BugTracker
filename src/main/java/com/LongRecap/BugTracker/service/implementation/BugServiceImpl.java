package com.LongRecap.BugTracker.service.implementation;

import com.LongRecap.BugTracker.models.Bug;
import com.LongRecap.BugTracker.repository.BugRepository;
import com.LongRecap.BugTracker.service.BugService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BugServiceImpl implements BugService {

    private final BugRepository bugRepository;

    @Override
    public Bug createBug(Bug bug) {
        return bugRepository.save(bug);
    }

    @Override
    public Optional<Bug> getBugById(Long id) {
        return bugRepository.findById(id);
    }

    @Override
    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    @Override
    public List<Bug> getBugByStatus(Bug.Status status) {
        return bugRepository.findByStatus(status);
    }

    @Override
    public Bug updateBug(Long id, Bug bug) {
        Bug extistingBug = bugRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Bug not found in the system with id {} " + id ));

        extistingBug.setDescription(bug.getDescription());
        extistingBug.setTitle(bug.getTitle());
        extistingBug.setStatus(bug.getStatus());

        return bugRepository.save(extistingBug);
    }

    @Override
    public void deleteBug(Long id) {
        bugRepository.deleteById(id);

    }
}
