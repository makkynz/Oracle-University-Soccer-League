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
        printer.accept(getAwayTeam().getName() + " vs " + getHomeTeam().getName() + "\n");
        int randNumOfGoals = (int)(Math.random()*((maxNumOfGoals)+1));
        GameUtils.addGameGoals(this, randNumOfGoals);
    }

    public void printGameResult(){
        /*build output*/
        StringBuilder output = new StringBuilder();
        buildResultOutput(output);
        buildTeamsOutput(output);
        buildGoalsOutput(output);

        printer.accept(output.toString());
    }

    public void buildResultOutput(StringBuilder output) {
        int homeTeamGoals = getTeamGoals(homeTeam);
        int awayTeamGoals = getTeamGoals(awayTeam);

        if(homeTeamGoals > awayTeamGoals){
            output.append("Winner: "+ homeTeam.getName());
        }else if(homeTeamGoals < awayTeamGoals) {
            output.append("Winner: "+ awayTeam.getName());
        }else{
            output.append("Draw:");
        }
        output.append("\n");
        output.append("Final Score: "+awayTeamGoals+ " - " +  homeTeamGoals);
        output.append("\n\n");
    }

    private int getTeamGoals(Team team){
        return goals.stream().filter(g->g.getTeam().equals(team)).collect(Collectors.toList()).size();
    }

    private void buildGoalsOutput(StringBuilder output) {
        output.append("Goals :\n");

        for (Goal goal : getGoals()) {
            output.append(String.format("Goal scored after %s mins by %s of the %s \n",
                    goal.getTime(),
                    goal.getPlayer().getName(),
                    goal.getTeam().getName()));
        }
    }

    private void buildTeamsOutput(StringBuilder output) {
        output.append(getHomeTeam().getName() + ":\n");

        for (Player player : getHomeTeam().getPlayers()) {
            output.append(player.getName() + "\n");
        }

        output.append("\n");
        output.append(getAwayTeam().getName() + ":\n");

        for (Player player : getAwayTeam().getPlayers()) {
            output.append(player.getName() + "\n");
        }

        output.append("\n");
    }


}


