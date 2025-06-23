package com.LongRecap.BugTracker.dto;

import com.LongRecap.BugTracker.models.Bug;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BugResponseDTO {

    private Long id;
    private String title;
    private String description;
    private Bug.Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
