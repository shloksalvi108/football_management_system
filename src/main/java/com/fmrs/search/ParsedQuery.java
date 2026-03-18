package com.fmrs.search;

public class ParsedQuery {
    private final QueryType type;
    private final String playerName;
    private final int limit;

    public ParsedQuery(QueryType type, String playerName, int limit) {
        this.type = type;
        this.playerName = playerName;
        this.limit = limit;
    }

    public QueryType getType() {
        return type;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getLimit() {
        return limit;
    }
}

