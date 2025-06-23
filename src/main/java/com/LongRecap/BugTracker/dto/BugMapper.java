package com.LongRecap.BugTracker.dto;

import com.LongRecap.BugTracker.models.Bug;

public class BugMapper {

    public static Bug toEntity(BugRequestDTO dto) {
        Bug bug = new Bug();
        bug.setTitle(dto.getTitle());
        bug.setDescription(dto.getDescription());
        bug.setStatus(dto.getStatus());
        return  bug;
    }

    public static BugResponseDTO toDTO(Bug bug) {
        BugResponseDTO responseDTO = new BugResponseDTO();
        responseDTO.setId(bug.getId());
        responseDTO.setTitle(bug.getTitle());
        responseDTO.setDescription(bug.getDescription());
        responseDTO.setStatus(bug.getStatus());
        responseDTO.setCreatedAt(bug.getCreatedAt());
        responseDTO.setUpdatedAt(bug.getUpdatedAt());
        return responseDTO;
    }
}
