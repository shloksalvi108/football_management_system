package com.fmrs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresConnectionFactory {

    private final String url;
    private final String user;
    private final String password;

    public PostgresConnectionFactory(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        // Force-load the PostgreSQL JDBC driver so misconfigured classpaths fail fast with a clear message.
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "PostgreSQL JDBC driver not found. Add the PostgreSQL JDBC .jar to your -cp classpath. " +
                            "Expected driver class: org.postgresql.Driver",
                    e
            );
        }

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        return DriverManager.getConnection(url, props);
    }
}

