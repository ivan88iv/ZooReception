package com.zuhlke.bg.camp.api;

import com.zuhlke.bg.camp.api.model.Amount;
import com.zuhlke.bg.camp.config.OpenApiTags;
import com.zuhlke.bg.camp.service.CharityService;
import com.zuhlke.bg.camp.service.model.AnimalDonationDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/charity", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = OpenApiTags.CHARITY_OPERATIONS_NAME, description = OpenApiTags.CHARITY_OPERATIONS_NAME)
public class CharityController {

    private final CharityService charityService;

    CharityController(CharityService charityService) {
        this.charityService = charityService;
    }

    @PostMapping(value = "/animals/{name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimalDonationDto> donateToAnimal(
            @PathVariable("name") String name,
            @RequestBody Amount donationAmount) {

        AnimalDonationDto donation = charityService.calculateFoodBoughtWithDonation(donationAmount.getMoney(), name);
        return ResponseEntity.ok(donation);
    }
}
