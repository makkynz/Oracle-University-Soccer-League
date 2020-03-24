package com.anthony.soccer;

import org.apache.commons.lang3.ArrayUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class League {
    public static void main(String[] args) {

        /* practice 6 */

        Player player1 = new Player("George Eliot");
        Player player2 = new Player("Graham Greene");
        Player player3 = new Player("Geoffrey Chaucer");
        Player player4 = new Player("Robert Service");
        Player player5 = new Player("Robbie Burns");
        Player player6 = new Player("Rafael Sabatini");

        Team greenTeam = new Team("Greens", player1, player2, player3);
        Team redTeam = new Team("Reds", player4, player5, player6);

        Game game1 = new Game(  greenTeam, redTeam,
                                new Goal(greenTeam, player3,12),
                                new Goal(greenTeam, player2,23),
                                new Goal(redTeam, player4,55));


        /*print game result*/
        StringBuilder output = new StringBuilder();
        output.append(game1.getHomeTeam().getName() + ":\n");

        for (Player player : game1.getHomeTeam().getPlayers()) {
            output.append(player.getName() + "\n");
        }

        output.append("\n");
        output.append(game1.getAwayTeam().getName() + ":\n");

        for (Player player : game1.getAwayTeam().getPlayers()) {
            output.append(player.getName() + "\n");
        }

        output.append("\n");
        output.append("Goals :\n");

        for (Goal goal : game1.getGoals()) {
            output.append(String.format("Goal scored after %s mins by %s of the %s \n",
                    goal.getTime(),
                    goal.getPlayer().getName(),
                    goal.getTeam().getName()));
        }

        /* practice 7 */
        output.append("\n");

        //search for a player
        String searchStr = "sab";
        Pattern searchPattern = Pattern.compile("(?!\\s)"+searchStr, Pattern.CASE_INSENSITIVE);
        Player[] allPlayersInTeams =   ArrayUtils.addAll(greenTeam.getPlayers(), redTeam.getPlayers());
        for (Player player : allPlayersInTeams) {
            if(searchPattern.matcher(player.getName()).find()){
                output.append("Found " + player.getName()+"\n");
            }
        }

        //print players last name first
        Pattern namePattern = Pattern.compile("([A-Za-z]+)\\s+([A-Za-z]+)", Pattern.CASE_INSENSITIVE);

        for (Player player : greenTeam.getPlayers()) {

            Matcher matcher = namePattern.matcher(player.getName());
            matcher.find();
            String firstName = matcher.group(1);
            String lastName = matcher.group(2);
            output.append(lastName + ", " + firstName +"\n");

        }
        System.out.printf(output.toString());
    }
}
