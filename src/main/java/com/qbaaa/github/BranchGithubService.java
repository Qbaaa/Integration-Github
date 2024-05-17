package com.qbaaa.github;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface BranchGithubService {

    @GetExchange(url="/repos/{owner}/{repo}/branches", accept = "application/vnd.github.v3+json")
    List<BranchDto> getBranches(@PathVariable String owner, @PathVariable String repo);
}
