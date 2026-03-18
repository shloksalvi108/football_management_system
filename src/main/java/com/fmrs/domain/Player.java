package com.fmrs.domain;

public class Player {
    private int id;
    private String name;
    private String country;
    private String club;
    private int totalGoals;

    public Player(int id, String name, String country, String club, int totalGoals) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.club = club;
        this.totalGoals = totalGoals;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCountry() { return country; }
    public String getClub() { return club; }
    public int getTotalGoals() { return totalGoals; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCountry(String country) { this.country = country; }
    public void setClub(String club) { this.club = club; }
    public void setTotalGoals(int totalGoals) { this.totalGoals = totalGoals; }
}

