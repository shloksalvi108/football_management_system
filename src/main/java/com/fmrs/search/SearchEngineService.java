package com.fmrs.search;

import com.fmrs.domain.Player;
import com.fmrs.domain.PlayerRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SearchEngineService {

    private final PlayerRepository playerRepository;
    private final SearchQueryParser parser;

    public SearchEngineService(PlayerRepository playerRepository, SearchQueryParser parser) {
        this.playerRepository = playerRepository;
        this.parser = parser;
    }

    public SearchResult search(String rawQuery) {
        ParsedQuery parsed = parser.parse(rawQuery);

        switch (parsed.getType()) {
            case PLAYER_WITH_MAX_GOALS:
                Optional<Player> maxPlayer = playerRepository.findPlayerWithMaximumGoals();
                return maxPlayer
                        .map(player -> new SearchResult(Collections.singletonList(player),
                                "Player with maximum goals"))
                        .orElseGet(() -> new SearchResult(Collections.emptyList(),
                                "No player found."));
            case TOP_SCORERS:
                List<Player> top = playerRepository.findTopScorers(parsed.getLimit());
                return new SearchResult(top, "Top " + parsed.getLimit() + " scorers");
            case PLAYER_BY_NAME:
                Optional<Player> byName = playerRepository.findByName(parsed.getPlayerName());
                return byName
                        .map(player -> new SearchResult(Collections.singletonList(player),
                                "Search result for player: " + parsed.getPlayerName()))
                        .orElseGet(() -> new SearchResult(Collections.emptyList(),
                                "Player not found: " + parsed.getPlayerName()));
            case UNKNOWN:
            default:
                return new SearchResult(Collections.emptyList(),
                        "Could not understand query.");
        }
    }
}

