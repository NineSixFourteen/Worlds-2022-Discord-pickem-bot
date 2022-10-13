package bot.Scarper.PlayerScraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.Player.PlayerRow;
import bot.InfoStorage.Player.PlayerRowBuilder;

public class MakePlayer {

    public static DataBase<PlayerRow> makePlayerDB(int num) throws IOException{
        DataBase<PlayerRow> db = new DataBase<>();
        createRows(db, num);
        return db;
    }

    private static void createRows(DataBase<PlayerRow> db, int num){
        String[] URLS = new String[]{
            "https://lol.fandom.com/wiki/2022_Season_World_Championship/Main_Event/Player_Statistics",
            "https://lol.fandom.com/wiki/2022_Season_World_Championship/Play-In/Player_Statistics"
        };
        try {
            ArrayList<String> rows = GameDataScraper.getPlayerData(URLS[num]);
            HashMap<String,PlayerInfo> pInfo = PlayerInfoScraper.getPlayerInfo();
            HashMap<String,String> champs = PlayerPicksScraper.getPlayerPicks();
            rows.remove(0);
            for(String row : rows){
                String[] info = row.split(" ");
                if(pInfo.get(info[1].toLowerCase()) == null){
                    db.addRow(info[1], makeRow(info, champs.get(info[1]), new PlayerInfo(0,"nameless one", "-","Worthless")));
                } else {
                    db.addRow(info[1], makeRow(info, champs.get(info[1].toLowerCase()), pInfo.get(info[1].toLowerCase()))); 
                }
            }

        } catch(Exception e ){
            e.printStackTrace();
            throw new Error("ERROR GETTING Player Data");
        }
    }

    public static PlayerRow makeRow(String[] info, String champs, PlayerInfo pi) {
        int i = 2;
        PlayerRowBuilder builder = new PlayerRowBuilder();
        builder
            .addName(pi.getName())
            .addGamesPlayed(Integer.parseInt(info[i++]))
            .addWins(Integer.parseInt(info[i++]))
            .addLoses(Integer.parseInt(info[i++]))
            .addWinrate(info[i++])
            .addKills(Float.parseFloat(info[i++]))
            .addDeaths(Float.parseFloat(info[i++]))
            .addAssits(Float.parseFloat(info[i++]))
            .addKda(Float.parseFloat(info[i++]))
            .addCs(Float.parseFloat(info[i++]))
            .addCSPM(Float.parseFloat(info[i++]))
            .addGold(info[i++])
            .addGPM(info[i++])
            .addKillPar(info[i++])
            .addKillShare(info[i++])
            .addGoldShare(info[i++])
            .addChampsPlayed(champs)
            .setPos(pi.getPosition())
            .addFirstBlood(pi.getFb())
            .addTeam(pi.getTeam());
        return builder.build();
    } 
}
