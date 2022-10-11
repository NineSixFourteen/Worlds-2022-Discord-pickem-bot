package bot.Scarper;

import java.io.IOException;
import java.util.ArrayList;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.PlayerRowBuilder;
import bot.InfoStorage.Row;

public class makePlayer {
    
    public static DataBase makePlayerDB() throws IOException{
        DataBase db = new DataBase();
        addRows("Top",db);
        addRows("Jungle",db);
        addRows("Mid",db);
        addRows("Bot",db);
        addRows("Support",db);
        return db;
    }

    private static void addRows( String Pos, DataBase db){
        String start 
          = "https://lol.fandom.com/wiki/Special:RunQuery/TournamentStatistics?TS%5Bpreload%5D=TournamentByPlayerRole&TS%5Brole%5D=";
        String end 
          = "&TS%5Btournament%5D=2022+Season+World+Championship%2FMain+Event&_run=";
        try {
            ArrayList<String> rows = PageGetter.getPlayerData(start + Pos + end);
            rows.remove(0);
            for(String row : rows){
                String[] info = row.split(" ");
                db.addRow(makeRow(info, Pos)); 
            }
        } catch(Exception e ){
            throw new Error("ERROR GETTING Player Data");
        }
    }

    public static Row makeRow(String[] info, String Pos) {
        int i = 1;
        PlayerRowBuilder builder = new PlayerRowBuilder();
        builder
            .addName(info[i++])
            .addGamesPlayed(Integer.parseInt(info[i++]))
            .addWins(Integer.parseInt(info[i++]))
            .addLoses(Integer.parseInt(info[i++]))
            .addWinrate(info[i++])
            .addKills(Float.parseFloat(info[i++]))
            .addDeaths(Float.parseFloat(info[i++]))
            .addAssits(Float.parseFloat(info[i++]))
            .addKda(Float.parseFloat(info[i++]))
            .addTotalCs(Float.parseFloat(info[i++]))
            .addCSPM(Float.parseFloat(info[i++]))
            .addGold(info[i++])
            .addGPM(info[i++])
            .addKillPar(info[i++])
            .addKillShare(info[i++])
            .addGoldShare(info[i++])
            .addChampsPlayed(Integer.parseInt(info[i++]))
            .setPos(Pos);
        return builder.build();
    } 
}
