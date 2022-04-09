package com.zuhlke.bg.camp.service.predicate;

import com.zuhlke.bg.camp.client.model.FoodType;
import com.zuhlke.bg.camp.client.model.ManagerAnimalDto;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class CarnivorousPredicate implements Predicate<ManagerAnimalDto> {
    @Override
    public boolean test(ManagerAnimalDto managerAnimalDto) {
        return managerAnimalDto != null && managerAnimalDto.getFoodTypes().length == 1 &&
                managerAnimalDto.getFoodTypes()[0] == FoodType.MEAT;
    }
}
