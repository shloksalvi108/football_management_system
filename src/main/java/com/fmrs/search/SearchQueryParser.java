package com.fmrs.search;

public class SearchQueryParser {

    public ParsedQuery parse(String rawQuery) {
        if (rawQuery == null || rawQuery.trim().isEmpty()) {
            return new ParsedQuery(QueryType.UNKNOWN, null, 0);
        }

        String q = rawQuery.trim().toLowerCase();

        if (q.contains("player with maximum goals")) {
            return new ParsedQuery(QueryType.PLAYER_WITH_MAX_GOALS, null, 0);
        }

        if (q.startsWith("top ") && q.contains(" scorers")) {
            try {
                String numberPart = q.substring(4, q.indexOf(" scorers")).trim();
                int limit = Integer.parseInt(numberPart);
                return new ParsedQuery(QueryType.TOP_SCORERS, null, limit);
            } catch (NumberFormatException e) {
                return new ParsedQuery(QueryType.UNKNOWN, null, 0);
            }
        }

        // Default: treat as player name search (Messi, Ronaldo, etc.)
        return new ParsedQuery(QueryType.PLAYER_BY_NAME, rawQuery.trim(), 0);
    }
}

