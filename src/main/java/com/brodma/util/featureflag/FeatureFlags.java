package com.brodma.util.featureflag;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum FeatureFlags implements Feature {

    @Label("HibSearchIndexBuild")
    HIBERNATE_SEARCH_INDEX_BUILD,

    @Label("AuthorScenarios")
    AUTHOR_SCENARIOS,

    @Label("SearchScenarios")
    SEARCH_SCENARIOS,

    @Label("ReviewScenarios")
    REVIEW_SCENARIOS;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
