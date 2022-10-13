package bot.InfoStorage.Event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.Player.PlayerRow;

public class ChampCalculator {


    public static String getMostUniqueChamps(DataBase<PlayerRow> db){
        return getMostUniqueChampsHelper(db);
    }

    private static String getMostUniqueChampsHelper(DataBase<PlayerRow> db) {
        HashMap<String,Set<String>> teamsAndChamps = new HashMap<>();
        for(String key : db.getRows().keySet()){
            PlayerRow player = db.get(key);
            if(teamsAndChamps.containsKey(player.getTeam())){
                Set<String> champs = teamsAndChamps.get(player.getTeam());
                for(String champ : player.getChampsPlayed().split(",")){
                    champs.add(champ);
                }
                teamsAndChamps.put(player.getTeam(), champs);
            } else{
                Set<String> champs = new HashSet<>();
                for(String champ : player.getChampsPlayed().split(",")){
                    champs.add(champ);
                }
                teamsAndChamps.put(player.getTeam(), champs);
            }
        }
        String team = "";
        int biggest = 0 ;
        for(String key : teamsAndChamps.keySet()){
            int size = teamsAndChamps.get(key).size();
            if(size > biggest){
                biggest = size;
                team = key;
            } else if (size == biggest){
                team += " + " + team;
            }
        }
        return team;
    }
    
}
