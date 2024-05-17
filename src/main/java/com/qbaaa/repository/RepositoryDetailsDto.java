package com.qbaaa.repository;

import com.qbaaa.github.BranchDto;
import com.qbaaa.github.RepositoryDto;

import java.util.List;

public record RepositoryDetailsDto(
        RepositoryDto repository,
        List<BranchDto> branches
) {
}
