package bot.Scraper.ChampScraper;

import java.util.HashSet;

import bot.InfoStorage.Converters;
import bot.InfoStorage.DataBase;
import bot.InfoStorage.Champ.ChampRow;
import bot.InfoStorage.Champ.ChampRowBuilder;

public class AddChampRows {

    public static DataBase<ChampRow> add(DataBase<ChampRow> data1, DataBase<ChampRow> data2){
        for(String key : data2.getRows().keySet()){
            if(data1.getRows().containsKey(key)){
                ChampRow cr = addRows(
                    data1.getRows().get(key),
                    data2.getRows().get(key)
                    ); 
                data1.getRows().remove(key);
                data1.getRows().put(key, cr);
            } else {
                data1.addRow(key, data2.getRows().get(key));
            }
        }
        return data1;
    }

    private static ChampRow addRows(ChampRow champRow, ChampRow champRow2) {
        float totalGames = getTotalGames(champRow,champRow2);
        ChampRowBuilder crb = new ChampRowBuilder();
        int gamesPlayed = champRow.getGamesPlayed() + champRow2.getGamesPlayed();
        crb
         .addPresence(getPrec(champRow, champRow2,totalGames))
         .addBans( champRow.getBaned() + champRow2.getBaned())
         .addGamesPlayed(gamesPlayed)
         .addWins( champRow.getWins() + champRow2.getWins() ) 
         .addLoses( champRow.getLoses() + champRow2.getLoses())
         .addWinRate(getWr(champRow, champRow2) )
         .addKills(
            (Math.round(champRow.getAvgKills() * champRow.getGamesPlayed()) + 
            Math.round(champRow2.getAvgKills() * champRow2.getGamesPlayed()) + 0F)/gamesPlayed)
         .addDeaths(
            (Math.round(champRow.getAvgDeaths() * champRow.getGamesPlayed()) + 
            Math.round(champRow2.getAvgDeaths() * champRow2.getGamesPlayed()) + 0F) / gamesPlayed)
         .addAssists( 
         ((champRow.getAvgAssists() * champRow.getGamesPlayed()) + 
            (champRow2.getAvgAssists() * champRow2.getGamesPlayed()) + 0F) / gamesPlayed)
         .addKda( getKda(champRow, champRow2, gamesPlayed) )
         .addCS(
             ((champRow.getAvgCs() * champRow.getGamesPlayed()) + 
             (champRow2.getAvgCs()) * champRow2.getGamesPlayed() )/ gamesPlayed)
         .addCSPM( (champRow.getCspm() + champRow2.getCspm() ) / 2 )
         .addGold(getGold(champRow, champRow2))
         .addGPM(getGoldPM(champRow, champRow2))
         .addKpar( getKillPar(champRow,champRow2))
         .addKillShare( getKillShare(champRow,champRow2) ) 
         .addGoldShare( getGoldShare(champRow,champRow2)  ) 
         .setPositionPlayed( getPosPlayed(champRow,champRow2) ) ;
       return crb.build();
    }

    private static String getGoldPM(ChampRow champRow, ChampRow champRow2) {
       float totalGold1 = Converters.removeK(champRow.getGold()) * champRow.getGamesPlayed();
       float mins1 = totalGold1 / Converters.asFloat(champRow.getGPM());
       float totalGold2 = Converters.removeK(champRow2.getGold()) * champRow2.getGamesPlayed();
       float mins2 = totalGold2 / Converters.asFloat(champRow2.getGPM());
       return ((totalGold1  + totalGold2) / (mins1 + mins2)) + "";
    }

    private static String getPosPlayed(ChampRow row, ChampRow row1) {
        String[] position1 = row.getPosPlayed().split(",");
        String[] position2 = row1.getPosPlayed().split(",");
        HashSet<String> positions = new HashSet<>();
        for(String pos : position1){
            positions.add(pos);
        }  
        for(String pos : position2){
            positions.add(pos);
        }
        if(positions.contains("-")){
            positions.remove("-");
        }
        if(positions.contains("")){
            positions.remove("");
        }
        String ret = "";
        for(String pos : positions){
            ret += (pos + ",");
        }
        if(!ret.isEmpty()){
            ret = ret.substring(0, ret.length() - 1);
        }
        return ret;
    }

