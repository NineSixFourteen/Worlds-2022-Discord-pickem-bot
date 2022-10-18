package bot.InfoStorage.Champ;

import java.util.ArrayList;
import java.util.Collections;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.Pair;

public class SortChamps {
    
    public static ArrayList<String> SortToList(DataBase<ChampRow> champs, String mes){
        ArrayList<Pair> pairs = new ArrayList<>();
        for(String key : champs.getRows().keySet()){
            pairs.add(
                new Pair(
                    key, 
                    champs.get(key).getGamesPlayed() > 0 ? champs.getRows().get(key).lookup(mes) : 0
                ));
        }
        Collections.sort(pairs);
        ArrayList<String> names = new ArrayList<>();
        for(Pair pair : pairs){
            names.add(pair.getID());
        }
        return names;
    }

    
}