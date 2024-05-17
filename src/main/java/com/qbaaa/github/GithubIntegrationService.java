package com.qbaaa.github;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubIntegrationService {

    private final RepositoryGithubService repositoryService;
    private final BranchGithubService branchService;

    public List<RepositoryDto> getRepositories(String username) {
        return repositoryService.getRepositories(username);
    }

    public List<BranchDto> getBranches(String username, String repoName) {
        return branchService.getBranches(username, repoName);
    }
}
