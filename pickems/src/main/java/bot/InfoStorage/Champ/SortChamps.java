package bot.InfoStorage.Champ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.Pair;
import bot.Scraper.ChampScraper.MakeChamp;

public class SortChamps {

    public static void main(String[] args) throws IOException {
        DataBase<ChampRow> db = MakeChamp.makeChampMainDB();
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

    public static ArrayList<String> SortTotal(DataBase<ChampRow> champs,String mes){
        ArrayList<Pair> pairs = new ArrayList<>();
        for(String key : champs.getRows().keySet()){
            pairs.add(
                new Pair(
                    key, 
                    champs.get(key).getGamesPlayed() > 0 ? champs.getRows().get(key).lookup(mes) * champs.getRows().get(key).lookup("gp") : 0
                ));
        }
        Collections.sort(pairs);
        ArrayList<String> order = new ArrayList<>();
        for(Pair pair : pairs){
            order.add(pair.getID());
        }
        return order;
    }

    public static ArrayList<String> SortToList(DataBase<ChampRow> champs, String mes){
        ArrayList<Pair> pairs = new ArrayList<>();
        for(String key : champs.getRows().keySet()){
            pairs.add(
                new Pair(
                    key, 
                    champs.getRows().get(key).lookup(mes)
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