package com.LongRecap.BugTracker.repository;

import com.LongRecap.BugTracker.models.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugRepository extends JpaRepository<Bug,Long> {

    List<Bug> findByStatus(Bug.Status status);

}
