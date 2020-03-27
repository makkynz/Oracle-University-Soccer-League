package com.anthony.soccer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class League {

    private final Team[] teams;
    private final Game[] games;
    public  static final int POINTS_FOR_WIN = 2;
    public  static final int POINTS_FOR_DRAW = 1;
    private static Consumer<String>  printer = (msg) -> System.out.printf(msg + "\n");
    private List<Team> leagueWinners;

    public League(){
        teams = getTeams();
        games = getGames(teams);
        leagueWinners = new ArrayList<>();
    }

    public void playGames(){
        for (Game game : games) {
            game.playGame();
            game.printGameResult();
        }

        printLeagueResult();
        calculateLeagueWinners();
        printLeagueWinners();
    }

    private void calculateLeagueWinners() {
        calculateLeagueWinnerByPoints();

        if(leagueWinners.size() == 0){
            calculateLeagueWinnerByGoals();
        }
    }

    private void calculateLeagueWinnerByPoints() {
        Team[] teamsOrderByPoints = Arrays.
                stream(teams).
                sorted((t1,t2) -> Integer.compare(t2.getTotalPoints(), t1.getTotalPoints())).
                toArray(Team[]::new);

        if(teamsOrderByPoints[0].getTotalPoints() != teamsOrderByPoints[1].getTotalPoints()){
            leagueWinners.add(teamsOrderByPoints[0]);
        }
    }

    private void calculateLeagueWinnerByGoals() {
        Team[] teamsOrderByGoals = Arrays.
                stream(teams).
                sorted((t1,t2) -> Integer.compare(t2.getTotalGoals(), t1.getTotalGoals())).
                toArray(Team[]::new);

        if(teamsOrderByGoals[0].getTotalGoals() != teamsOrderByGoals[1].getTotalGoals()){
            leagueWinners.add(teamsOrderByGoals[0]);
        }

        //still no winner found, then allow for multiple winners based on goal count
        if(leagueWinners.size() == 0){
            for (Team team : teamsOrderByGoals) {
                if(leagueWinners.size() == 0 || leagueWinners.get(0).getTotalGoals() == team.getTotalGoals()){
                    leagueWinners.add(team);
                }else{
                    break;
                }
            }
        }
    }

    private void printLeagueWinners() {
        printer.accept("\n-- Congratulations! --");

        for (Team team : leagueWinners) {
            printer.accept("Winner: "+ team.getName());
        }
    }

    private void printLeagueResult() {
        printer.accept("\n-- League Results--");

        for (Team team : teams) {
            printer.accept(String.format("Team %s, Total Goals: %s, Total Points %s",
                    team.getName(),
                    team.getTotalGoals(),
                    team.getTotalPoints()));

        }
    }

    public static void main(String[] args) {
        League league = new League();
        league.playGames();
    }

    private static Team[] getTeams(){
        Player player1 = new Player("George Eliot");
        Player player2 = new Player("Graham Greene");
        Player player3 = new Player("Geoffrey Chaucer");
        Player player4 = new Player("Robert Service");
        Player player5 = new Player("Robbie Burns");
        Player player6 = new Player("Rafael Sabatini");

        Team greenTeam = new Team("Greens", player1, player2, player3);
        Team redTeam = new Team("Reds", player4, player5, player6);

        return new Team[]{greenTeam, redTeam};
    }

    private static Game[] getGames(Team[] teams){
        return new Game[]{
                new Game( teams[0], teams[1], printer),
                new Game( teams[1], teams[0], printer),
                new Game( teams[0], teams[1], printer),
                new Game( teams[1], teams[0], printer),
        };
    }


}
