package com.fmrs.domain;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    Optional<Player> findByName(String name);

    List<Player> findTopScorers(int limit);

    Optional<Player> findPlayerWithMaximumGoals();
}

