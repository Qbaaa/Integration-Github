package com.qbaaa.github;

public record BranchDto(
        String name,
        CommitDto commit
) {
}
