package utility;

import soccer.Goal;
import soccer.Game;
import soccer.Team;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 * @author ksomervi
 */
public class GameUtils {

    public static void addGameGoals(Game currGame) {
        
        //System.out.println(currGame.awayTeam + " : " + currGame.homeTeam);

        // Or possibly throw an Exception?
        if (currGame.goals == null) {
            currGame.goals = new Goal[(int) (Math.random() * 10)];   // If goals not initialized max will be 9
        }

        //System.out.println(currGame.goals.length);
        int i = 0;
        for (Goal currGoal : currGame.goals) {
            currGoal = new Goal();
            currGoal.team = Math.random() > 0.5 ? getHomeTeam(currGame, "home") : getHomeTeam(currGame, "away");
            currGoal.player = currGoal.team.players[(int) (Math.random() * currGoal.team.players.length)];
            currGoal.time = (int) (Math.random() * 90);
            currGame.goals[i] = currGoal;
            i++;
        }
        Arrays.sort(currGame.goals, (g1, g2) -> Double.valueOf(g1.time).compareTo(Double.valueOf(g2.time)));

    }

    // Uses reflection so works with getter method or public field
    private static Team getHomeTeam(Game currGame, String homeOrAway) {
        Team theTeam = null;
        Method m;
        Field f;
        try {
            m = Game.class.getMethod("get" + Character.toUpperCase(homeOrAway.charAt(0)) + homeOrAway.substring(1) + "Team");
            theTeam = (Team)m.invoke(currGame);
            //System.out.println(theTeam);
        } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException em) {
            try {
                f = Game.class.getField(homeOrAway + "Team");
                theTeam = (Team)f.get(currGame);
                //System.out.println(theTeam);
            } catch (NoSuchFieldException|IllegalAccessException ef) { 
                System.out.println("The addGoals() utility requires the Goal class to contain either:\n" +
                        "public String fields called homeTeam and awayTeam, OR,\n" +
                        "public accessor methods called getHomeTeam() and getAwayTeam().");
            }
        }
        return theTeam;
    }
}
