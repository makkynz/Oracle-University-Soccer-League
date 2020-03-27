package com.anthony.soccer;

import com.kenny.utilities.GameUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Game {
    private Team homeTeam;
    private Team awayTeam;
    private Consumer<String> printer;
    private List<Goal> goals;


    public Game(Team homeTeam, Team awayTeam, Consumer<String> printer) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.printer = printer;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public List<Goal> getGoals() {
        return goals;
    }
    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void playGame(){
        playGame(6);
    }
    public void playGame(int maxNumOfGoals){
        printer.accept("\n--- Game: " + getAwayTeam().getName() + " vs " + getHomeTeam().getName() +"---");
        int randNumOfGoals = (int)(Math.random()*((maxNumOfGoals)+1));
        GameUtils.addGameGoals(this, randNumOfGoals);
        awardPoints();
        homeTeam.addGoals(getTeamGoals(homeTeam));
        awayTeam.addGoals(getTeamGoals(awayTeam));
    }

    public void awardPoints() {
        Team winner = getWinner();

        if(winner!=null){
            winner.awardPoints(League.POINTS_FOR_WIN);
        }else{ //draw
            homeTeam.awardPoints(League.POINTS_FOR_DRAW);
            awayTeam.awardPoints(League.POINTS_FOR_DRAW);
        }
    }

    public Team getWinner() {
        int homeTeamGoals = getTeamGoals(homeTeam);
        int awayTeamGoals = getTeamGoals(awayTeam);

        Team winner = null;

        if(homeTeamGoals > awayTeamGoals){
            winner = homeTeam;
        }else if(homeTeamGoals < awayTeamGoals) {
            winner = awayTeam;
        }
        return winner;
    }

    public void printGameResult(){
        printResultSummary();
        printTeams();
        printGoals();
    }

    public void printResultSummary() {
        Team winner = getWinner();

        if(winner!=null){
            printer.accept("Winner: "+ winner.getName());
        }else{
            printer.accept("Draw:");
        }

        printer.accept("Final Score: "+ getTeamGoals(homeTeam)+ " - " +  getTeamGoals(awayTeam));
    }

    private int getTeamGoals(Team team){
        return goals.stream().filter(g->g.getTeam().equals(team)).collect(Collectors.toList()).size();
    }

    private void printGoals() {
        printer.accept("\nGoals");

        for (Goal goal : getGoals()) {
            printer.accept(String.format("Goal scored after %s mins by %s of the %s",
                    goal.getTime(),
                    goal.getPlayer().getName(),
                    goal.getTeam().getName()));
        }
    }

    private void printTeams() {
        printer.accept("\n"+getHomeTeam().getName() );

        for (Player player : getHomeTeam().getPlayers()) {
            printer.accept(player.getName() );
        }

        printer.accept("\n"+getAwayTeam().getName() );

        for (Player player : getAwayTeam().getPlayers()) {
            printer.accept(player.getName());
        }
    }


}


