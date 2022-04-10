package com.zuhlke.bg.camp.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.internal.Files;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WireMockTest(httpPort = 8092)
class GuideComponentTest {
    private static final String SUGGEST_WALK_PATH = "/guide/suggestedWalk/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void suggestWalk_searchForHerbivorous_returnsOnlyHerbivorous() throws Exception {
        String managerResponseContent = readFileContent("okAllAnimalsResponse.json");

        // TODO stub ZooManager

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

    private ResultActions getSuggestedWalk(String criteria) throws Exception {
        return mockMvc.perform(
                        MockMvcRequestBuilders.get(SUGGEST_WALK_PATH + criteria)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(MockMvcResultHandlers.print());
    }

    private String readFileContent(String fileName) throws IOException {
        final InputStream stream = GuideComponentTest.class.getResourceAsStream(fileName);
        return Files.read(stream, StandardCharsets.UTF_8);
    }

    private ResultMatcher body(Map<String,Object>... maps) throws JsonProcessingException {
        return MockMvcResultMatchers.content()
                .json(
                        mapper.writeValueAsString(List.of(maps))
                );
    }
}
