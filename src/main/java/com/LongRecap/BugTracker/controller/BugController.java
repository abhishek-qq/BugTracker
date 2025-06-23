package com.LongRecap.BugTracker.controller;

import com.LongRecap.BugTracker.dto.BugMapper;
import com.LongRecap.BugTracker.dto.BugRequestDTO;
import com.LongRecap.BugTracker.dto.BugResponseDTO;
import com.LongRecap.BugTracker.models.Bug;
import com.LongRecap.BugTracker.service.BugService;
import jakarta.validation.Valid;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bugs")
public class BugController {


    private final BugService bugService;

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @PostMapping("/create")
    public ResponseEntity<BugResponseDTO> createBug(@Valid @RequestBody BugRequestDTO bugRequestDTO){
        Bug created = bugService.createBug(BugMapper.toEntity(bugRequestDTO));
        return ResponseEntity.ok(BugMapper.toDTO(created));

    }

    @GetMapping("/{id}")
    public ResponseEntity<BugResponseDTO> getBugById( @PathVariable  Long id){

        return bugService.getBugById(id)
                .map(BugMapper :: toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<List<BugResponseDTO>> getAllBugs(){

        List<BugResponseDTO> list = bugService.getAllBugs().stream()
                .map(BugMapper :: toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<BugResponseDTO>> getBugByStatus(@PathVariable Bug.Status status){
        List<BugResponseDTO> list = bugService.getBugByStatus(status).stream()
                .map(BugMapper :: toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BugResponseDTO> updateBug(@PathVariable Long id,
                                         @RequestBody BugRequestDTO bugRequestDTO){

        Bug updatedBug = bugService.updateBug(id,BugMapper.toEntity(bugRequestDTO));

        return ResponseEntity.ok(BugMapper.toDTO(updatedBug));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBugBYId(@PathVariable Long id){
        bugService.deleteBug(id);
        return ResponseEntity.noContent().build();
    }
}
