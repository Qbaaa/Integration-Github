package com.qbaaa.github.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("github.api.client")
@Data
public class GithubApiClientConfigProperties {

    private String url;
}
