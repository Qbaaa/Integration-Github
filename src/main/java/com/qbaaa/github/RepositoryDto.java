package com.qbaaa.github;

public record RepositoryDto(
        String name,
        boolean fork,
        UserDto owner
) {
}
