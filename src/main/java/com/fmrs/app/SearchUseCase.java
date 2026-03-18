package com.fmrs.app;

import com.fmrs.search.SearchEngineService;
import com.fmrs.search.SearchResult;

public class SearchUseCase {

    private final SearchEngineService searchEngineService;

    public SearchUseCase(SearchEngineService searchEngineService) {
        this.searchEngineService = searchEngineService;
    }

    public SearchResult execute(String query) {
        return searchEngineService.search(query);
    }
}

