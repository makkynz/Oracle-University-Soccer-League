package com.anthony.soccer;

import java.util.List;

public class Team {
    private String name;
    private Player[] players;
    private int points;

    public Team(String name, Player... players) {
        this.name = name;
        this.players = players;
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getPoints() {
        return points;
    }

    public void awardPoints(int points) {
        this.points+= points;
    }
}
