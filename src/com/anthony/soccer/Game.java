package com.anthony.soccer;

public class Game {
    private Team homeTeam;
    private Team awayTeam;
    private Goal[] goals;

    public Game(Team homeTeam, Team awayTeam, Goal... goals) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.goals = goals;
    }


    public Team getHomeTeam() {
        return homeTeam;
    }



    public Goal[] getGoals() {
        return goals;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }
}


