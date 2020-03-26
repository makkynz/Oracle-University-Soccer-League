package com.anthony.soccer;

public class Goal {
    private Team team;
    private Player player;
    private double time;

    public Goal(Team team, Player player, double time) {
        this.team = team;
        this.player = player;
        this.time = time;
    }



    public Player getPlayer() {
        return player;
    }

    public double getTime() {
        return time;
    }

    public Team getTeam() {
        return team;
    }
}
