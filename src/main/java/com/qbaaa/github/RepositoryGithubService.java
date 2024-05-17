package com.qbaaa.github;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface RepositoryGithubService {

    @GetExchange(url="/users/{username}/repos", accept = "application/vnd.github.v3+json")
    List<RepositoryDto> getRepositories(@PathVariable String username);
}
