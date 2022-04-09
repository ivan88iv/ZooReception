package com.zuhlke.bg.camp.service.predicate;

import com.zuhlke.bg.camp.client.model.FoodType;
import com.zuhlke.bg.camp.client.model.ManagerAnimalDto;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Predicate;

@Service
public class OmnivorousPredicate implements Predicate<ManagerAnimalDto> {
    @Override
    public boolean test(ManagerAnimalDto managerAnimalDto) {
        if(managerAnimalDto == null) {
            return false;
        }

        var actualFoodTypesSet = Set.of(managerAnimalDto.getFoodTypes());
        var omnivorousFoodTypesSet = Set.of(FoodType.MEAT, FoodType.PLANT);
        return omnivorousFoodTypesSet.equals(actualFoodTypesSet);
    }
}
