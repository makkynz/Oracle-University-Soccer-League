package com.anthony.soccer;

import java.util.List;

public class Team {
    private String name;
    private Player[] players;

    public Team(String name, Player... players) {
        this.name = name;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public Player[] getPlayers() {
        return players;
    }
}
