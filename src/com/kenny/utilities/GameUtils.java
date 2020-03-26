package com.kenny.utilities;

import com.anthony.soccer.Game;
import com.anthony.soccer.Goal;
import com.anthony.soccer.Player;
import com.anthony.soccer.Team;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameUtils {

    public static void addGameGoals(Game currGame, int numOfGoals) {

        List<Goal> goals = new ArrayList<>();

        for (int i = 0; i < numOfGoals; i++) {
            Team team = Math.random() > 0.5 ? currGame.getHomeTeam() : currGame.getAwayTeam();
            Player[] teamPlayers = team.getPlayers();
            Player goalScorer = teamPlayers[(int) (Math.random() * teamPlayers.length)];
            double time = (int) (Math.random() * 90);

            goals.add(new Goal(team, goalScorer, time));
        }

        goals.sort(Comparator.comparing(g -> Double.valueOf(g.getTime())));

        currGame.setGoals(goals);

    }
}
