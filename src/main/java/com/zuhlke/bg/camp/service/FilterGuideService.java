package com.zuhlke.bg.camp.service;

import com.zuhlke.bg.camp.client.model.ManagerAnimalDto;
import com.zuhlke.bg.camp.service.model.WalkCriteria;
import com.zuhlke.bg.camp.service.predicate.CarnivorousPredicate;
import com.zuhlke.bg.camp.service.predicate.HerbivorousPredicate;
import com.zuhlke.bg.camp.service.predicate.OmnivorousPredicate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
class FilterGuideService {
    private final CarnivorousPredicate carnivorousPredicate;

    private final OmnivorousPredicate omnivorousPredicate;

    private final HerbivorousPredicate herbivorousPredicate;

    FilterGuideService(CarnivorousPredicate carnivorousPredicate,
                 OmnivorousPredicate omnivorousPredicate,
                 HerbivorousPredicate herbivorousPredicate) {

        this.carnivorousPredicate = carnivorousPredicate;
        this.omnivorousPredicate = omnivorousPredicate;
        this.herbivorousPredicate = herbivorousPredicate;
    }

    public List<ManagerAnimalDto> filterAnimals(final List<ManagerAnimalDto> animalsToFilter,
                                                final WalkCriteria criteria) {
        if(animalsToFilter == null) {
            return null;
        }

        Predicate<ManagerAnimalDto> filteringPredicate = carnivorousPredicate;

        if (WalkCriteria.HERBIVOROUS == criteria) {
            filteringPredicate = herbivorousPredicate;
        } else if (WalkCriteria.OMNIVOROUS == criteria) {
            filteringPredicate = omnivorousPredicate;
        }

        return animalsToFilter.stream()
                .filter(filteringPredicate)
                .collect(Collectors.toList());
    }
}
