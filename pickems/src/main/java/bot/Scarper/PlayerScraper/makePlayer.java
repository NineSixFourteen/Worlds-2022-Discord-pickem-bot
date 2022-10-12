package bot.Scarper.PlayerScraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.Player.PlayerRow;
import bot.InfoStorage.Player.PlayerRowBuilder;

public class makePlayer {

    public static void main(String[] args) throws IOException {
        //DataBase<PlayerRow> db = makePlayerMainDB();
        //db.display();
        DataBase<PlayerRow> db2 = makePlayerPlayInDB();
        db2.display();
    }
    
    public static DataBase<PlayerRow> makePlayerMainDB() throws IOException{
        DataBase<PlayerRow> db = new DataBase<>();
        addRows("Top",db);
        addRows("Jungle",db);
        addRows("Mid",db);
        addRows("Bot",db);
        addRows("Support",db);
        return db;
    }

    public static DataBase<PlayerRow> makePlayerPlayInDB() throws IOException{
        DataBase<PlayerRow> db = new DataBase<>();
        addRows2("Top",db);
        addRows2("Jungle",db);
        addRows2("Mid",db);
        addRows2("Bot",db);
        addRows2("Support",db);
        return db;
    }


    private static void addRows( String Pos, DataBase<PlayerRow> db){
        String start 
          = "https://lol.fandom.com/wiki/Special:RunQuery/TournamentStatistics?TS%5Bpreload%5D=TournamentByPlayerRole&TS%5Brole%5D=";
        String end 
          = "&TS[tournament]=2022+Season+World+Championship%2FMain+Event&_run=";
        try {
            ArrayList<String> rows = PlayerScraper.getPlayerData(start + Pos + end);
            HashMap<String,String> champs = PlayerScraper.getPlayerPicks();
            HashMap<String,playerInfo> pInfo = PlayerScraper.getPlayerInfo();
            rows.remove(0);
            for(String row : rows){
                String[] info = row.split(" ");
                if(pInfo.get(info[1].toLowerCase()) == null){
                    System.out.println(info[1]);
                    db.addRow(info[1], makeRow(info, Pos, champs.get(info[1]), new playerInfo(0, "-")));
                } else {
                    db.addRow(info[1], makeRow(info, Pos,champs.get(info[1]), pInfo.get(info[1].toLowerCase()))); 
                }
            }

        } catch(Exception e ){
            e.printStackTrace();
            throw new Error("ERROR GETTING Player Data");
        }
    }

    private static void addRows2( String Pos, DataBase<PlayerRow> db){
        String start 
          = "https://lol.fandom.com/wiki/Special:RunQuery/TournamentStatistics?TS%5Bpreload%5D=TournamentByPlayerRole&TS%5Brole%5D=";
        String end 
          = "&TS[tournament]=2022+Season+World+Championship%2FPlay-In&_run=";
        try {
            ArrayList<String> rows = PlayerScraper.getPlayerData(start + Pos + end);
            HashMap<String,String> champs = PlayerScraper.getPlayerPicksPL();
            HashMap<String,playerInfo> pInfo = PlayerScraper.getPlayerInfo();
            rows.remove(0);
            for(String row : rows){
                String[] info = row.split(" ");
                if(pInfo.get(info[1].toLowerCase()) == null){
                    System.out.println(info[1]);
                    db.addRow(info[1], makeRow(info, Pos, champs.get(info[1]), new playerInfo(-1, "-")));
                } else {
                    db.addRow(info[1], makeRow(info, Pos,champs.get(info[1]), pInfo.get(info[1].toLowerCase()))); 
                }
            }

        } catch(Exception e ){
            e.printStackTrace();
            throw new Error("ERROR GETTING Player Data");
        }
    }

    public static PlayerRow makeRow(String[] info, String Pos, String champs, playerInfo pi) {
        int i = 2;
        PlayerRowBuilder builder = new PlayerRowBuilder();
        builder
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
            .addChampsPlayed(champs)
            .setPos(Pos)
            .addFirstBlood(pi.getFb())
            .addTeam(pi.getTeam());
        return builder.build();
    } 
}
