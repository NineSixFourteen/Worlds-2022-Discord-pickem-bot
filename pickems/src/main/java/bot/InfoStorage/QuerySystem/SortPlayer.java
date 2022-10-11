package bot.InfoStorage.QuerySystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.Player.PlayerRow;
import bot.Scarper.PlayerScraper.makePlayer;

public class SortPlayer {

    public static void main(String[] args) throws IOException {
        DataBase<PlayerRow> db = makePlayer.makePlayerDB();
        Sort(db,5);
    }

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
    
}
