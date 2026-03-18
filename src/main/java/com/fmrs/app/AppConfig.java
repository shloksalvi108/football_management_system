package com.fmrs.app;

import com.fmrs.dao.JdbcPlayerRepository;
import com.fmrs.dao.PostgresConnectionFactory;
import com.fmrs.search.SearchEngineService;
import com.fmrs.search.SearchQueryParser;

public class AppConfig {

    // In real app, load from properties
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/football_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Shlok999$";

    public SearchUseCase searchUseCase() {
        PostgresConnectionFactory connectionFactory =
                new PostgresConnectionFactory(DB_URL, DB_USER, DB_PASSWORD);
        JdbcPlayerRepository playerRepository = new JdbcPlayerRepository(connectionFactory);
        SearchQueryParser parser = new SearchQueryParser();
        SearchEngineService searchEngineService = new SearchEngineService(playerRepository, parser);
        return new SearchUseCase(searchEngineService);
    }
}

