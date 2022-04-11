package com.zuhlke.bg.camp.api;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.LambdaDsl;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.zuhlke.bg.camp.client.ZooManagerClient;
import com.zuhlke.bg.camp.client.exception.NonExistentAnimalException;
import com.zuhlke.bg.camp.client.model.AnimalDetails;
import com.zuhlke.bg.camp.client.model.AnimalFood;
import com.zuhlke.bg.camp.client.model.FoodType;
import com.zuhlke.bg.camp.client.model.PriceUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Map;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(pactVersion = PactSpecVersion.V3)
public class GetAnimalDetailsContractTest {

    private static final String CONSUMER_NAME = "DiviStaroselci";

    private static final String PROVIDER_NAME = "ZooManager";

    @Pact(consumer = CONSUMER_NAME, provider = PROVIDER_NAME)
    public RequestResponsePact pact4EmptyZoo(PactDslWithProvider pactDsl) {
        return pactDsl.given("Animal not found")
                .uponReceiving("A request for animal that does not exist")
                .path("/animals/Joro")
                .method("GET")
                .headers(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .willRespondWith()
                .status(404)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "pact4EmptyZoo")
    public void verifyNoAnimalsPact(MockServer server) {
        var zooManagerClient = new ZooManagerClient(server.getUrl(), new RestTemplateBuilder());

        Assertions.assertThrows(NonExistentAnimalException.class, () -> zooManagerClient.getAnimalDetails("Joro"));
    }

    @Pact(consumer = CONSUMER_NAME, provider = PROVIDER_NAME)
    public RequestResponsePact pact4NoFood(PactDslWithProvider pactDsl) {
        return pactDsl.given("Existing animal without suitable food", Map.of("animalName", "Pesho"))
                .uponReceiving("A request for an existing animal without food")
                .path("/animals/Pesho")
                .method("GET")
                .headers(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .willRespondWith()
                .headers(Map.of(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .status(200)
                .body(LambdaDsl.newJsonBody((object) -> {
                    object.stringValue("name", "Pesho");
                    object.stringType("animalType", "Chicken");
                    object.maxArrayLike("acceptableFood", 0, 0, (food) -> {});
                }).build())
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "pact4NoFood")
    public void verifyNoFoodPact(MockServer server) {
        var zooManagerClient = new ZooManagerClient(server.getUrl(), new RestTemplateBuilder());
        AnimalDetails details = zooManagerClient.getAnimalDetails("Pesho");

        Assertions.assertEquals("Pesho", details.getName());
        Assertions.assertEquals("Chicken", details.getAnimalType());

        Assertions.assertTrue(details.getAcceptableFood().isEmpty());
    }

    @Pact(consumer = CONSUMER_NAME, provider = PROVIDER_NAME)
    public RequestResponsePact pact4SomeFood(PactDslWithProvider pactDsl) {
        return pactDsl.given("Existing animal with one suitable food", Map.of("animalName", "Jimi"))
                .uponReceiving("A request for an existing animal with suitable food")
                .path("/animals/Jimi")
                .method("GET")
                .headers(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .willRespondWith()
                .headers(Map.of(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .status(200)
                .body(LambdaDsl.newJsonBody((object) -> {
                            object.stringValue("name", "Jimi");
                            object.stringType("animalType", "Kangaroo");
                            object.minArrayLike("acceptableFood", 1, 2, (food) -> {
                                food.stringType("name", "Chicken");
                                food.stringType("type", "MEAT");
                                food.decimalType("price", 1.33);
                                food.stringMatcher("priceUnit", "L|KG", "L");
                            });
                        }).build()
                )
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "pact4SomeFood")
    public void verifySomeFoodPact(MockServer server) {
        var zooManagerClient = new ZooManagerClient(server.getUrl(), new RestTemplateBuilder());
        AnimalDetails details = zooManagerClient.getAnimalDetails("Jimi");

        Assertions.assertEquals("Jimi", details.getName());
        Assertions.assertEquals("Kangaroo", details.getAnimalType());

        Assertions.assertEquals(2, details.getAcceptableFood().size());
        Assertions.assertTrue(details.getAcceptableFood().contains(new AnimalFood("Chicken",
                FoodType.MEAT, 1.33, PriceUnit.L)));
    }

}
