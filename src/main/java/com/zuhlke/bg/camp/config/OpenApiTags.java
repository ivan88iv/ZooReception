package com.zuhlke.bg.camp.config;


import io.swagger.v3.oas.models.tags.Tag;

public enum OpenApiTags {
    GUIDE_OPERATIONS(new Tag()
            .name(OpenApiTags.GUIDE_OPERATIONS_NAME)
            .description(OpenApiTags.GUIDE_OPERATIONS_DESCRIPTION)
    );

    public static final String GUIDE_OPERATIONS_NAME = "Guide Controller";

    public static final String GUIDE_OPERATIONS_DESCRIPTION = "Groups operations for guiding through the Zoo";

    private final Tag tag;

    OpenApiTags(final Tag tag) {
        this.tag = tag;
    }

    public Tag getTag() {
        return tag;
    }
}
