package com.zuhlke.bg.camp.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuhlke.bg.camp.client.model.ManagerAnimalDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class ZooManagerClient {
    private static final String GET_ALL_ANIMALS_PATH = "/animals";

    private final String baseUrl;

    private final RestTemplate restTemplate;

    ZooManagerClient(
            @Value("${zooManager.baseUrl}") String baseUrl,
            RestTemplateBuilder builder) {
        this.baseUrl = baseUrl;
        this.restTemplate = builder.build();
    }

    public List<ManagerAnimalDto> extractAllAnimals() {
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        var requestEntity = new HttpEntity<Void>(headers);
        var extractedAnimalsResponse = restTemplate.exchange(
                baseUrl + GET_ALL_ANIMALS_PATH, HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<List<ManagerAnimalDto>>() {});
        return extractedAnimalsResponse.getBody();
    }

    public List<ManagerAnimalDto> extractAllAnimals2() {
        final var mapper = new ObjectMapper();
        final InputStream animalsStream = ZooManagerClient.class.getResourceAsStream("animals.json");

        try {
            return mapper.readValue(animalsStream, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
