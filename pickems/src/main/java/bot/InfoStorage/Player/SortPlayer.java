package bot.InfoStorage.Player;

import java.util.ArrayList;
import java.util.Collections;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.Pair;

public class SortPlayer {


    public static void Sort(DataBase<PlayerRow> champs,int id){
        ArrayList<Pair> pairs = new ArrayList<>();
        for(String key : champs.getRows().keySet()){
            pairs.add(
                new Pair(
                    key, 
                    champs.getRows().get(key).lookupID(id)
                ));
        }
        Collections.sort(pairs);
        for(Pair pair : pairs){
            System.out.println(pair.getID() + " | " + pair.getValue());
        }
    }
    
    public static ArrayList<String> SortToList(DataBase<PlayerRow> champs,int id){
        ArrayList<Pair> pairs = new ArrayList<>();
        for(String key : champs.getRows().keySet()){
            pairs.add(
                new Pair(
                    key, 
                    champs.getRows().get(key).lookupID(id)
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
