package com.anthony.soccer;

import com.kenny.utilities.GameUtils;

import java.util.function.Consumer;

public class Game {
    private Team homeTeam;
    private Team awayTeam;
    private Consumer<String> printer;
    private Goal[] goals;


    public Game(Team homeTeam, Team awayTeam, Consumer<String> printer) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.printer = printer;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Goal[] getGoals() {
        return goals;
    }
    public void setGoals(Goal[] goals) {
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

    public String getGameResult(){
        /*build output*/
        StringBuilder output = new StringBuilder();
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
        output.append("Goals :\n");

        for (Goal goal : getGoals()) {
            output.append(String.format("Goal scored after %s mins by %s of the %s \n",
                    goal.getTime(),
                    goal.getPlayer().getName(),
                    goal.getTeam().getName()));
        }

        return output.toString();

    }




}


