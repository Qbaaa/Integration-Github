package com.qbaaa.github.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbaaa.github.BranchGithubService;
import com.qbaaa.github.RepositoryGithubService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GithubApiClientConfig {

    private final GithubApiClientConfigProperties githubApiClientConfigProperties;

    @Bean
    RestClient restClient(ObjectMapper objectMapper) {
        return RestClient.builder()
                .baseUrl(githubApiClientConfigProperties.getUrl())
                .build();
    }

    @SneakyThrows
    @Bean
    RepositoryGithubService repositoryGithubService(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builderFor(adapter)
                        .build();
        return httpServiceProxyFactory.createClient(RepositoryGithubService.class);
    }

    @SneakyThrows
    @Bean
    BranchGithubService branchGithubService(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builderFor(adapter)
                        .build();
        return httpServiceProxyFactory.createClient(BranchGithubService.class);
    }

}
