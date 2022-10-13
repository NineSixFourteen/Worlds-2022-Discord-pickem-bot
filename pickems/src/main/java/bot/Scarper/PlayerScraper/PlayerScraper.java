package bot.Scarper.PlayerScraper;

import java.util.ArrayList;
import bot.InfoStorage.DataBase;
import bot.InfoStorage.Player.PlayerRow;

public class PlayerScraper {

    public static void main(String[] args) {
        try {
            MakePlayer.makePlayerDB(0).display();
        } catch(Exception e){}
    }

    public static ArrayList<DataBase<PlayerRow>> getPlayers(){
        try{
            DataBase<PlayerRow> playIns = MakePlayer.makePlayerDB(0);
            DataBase<PlayerRow> Main = MakePlayer.makePlayerDB(1);
            ArrayList<DataBase<PlayerRow>> Databases = new ArrayList<>();
            Databases.add(playIns);
            Databases.add(Main);
            Databases.add(AddPlayerRow.add(playIns, Main));
            return Databases;
        } catch(Exception e ){
            return new ArrayList<>();
        }
    }

}
