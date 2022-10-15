package bot.Scraper.EventScraper;

import java.util.HashMap;

import org.apache.commons.lang.text.StrBuilder;

import bot.InfoStorage.Event.ChampCalculator;
import bot.InfoStorage.Event.EventData;
import bot.Scraper.PlayerScraper.MakePlayer;

public class MakeEvent {

    private static EventScraper es = new EventScraper();

    public static EventData getMatchData(int num){
        String team;
        try{
            team = ChampCalculator.getMostUniqueChamps(MakePlayer.getPlayers().get(2));
        } catch(Exception e){
            team = "";
        }
        switch(num){
            case 1:
                String playinL = es.getLongestTime(0);
                String playinK = es.getMostKills(0);
                HashMap<String,Integer> playinD = es.getDrake(0);
                return new EventData(team, getHighest(playinD), playinL, playinK,es.getDrake(1).get("'Elder'"),playinD);
            case 2:
                String MainL = es.getLongestTime(1);
                String MainK = es.getMostKills(1);
                HashMap<String,Integer> MainD = es.getDrake(1);
                return new EventData(team, getHighest(MainD), MainL, MainK,es.getDrake(1).get("'Elder'"),MainD);
            default :
                String HighestL = compareTime(es.getLongestTime(0), es.getLongestTime(1));
                String HighestK = compareKill(es.getMostKills(0), es.getMostKills(1));
                HashMap<String,Integer> HighestD = addDrakes(es.getDrake(0), es.getDrake(1));
                return new EventData(team, getHighest(HighestD), HighestL, HighestK,es.getDrake(1).get("'Elder'"),HighestD);
        }
    }

    private static String getHighest(HashMap<String, Integer> drakes) {
        String drake = "";
        int biggest = 0;
        for(String key : drakes.keySet()){
            if(drakes.get(key) > biggest){
                drake = key;
                biggest = drakes.get(key);
            } else if(drakes.get(key) == biggest){
                drake += " + " + key;
            }
        }
        return drake;
    }

    private static String compareTime(String longestTime, String longestTime2) {
        return Float.parseFloat(longestTime.replace(":",".").substring(0,4)) < 
        Float.parseFloat(longestTime.replace(":",".").substring(0,4)) ? 
        longestTime : longestTime2 ; 
    }

    private static String compareKill(String mostKills, String mostKills2) {
        return findTime(mostKills) > findTime(mostKills2) ? mostKills : mostKills2;
    }

    private static int findTime(String kills) {
        StrBuilder sb = new StrBuilder(kills);
        while(sb.charAt(0) != ':'){
            sb.deleteCharAt(0);
        }sb.deleteCharAt(0);sb.deleteCharAt(0);
        return Integer.parseInt(sb.substring(0, 2));
    }

    private static HashMap<String,Integer> addDrakes(HashMap<String, Integer> drake, HashMap<String, Integer> drake2) {
        for(String key : drake2.keySet()){
            drake.put(key, drake.get(key) + drake2.get(key));
        }
        return drake;
    } 
    
}
