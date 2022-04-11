package com.zuhlke.bg.camp.config;


import io.swagger.v3.oas.models.tags.Tag;

public enum OpenApiTags {
    GUIDE_OPERATIONS(new Tag()
            .name(OpenApiTags.GUIDE_OPERATIONS_NAME)
            .description(OpenApiTags.GUIDE_OPERATIONS_DESCRIPTION)
    ),

    CHARITY_OPERATIONS(new Tag()
            .name(OpenApiTags.CHARITY_OPERATIONS_NAME)
            .description(OpenApiTags.CHARITY_OPERATIONS_DESCRIPTION)
    );

    public static final String GUIDE_OPERATIONS_NAME = "Guide Controller";

    public static final String GUIDE_OPERATIONS_DESCRIPTION = "Groups operations for guiding through the Zoo";

    public static final String CHARITY_OPERATIONS_NAME = "Charity Operations";

    public static final String CHARITY_OPERATIONS_DESCRIPTION = "Groups operations for making a donation to the Zoo";

    private final Tag tag;

    OpenApiTags(Tag tag) {
        this.tag = tag;
    }

    public Tag getTag() {
        return tag;
    }
}
