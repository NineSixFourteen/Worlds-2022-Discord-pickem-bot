package bot.InfoStorage.QuerySystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import bot.InfoStorage.DataBase;

import bot.InfoStorage.Champ.ChampRow;
import bot.Scarper.ChampScraper.MakeChamp;

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

    public static String SortTR(DataBase<ChampRow> champs,String mes){
        ArrayList<Pair> pairs = new ArrayList<>();
        for(String key : champs.getRows().keySet()){
            pairs.add(
                new Pair(
                    key, 
                    champs.getRows().get(key).lookup(mes)
                ));
        }
        Collections.sort(pairs);
        String message = "";
        for(Pair pair : pairs){
            message += pair.getID() + " | " + pair.getValue() + "\n";
        }
        return message;
    }

    public static String SortTM(DataBase<ChampRow> champs,String mes){
        ArrayList<Pair> pairs = new ArrayList<>();
        for(String key : champs.getRows().keySet()){
            pairs.add(
                new Pair(
                    key, 
                    champs.getRows().get(key).lookup(mes)
                ));
        }
        Collections.sort(pairs);
        String message = "";
        for(Pair pair : pairs){
            message += pair.getID() + " | " + pair.getValue() + "\n";
        }
        return message;
    }

    public static String[][] SortTL(DataBase<ChampRow> champs, String mes) {
        ArrayList<Pair> pairs = new ArrayList<>();
        for(String key : champs.getRows().keySet()){
            pairs.add(
                new Pair(
                    key, 
                    champs.getRows().get(key).lookup(mes)
                ));
        }
        Collections.sort(pairs);
        String[][] arr = new String[2][pairs.size()];
        for(int i = 0;i <pairs.size();i++){
            arr[0][i] = pairs.get(i).getID();
            arr[1][i] = pairs.get(i).getValue() + "";
        }
        return arr;
        
        
    }

}
