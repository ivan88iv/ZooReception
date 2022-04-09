package com.zuhlke.bg.camp.service;

import com.zuhlke.bg.camp.client.model.ManagerAnimalDto;
import com.zuhlke.bg.camp.service.model.WalkCriteria;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SortingGuideService {
    private static final AnimalNameAscComparator NAME_ASC_COMPARATOR = new AnimalNameAscComparator();

    public List<ManagerAnimalDto> sortAnimals(
            final List<ManagerAnimalDto> animalsToSort,
            final WalkCriteria criteria) {

        List<ManagerAnimalDto> sortedAnimals = null;
        if(WalkCriteria.NAME_ASC == criteria) {
            sortedAnimals = sortByNameAscending(animalsToSort);
        } else if(WalkCriteria.NAME_DESC == criteria) {
            sortedAnimals = sortByNameDescending(animalsToSort);
        }

        return sortedAnimals;
    }

    private List<ManagerAnimalDto> sortByNameAscending(final List<ManagerAnimalDto> animalsToSort) {
        animalsToSort.sort(NAME_ASC_COMPARATOR);
        return animalsToSort;
    }

    private List<ManagerAnimalDto> sortByNameDescending(final List<ManagerAnimalDto> animalsToSort) {
        animalsToSort.sort((animal1, animal2) -> -1 * NAME_ASC_COMPARATOR.compare(animal1, animal2));
        return animalsToSort;
    }

    private static class AnimalNameAscComparator implements Comparator<ManagerAnimalDto> {
        @Override
        public int compare(final ManagerAnimalDto animal1, final ManagerAnimalDto animal2) {
            if(animal1 == null && animal2 == null) {
                return 0;
            }

            if(animal1 == null) {
                return -1;
            }

            if(animal2 == null) {
                return 1;
            }

            return CharSequence.compare(animal1.getName(), animal2.getName());
        }
    }
}
