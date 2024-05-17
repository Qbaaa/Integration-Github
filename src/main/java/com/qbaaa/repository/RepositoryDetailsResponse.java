package com.qbaaa.repository;

import java.util.List;

public record RepositoryDetailsResponse(
        List<RepositoryDetailsDto> repositoryDetailsList
) {
}
