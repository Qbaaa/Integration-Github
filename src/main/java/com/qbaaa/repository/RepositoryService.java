package com.qbaaa.repository;

import com.qbaaa.github.BranchGithubService;
import com.qbaaa.github.GithubIntegrationService;
import com.qbaaa.github.RepositoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RepositoryService {

    private final GithubIntegrationService githubIntegrationService;
    private final BranchGithubService branchService;

    public List<RepositoryDetailsDto> getRepositories(String username, Boolean isFork) {
        List<RepositoryDto> repositories = githubIntegrationService.getRepositories(username);
        List<RepositoryDto> filteredRepositories = filterRepositories(repositories, isFork);

        List<RepositoryDetailsDto> repositoryDetailsList = new LinkedList<>();
        filteredRepositories.forEach(
                repo -> {
                    var branches = githubIntegrationService.getBranches(repo.owner().login(), repo.name());
                    repositoryDetailsList.add(new RepositoryDetailsDto(repo, branches));
                }
        );

        return repositoryDetailsList;
    }

    private List<RepositoryDto> filterRepositories(List<RepositoryDto> repositories, Boolean isFork) {
        if (isFork == null) {
            return repositories;
        } else if (isFork) {
            return repositories
                    .stream()
                    .filter(repo -> repo.fork() == Boolean.TRUE)
                    .toList();
        } else {
            return  repositories
                    .stream()
                    .filter(repo -> repo.fork() == Boolean.FALSE)
                    .toList();
        }
    }
}