    private static String getGoldShare(ChampRow row, ChampRow row1) {
        float gold1 = !row.getGold().equals("-") ? Float.parseFloat(row.getGold().substring(0, row.getGold().length() -1)) : 0; ;
        float gold2 = !row1.getGold().equals("-") ? Float.parseFloat(row1.getGold().substring(0, row1.getGold().length() -1)) : 0; 
        gold1 = gold1 * row.getGamesPlayed();
        gold2 = gold2 * row1.getGamesPlayed();
        float totalPlayIn = (1 / Converters.removePer(row.getGShare()) * 100) * gold1;
        float totalMain = (1 / Converters.removePer(row1.getGShare()) * 100) * gold2;
        return ((gold1 + gold2) / (totalPlayIn + totalMain) *100) + "%";
    }

    private static String getKillPar(ChampRow row, ChampRow row1) {
        float kill1 = (row.getAvgKills() * row.getGamesPlayed()) + (row.getAvgAssists() * row.getGamesPlayed());
        float kill2 = (row1.getAvgKills() * row1.getGamesPlayed()) + (row1.getAvgAssists() * row1.getGamesPlayed());
        float totalPlayIn = (1 / Converters.removePer(row.getKPar()) * 100) * kill1;
        float totalMain = (1 / Converters.removePer(row1.getKPar()) * 100) * kill2;
        return ((kill1 + kill2) / (totalPlayIn + totalMain) * 100) + "%";
    }

    private static String getKillShare(ChampRow row, ChampRow row1) {
        float kill1 = row.getAvgKills() * row.getGamesPlayed()  ;
        float kill2 = row1.getAvgKills() * row1.getGamesPlayed(); 
        float totalPlayIn = (1 / Converters.removePer(row.getKShare()) *100) * kill1;
        float totalMain = (1 / Converters.removePer(row1.getKShare()) *100) * kill2;
        return ((kill1 + kill2) / (totalPlayIn + totalMain) *100) + "%";
    }

    private static float getTotalGames(ChampRow row, ChampRow row1){
        // 1 / Presence * (pick + ban) = total games
        int totalPlayIn = Math.round(
            (1 / Converters.asFloat(row.getPresence().substring(0, row.getPresence().length() -1)) * 
            (row.getGamesPlayed() + row.getBaned())) * 100
        );
        int totalMain = Math.round(
            (1 / Converters.asFloat(row1.getPresence().substring(0, row1.getPresence().length() -1)) * 
            (row1.getGamesPlayed() + row1.getBaned())) * 100
        );
        return totalMain + totalPlayIn; 
    }

    private static String getPrec(ChampRow row, ChampRow row1, float totalGames){
        int banned = row.getBaned() + row1.getBaned();
        int played = row.getGamesPlayed() + row1.getGamesPlayed() ;
        if(totalGames!= 0) {
            float prec = (banned + played) / totalGames;
            return Math.round(prec * 100) + "%";
        }else{
            return "0%";
        }
    }

    private static String getWr(ChampRow row , ChampRow row1){ 
        int wins  = row.getWins() + row1.getWins() ;
        int totalGames = row.getGamesPlayed() + row1.getGamesPlayed();
        if(totalGames != 0 ){
            return ((Converters.asFloat(wins+ "") / totalGames) *100) + "%";
        } else{
            return "0%";
        }
    }

    private static float getKda(ChampRow row , ChampRow row1, int gamesPlayed){ 
        int Kills = Math.round((row.getAvgKills() * gamesPlayed) + (row1.getAvgKills() * gamesPlayed))  ;
        int Assists = Math.round((row.getAvgAssists() * gamesPlayed) + (row1.getAvgAssists() * gamesPlayed));
        int Deaths = Math.round((row.getAvgDeaths() * gamesPlayed) + (row1.getAvgDeaths() * gamesPlayed));
        if(Deaths != 0 ){
            return (Float.parseFloat((Kills + Assists)+ "") / Deaths) ;
        } else{
            return 0;
        }
    }

    private static String getGold(ChampRow row, ChampRow row1){
        float gold1 = !row.getGold().equals("-") ?Float.parseFloat(row.getGold().substring(0, row.getGold().length() -1)) : 0; ;
        float gold2 = !row1.getGold().equals("-") ? Float.parseFloat(row1.getGold().substring(0, row1.getGold().length() -1)) : 0; 
        return ((gold1 + gold2)/2) + "k";

    }
    
}
