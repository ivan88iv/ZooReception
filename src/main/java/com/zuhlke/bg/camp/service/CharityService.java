package com.zuhlke.bg.camp.service;

import com.zuhlke.bg.camp.client.ZooManagerClient;
import com.zuhlke.bg.camp.client.model.AnimalDetails;
import com.zuhlke.bg.camp.client.model.AnimalFood;
import com.zuhlke.bg.camp.service.model.AnimalDonationDto;
import com.zuhlke.bg.camp.service.model.FoodToBuy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CharityService {

    private final ZooManagerClient zooManagerClient;

    CharityService(ZooManagerClient client) {
        this.zooManagerClient = client;
    }

    public AnimalDonationDto calculateFoodBoughtWithDonation(double donation, String animalName) {
        AnimalDetails details = zooManagerClient.getAnimalDetails(animalName);
        List<AnimalFood> acceptableFood = details.getAcceptableFood();

        if (details.getAcceptableFood().isEmpty()) {
            var unsuccessfulDonationMsg = String.format(
                    "We are sorry. Currently it is not possible to donate to %s(%s)",
                    animalName, details.getAnimalType());
            return new AnimalDonationDto(unsuccessfulDonationMsg, null);
        }

        Set<FoodToBuy> weCanBuy = acceptableFood.stream()
                .map((food) -> convertToFoodToBuy(food, donation))
                .collect(Collectors.toSet());
        var successfulDonationMsg = String.format("Thank you for buying food for %s(%s).", animalName,
                details.getAnimalType());
        return new AnimalDonationDto(successfulDonationMsg, weCanBuy);
    }

    private FoodToBuy convertToFoodToBuy(AnimalFood animalFood, double donation) {
        double quantity = donation / animalFood.getPricePerKg();
        double roundedQuantity = roundQuantity(quantity);
        return new FoodToBuy(animalFood.getName(), animalFood.getType(), roundedQuantity, "kilos");
    }

    private double roundQuantity(double quantity) {
        var decimal = new BigDecimal(quantity);
        decimal = decimal.setScale(2, RoundingMode.HALF_UP);
        return decimal.doubleValue();
    }
}
