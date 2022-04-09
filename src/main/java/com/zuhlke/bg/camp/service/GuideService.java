package com.zuhlke.bg.camp.service;

import com.zuhlke.bg.camp.client.ZooManagerClient;
import com.zuhlke.bg.camp.client.model.ManagerAnimalDto;
import com.zuhlke.bg.camp.service.converter.AnimalConverter;
import com.zuhlke.bg.camp.service.model.VisitedAnimalDto;
import com.zuhlke.bg.camp.service.model.WalkCriteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GuideService {
    private static final Set<WalkCriteria> FILTERING_CRITERIA = Set.of(WalkCriteria.CARNIVOROUS,
            WalkCriteria.HERBIVOROUS, WalkCriteria.OMNIVOROUS);

    private static final Set<WalkCriteria> SORTING_CRITERIA = Set.of(WalkCriteria.NAME_ASC, WalkCriteria.NAME_DESC);

    private final ZooManagerClient zooManagerClient;

    private final AnimalConverter animalConverter;

    private final FilterGuideService filterGuideService;

    private final SortingGuideService sortingGuideService;

    GuideService(ZooManagerClient zoomanagerClient,
                 AnimalConverter animalConverter,
                 FilterGuideService filterGuideService,
                 SortingGuideService sortingGuideService) {

        this.zooManagerClient = zoomanagerClient;
        this.animalConverter = animalConverter;
        this.filterGuideService = filterGuideService;
        this.sortingGuideService = sortingGuideService;
    }

    public List<VisitedAnimalDto> getAnimals(final WalkCriteria criteria) {
        List<ManagerAnimalDto> managerAnimals = zooManagerClient.extractAllAnimals2();
        if (FILTERING_CRITERIA.contains(criteria)) {
            managerAnimals = filterGuideService.filterAnimals(managerAnimals, criteria);
        } else if (SORTING_CRITERIA.contains(criteria)) {
            managerAnimals = sortingGuideService.sortAnimals(managerAnimals, criteria);
        }

        return convertAnimals(managerAnimals);
    }

    private List<VisitedAnimalDto> convertAnimals(final List<ManagerAnimalDto> managerAnimals) {
        return managerAnimals.stream()
                .map(animalConverter)
                .collect(Collectors.toList());
    }
}
