package com.fmrs.search;

import com.fmrs.domain.Player;

import java.util.List;

public class SearchResult {
    private final List<Player> players;
    private final String message;

    public SearchResult(List<Player> players, String message) {
        this.players = players;
        this.message = message;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getMessage() {
        return message;
    }
}

