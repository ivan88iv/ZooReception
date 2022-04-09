package com.zuhlke.bg.camp.api;

import com.zuhlke.bg.camp.service.GuideService;
import com.zuhlke.bg.camp.service.model.VisitedAnimalDto;
import com.zuhlke.bg.camp.service.model.WalkCriteria;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/guide",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class GuideController {

    private final GuideService guideService;

    GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    @GetMapping("/suggestedWalk/{criteria}")
    public ResponseEntity<List<VisitedAnimalDto>> suggestWalk(@PathVariable("criteria") final String rawCriteria) {
        // HIX -> 400 instead of 500
        final WalkCriteria criteria = WalkCriteria.fromValue(rawCriteria);
        final var walk = this.guideService.getAnimals(criteria);
        return ResponseEntity.ok(walk);
    }
}
