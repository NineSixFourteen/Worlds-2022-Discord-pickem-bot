package bot.InfoStorage.QuerySystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import bot.InfoStorage.DataBase;

import bot.InfoStorage.Champ.ChampRow;
import bot.Scarper.ChampScraper.makeChamp;

public class SortChamps {

    public static void main(String[] args) throws IOException {
        DataBase<ChampRow> db = makeChamp.makeChampMainDB();
        Sort(db,"pos");
    }

    public static void Sort(DataBase<ChampRow> champs,int id){
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

    public static void Sort(DataBase<ChampRow> champs,String mes){
        ArrayList<Pair> pairs = new ArrayList<>();
        for(String key : champs.getRows().keySet()){
            pairs.add(
                new Pair(
                    key, 
                    champs.getRows().get(key).lookup(mes)
                ));
        }
        Collections.sort(pairs);
        for(Pair pair : pairs){
            System.out.println(pair.getID() + " | " + pair.getValue());
        }
    }

}
