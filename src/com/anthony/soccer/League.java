package com.anthony.soccer;

public class League {

    private final Team[] teams;
    private final Game[] games;

    public League(){
        teams = getTeams();
        games = getGames(teams);
    }

    public void playGames(){
        for (Game game : games) {
            game.playGame();
            System.out.printf(game.getGameResult());
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
        Team homeTeam = teams[0];
        Team awayTeam = teams[1];
        Game game1 = new Game(  homeTeam, awayTeam);

        return new Game[]{game1};
    }
}
