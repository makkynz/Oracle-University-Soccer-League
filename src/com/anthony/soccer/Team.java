package com.anthony.soccer;

public class Team {
    private String name;
    private Player[] players;
    private int totalPoints;
    private int totalGoals;

    public Team(String name, Player... players) {
        this.name = name;
        this.players = players;
        this.totalPoints = 0;
    }

    public String getName() {
        return name;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void awardPoints(int points) {
        this.totalPoints += points;
    }

    public int getTotalGoals() {
        return totalGoals;
    }

    public void addGoals(int totalGoals) {
        this.totalGoals+= totalGoals;
    }
}
