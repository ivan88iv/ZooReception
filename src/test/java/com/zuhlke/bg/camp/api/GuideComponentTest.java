package com.zuhlke.bg.camp.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.internal.Files;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WireMockTest(httpPort = 8092)
class GuideComponentTest {
    private static final String SUGGEST_WALK_PATH = "/guide/suggestedWalk/";

    private static final String GET_ALL_ANIMALS = "/animals";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void suggestedWalk_searchForCarnivorous_returnsOnlyCarnivorous() throws Exception {
        final String managerResponseContent = readFileContent("okAllAnimalsResponse.json");
        WireMock.stubFor(
                WireMock.get(GET_ALL_ANIMALS)
                        .withHeader(HttpHeaders.ACCEPT, WireMock.equalTo(MediaType.APPLICATION_JSON_VALUE))
                        .willReturn(WireMock.ok(managerResponseContent)
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        )
        );

        getSuggestedWalk("carnivorous")
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(body(Map.of(
                                "name", "Evgeni",
                                "species", "Tiger",
                                "age", 3,
                                "gender", "M",
                                "category", "CARNIVOROUS",
                                "cageNumber", 2
                        ))
                );
    }

    @Test
    void suggestWalk_searchForHerbivorous_returnsOnlyHerbivorous() throws Exception {
        final String managerResponseContent = readFileContent("okAllAnimalsResponse.json");
        WireMock.stubFor(
                WireMock.get(GET_ALL_ANIMALS)
                        .withHeader(HttpHeaders.ACCEPT, WireMock.equalTo(MediaType.APPLICATION_JSON_VALUE))
                        .willReturn(WireMock.ok(managerResponseContent)
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        )
        );

        getSuggestedWalk("herbivorous")
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(body(Map.of(
                                "name", "Eli",
                                "species", "Elephant",
                                "age", 13,
                                "gender", "F",
                                "category", "HERBIVOROUS",
                                "cageNumber", 14
                        ))
                );
    }

    @Test
    void suggestWalk_searchForOmnivorous_returnsOnlyOmnivorous() throws Exception {
        final String managerResponseContent = readFileContent("okAllAnimalsResponse.json");
        WireMock.stubFor(
                WireMock.get(GET_ALL_ANIMALS)
                        .withHeader(HttpHeaders.ACCEPT, WireMock.equalTo(MediaType.APPLICATION_JSON_VALUE))
                        .willReturn(WireMock.ok(managerResponseContent)
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        )
        );

        getSuggestedWalk("omnivorous")
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(body(Map.of(
                                "name", "Roro",
                                "species", "Dog",
                                "age", 1,
                                "gender", "M",
                                "category", "OMNIVOROUS",
                                "cageNumber", 3
                        ))
                );
    }

    @Test
    void suggestedWalk_problemInProvider_returns500() throws Exception {
        WireMock.stubFor(
                WireMock.get(GET_ALL_ANIMALS)
                        .withHeader(HttpHeaders.ACCEPT, WireMock.equalTo(MediaType.APPLICATION_JSON_VALUE))
                        .willReturn(WireMock.serverError())
        );

        getSuggestedWalk("carnivorous")
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    void suggestedWalk_invalidCriteria_returns400() throws Exception {
        final String managerResponseContent = readFileContent("okAllAnimalsResponse.json");
        WireMock.stubFor(
                WireMock.get(GET_ALL_ANIMALS)
                        .withHeader(HttpHeaders.ACCEPT, WireMock.equalTo(MediaType.APPLICATION_JSON_VALUE))
                        .willReturn(WireMock.ok(managerResponseContent)
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        )
        );

        getSuggestedWalk("123")
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void suggestedWalk_noAnimals_returns404() throws Exception {
        final String managerResponseContent = readFileContent("noAnimalsResponse.json");
        WireMock.stubFor(
                WireMock.get(GET_ALL_ANIMALS)
                        .withHeader(HttpHeaders.ACCEPT, WireMock.equalTo(MediaType.APPLICATION_JSON_VALUE))
                        .willReturn(WireMock.ok(managerResponseContent)
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        )
        );

        getSuggestedWalk("carnivorous")
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void suggestedWalk_noCriteria_returns200() throws Exception {
        final String managerResponseContent = readFileContent("singleAnimal.json");
        WireMock.stubFor(
                WireMock.get(GET_ALL_ANIMALS)
                        .withHeader(HttpHeaders.ACCEPT, WireMock.equalTo(MediaType.APPLICATION_JSON_VALUE))
                        .willReturn(WireMock.ok(managerResponseContent)
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        )
        );

        getSuggestedWalk("")
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(body(Map.of(
                                "name", "Eli",
                                "species", "Elephant",
                                "age", 13,
                                "gender", "F",
                                "category", "HERBIVOROUS",
                                "cageNumber", 14
                        ))
                );;
    }

    private ResultActions getSuggestedWalk(final String criteria) throws Exception {
        return mockMvc.perform(
                        MockMvcRequestBuilders.get(SUGGEST_WALK_PATH + criteria)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(MockMvcResultHandlers.print());
    }

    private String readFileContent(final String fileName) throws IOException {
        final InputStream stream = GuideComponentTest.class.getResourceAsStream(fileName);
        return Files.read(stream, Charset.forName("UTF-8"));
    }

    private ResultMatcher body(Map<String,Object> ...maps) throws JsonProcessingException {
        return MockMvcResultMatchers.content()
                .json(
                        mapper.writeValueAsString(List.of(maps))
                );
    }
}
