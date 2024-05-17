package com.qbaaa;

import com.qbaaa.exception.ExceptionResponse;
import net.bytebuddy.asm.MemberSubstitution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RepositoryControllerTestsIT extends IntegrationTests {

    static final String API_GET_REPOSITORIES_DETAILS = "/users/{username}/repositories";

    @Test
    void Should_GetExceptionResponseOf404When_GetRepositories() throws Exception {
        String username = "test!34";

        mockMvc.perform(MockMvcRequestBuilders.get(API_GET_REPOSITORIES_DETAILS, username)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(result -> {
                    Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
                    final ExceptionResponse response =
                            objectMapper.readValue(result.getResponse().getContentAsString(), ExceptionResponse.class);
                    Assertions.assertNotNull(response);
                });
    }

    private static Stream<Arguments> requestInputGetRepositories() {
        return Stream.of(
                Arguments.of("Qbaaa", Boolean.FALSE),
                Arguments.of("coi-gov-pl", Boolean.FALSE)
        );
    }

    @ParameterizedTest(name = "#{index} - Run test with username {0}, isFork {1}")
    @MethodSource("requestInputGetRepositories")
    void Should_GetRepositoriesDetails(String username, Boolean isFork) throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(API_GET_REPOSITORIES_DETAILS, username)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .param("isFork", isFork.toString()))
                .andExpect(status().isOk());
    }
}
