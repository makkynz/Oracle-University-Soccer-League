package com.kenny.utilities;

import com.anthony.soccer.Game;
import com.anthony.soccer.Goal;
import com.anthony.soccer.Player;
import com.anthony.soccer.Team;

import java.util.Arrays;
import java.util.Comparator;

public class GameUtils {

    public static void addGameGoals(Game currGame, int numOfGoals) {

        Goal[] goals =new Goal[numOfGoals];

        for (int i = 0; i < goals.length; i++) {
            Team team = Math.random() > 0.5 ? currGame.getHomeTeam() : currGame.getAwayTeam();
            Player[] teamPlayers = team.getPlayers();
            Player goalScorer = teamPlayers[(int) (Math.random() * teamPlayers.length)];
            double time = (int) (Math.random() * 90);

            goals[i] = new Goal(team, goalScorer, time);
        }

        Arrays.sort(goals, Comparator.comparing(g -> Double.valueOf(g.getTime())));

        currGame.setGoals(goals);

    }
}
