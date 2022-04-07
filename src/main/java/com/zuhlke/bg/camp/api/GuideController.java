package com.zuhlke.bg.camp.api;

import com.zuhlke.bg.camp.api.model.Animal;
import com.zuhlke.bg.camp.api.model.AnimalType;
import com.zuhlke.bg.camp.api.model.Gender;
import com.zuhlke.bg.camp.api.model.WalkCriteria;
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

    @GetMapping("/suggestedWalk/{criteria}")
    public ResponseEntity<List<Animal>> suggestWalk(@PathVariable("criteria") String rawCriteria) {
        // HIX -> 400 instead of 500
        WalkCriteria criteria = WalkCriteria.fromValue(rawCriteria);

        var walk = List.of(
                new Animal("Evgeni", AnimalType.TIGER, 10, Gender.M),
                new Animal("Roro", AnimalType.DOG, 10, Gender.M),
                new Animal("Piggy", AnimalType.PIG, 10, Gender.F)
        );
        return ResponseEntity.ok(walk);
    }
}
