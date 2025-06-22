package com.LongRecap.BugTracker.controller;

import com.LongRecap.BugTracker.models.Bug;
import com.LongRecap.BugTracker.service.BugService;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bugs")
public class BugController {


    private final BugService bugService;

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @PostMapping("/create")
    public ResponseEntity<Bug> createBug(@RequestBody Bug bug){
        return ResponseEntity.ok(bugService.createBug(bug));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Bug> getBugById( @PathVariable  Long id){

        return bugService.getBugById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<List<Bug>> getAllBugs(){
        return ResponseEntity.ok(bugService.getAllBugs());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Bug>> getBugByStatus(@PathVariable Bug.Status status){
        return ResponseEntity.ok(bugService.getBugByStatus(status));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Bug> updateBug(@PathVariable Long id, @RequestBody Bug bug){
        return ResponseEntity.ok(bugService.updateBug(id,bug));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBugBYId(@PathVariable Long id){
        bugService.deleteBug(id);
        return ResponseEntity.noContent().build();
    }
}
