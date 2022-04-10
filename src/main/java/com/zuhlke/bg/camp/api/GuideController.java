package com.zuhlke.bg.camp.api;

import com.zuhlke.bg.camp.config.OpenApiTags;
import com.zuhlke.bg.camp.service.GuideService;
import com.zuhlke.bg.camp.service.model.VisitedAnimalDto;
import com.zuhlke.bg.camp.service.model.WalkCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = OpenApiTags.GUIDE_OPERATIONS_NAME, description = OpenApiTags.GUIDE_OPERATIONS_DESCRIPTION)
public class GuideController {

    private final GuideService guideService;

    GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    @Operation(summary="Returns a suggested walk given a specific criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The suggested walk given the specified criteria. " +
                    "If no criteria is given all animals should be returned.",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = VisitedAnimalDto.class)))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid criteria provided", content = @Content),
            @ApiResponse(responseCode = "404", description = "No animals to visit", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/suggestedWalk/{criteria}")
    public ResponseEntity<List<VisitedAnimalDto>> suggestWalk(
            @Parameter(description = "The given criteria to find a walk. Can be carnivorous, herbivorous and omnivorous")
            @PathVariable(value = "criteria", required = false) String rawCriteria) {

        WalkCriteria criteria = WalkCriteria.fromValue(rawCriteria);
        var walk = this.guideService.getAnimals(criteria);

        if(walk.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(walk);
    }
}
