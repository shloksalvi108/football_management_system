package com.fmrs.dao;

import com.fmrs.common.DataAccessException;
import com.fmrs.domain.Player;
import com.fmrs.domain.PlayerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcPlayerRepository implements PlayerRepository {

    private final PostgresConnectionFactory connectionFactory;

    public JdbcPlayerRepository(PostgresConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Optional<Player> findByName(String name) {
        String sql = "SELECT id, name, country, club, total_goals FROM players WHERE LOWER(name) = LOWER(?)";
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToPlayer(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching player by name", e);
        }
    }

    @Override
    public List<Player> findTopScorers(int limit) {
        String sql = "SELECT id, name, country, club, total_goals " +
                "FROM players ORDER BY total_goals DESC LIMIT ?";
        List<Player> players = new ArrayList<>();
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    players.add(mapRowToPlayer(rs));
                }
            }
            return players;
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching top scorers", e);
        }
    }

    @Override
    public Optional<Player> findPlayerWithMaximumGoals() {
        String sql = "SELECT id, name, country, club, total_goals " +
                "FROM players ORDER BY total_goals DESC LIMIT 1";
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return Optional.of(mapRowToPlayer(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching player with maximum goals", e);
        }
    }

    private Player mapRowToPlayer(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String country = rs.getString("country");
        String club = rs.getString("club");
        int totalGoals = rs.getInt("total_goals");
        return new Player(id, name, country, club, totalGoals);
    }
}

