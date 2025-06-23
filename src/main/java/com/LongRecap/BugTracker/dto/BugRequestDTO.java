package com.LongRecap.BugTracker.dto;

import com.LongRecap.BugTracker.models.Bug;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BugRequestDTO {

    @NotBlank(message = "Tittle is required")
    private String title;

    private String description;

    @NotNull(message = "You must provide status")
    private Bug.Status status;

}
