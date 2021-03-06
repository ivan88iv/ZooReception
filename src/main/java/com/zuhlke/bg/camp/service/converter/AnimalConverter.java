package com.zuhlke.bg.camp.service.converter;

import com.zuhlke.bg.camp.client.model.ManagerAnimalDto;
import com.zuhlke.bg.camp.service.model.AnimalCategory;
import com.zuhlke.bg.camp.service.model.VisitedAnimalDto;
import com.zuhlke.bg.camp.service.predicate.CarnivorousPredicate;
import com.zuhlke.bg.camp.service.predicate.HerbivorousPredicate;
import com.zuhlke.bg.camp.service.predicate.OmnivorousPredicate;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AnimalConverter implements Function<ManagerAnimalDto, VisitedAnimalDto> {

    private final CarnivorousPredicate carnivorousPredicate;

    private final OmnivorousPredicate omnivorousPredicate;

    private final HerbivorousPredicate herbivorousPredicate;

    AnimalConverter(CarnivorousPredicate carnivorousPredicate,
                   OmnivorousPredicate omnivorousPredicate,
                   HerbivorousPredicate herbivorousPredicate) {
        this.carnivorousPredicate = carnivorousPredicate;
        this.omnivorousPredicate = omnivorousPredicate;
        this.herbivorousPredicate = herbivorousPredicate;
    }

    @Override
    public VisitedAnimalDto apply(ManagerAnimalDto managerAnimalDto) {
        if (managerAnimalDto == null) {
            return null;
        }

        AnimalCategory category = getCategory(managerAnimalDto);

        return new VisitedAnimalDto.Builder()
                .withName(managerAnimalDto.getName())
                .withSpecies(managerAnimalDto.getType())
                .withCategory(category)
                .withAge(managerAnimalDto.getAge())
                .withGender(managerAnimalDto.getGender())
                .withCageNumber(managerAnimalDto.getCageNumber())
                .build();
    }

    private AnimalCategory getCategory(ManagerAnimalDto managerAnimal) {
        AnimalCategory category = null;

        if (herbivorousPredicate.test(managerAnimal)) {
            category = AnimalCategory.HERBIVOROUS;
        } else if(omnivorousPredicate.test(managerAnimal)) {
            category = AnimalCategory.OMNIVOROUS;
        } else if(carnivorousPredicate.test(managerAnimal)) {
            category = AnimalCategory.CARNIVOROUS;
        }

        return category;
    }
}
