package com.zuhlke.bg.camp.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuhlke.bg.camp.client.exception.NonExistentAnimalException;
import com.zuhlke.bg.camp.client.model.AnimalDetails;
import com.zuhlke.bg.camp.client.model.ManagerAnimalDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class ZooManagerClient {
    private static final String GET_ALL_ANIMALS_PATH = "/animals";

    private static final String GET_ANIMAL_DETAILS_PATH = "/animals/%s";

    private final String baseUrl;

    private final RestTemplate restTemplate;

    public ZooManagerClient(
            @Value("${zooManager.baseUrl}") String baseUrl,
            RestTemplateBuilder builder) {
        this.baseUrl = baseUrl;
        this.restTemplate = builder.build();
    }

    public List<ManagerAnimalDto> extractAllAnimals() {
        var requestEntity = prepareGetEntity();
        var extractedAnimalsResponse = restTemplate.exchange(
                baseUrl + GET_ALL_ANIMALS_PATH, HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<List<ManagerAnimalDto>>() {});
        return extractedAnimalsResponse.getBody();
    }

    public AnimalDetails getAnimalDetails(String name) {
        var path = String.format(GET_ANIMAL_DETAILS_PATH, name);
        var requestEntity = prepareGetEntity();
        try {
            var extractedDetailsResponse = restTemplate.exchange(baseUrl + path, HttpMethod.GET,
                    requestEntity, AnimalDetails.class);

            return extractedDetailsResponse.getBody();
        } catch(HttpClientErrorException.NotFound nfe) {
            throw new NonExistentAnimalException(name, nfe);
        }
    }

    public AnimalDetails getAnimalDetails2(String names) {
        final var mapper = new ObjectMapper();
        final InputStream animalsStream = ZooManagerClient.class.getResourceAsStream(
                "/animal.json");

        try {
            return mapper.readValue(animalsStream, AnimalDetails.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpEntity<Void> prepareGetEntity() {
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}
