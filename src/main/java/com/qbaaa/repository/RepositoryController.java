package com.qbaaa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RepositoryController {

    private final RepositoryService repositoryService;

    @GetMapping("/users/{username}/repositories")
    public ResponseEntity<RepositoryDetailsResponse> getRepositories(@PathVariable String username, @RequestParam(required = false) Boolean isFork) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new RepositoryDetailsResponse(repositoryService.getRepositories(username, isFork)));
    }
}
