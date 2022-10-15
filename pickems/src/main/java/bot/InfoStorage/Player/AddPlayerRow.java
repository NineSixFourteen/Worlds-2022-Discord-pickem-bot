package bot.InfoStorage.Player;

import java.util.HashSet;
import bot.InfoStorage.Converters;
import bot.InfoStorage.DataBase;

public class AddPlayerRow {

    public static DataBase<PlayerRow> add(DataBase<PlayerRow> data1, DataBase<PlayerRow> data2){
        for(String key : data2.getRows().keySet()){
            if(data1.getRows().containsKey(key)){
                PlayerRow cr = addRows(
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

    private static PlayerRow addRows(PlayerRow row, PlayerRow row1) {
        if(row.getWins() == 3 && row.getKda() < 5){
            System.out.println(row1.getKills());
            System.out.println(row.getKills());
            System.out.println(row.getKills() + row1.getKills());
        }
        int gp = row.getGamesPlayed() + row1.getGamesPlayed();
        PlayerRowBuilder prb = new PlayerRowBuilder();
        prb
         .addName(row.getName())
         .addGamesPlayed(gp)
         .addWins(row.getWins() + row1.getWins())
         .addLoses(row.getLoses() + row1.getLoses())
         .addWinrate(
            Converters.toOnePlace((0F + row.getWins() + row1.getWins())
            / (row.getGamesPlayed() + row1.getGamesPlayed()) * 100) + "%")
         .addKills(row.getKills() + row1.getKills())
         .addDeaths(row.getDeaths() + row1.getDeaths()) 
         .addAssits(row.getAssists() + row1.getAssists())
         .addKda(getKda(row, row1))
         .addCs(Converters.toOnePlace(row.getCs() + row1.getCs() / gp))
         .addCSPM(getCSPM(row,row1))
         .addGold(getGold(row,row1))
         .addGPM(getGPM(row,row1))
         .addKillPar(getKillPar(row,row1))
         .addKillShare(getKillShare(row,row1))
         .addGoldShare(getGoldShare(row,row1))
         .addChampsPlayed(getChampsPlayed(row,row1))
         .setPos(row.getPos())
         .addFirstBlood(row.getFirstBlood())
         .addTeam(row.getTeam());
         return prb.build();
    }

    private static String getChampsPlayed(PlayerRow row, PlayerRow row1) {
        String[] champ1 = row.getChampsPlayed().split(",");
        String[] champ2 = row.getChampsPlayed().split(",");
        HashSet<String> champs = new HashSet<>();
        for(String champ : champ1){
            champs.add(champ);
        }
        for(String champ : champ2){
            champs.add(champ);
        }
        if(champs.contains("-")){
            champs.remove("-");
        }
        if(champs.contains("")){
            champs.remove("");
        }
        String ret = "";
        for(String champ : champs){
            ret += (champ + ",");
        }
        if(!ret.isEmpty()){
            ret = ret.substring(0, ret.length() - 1);
        }
        return ret;
    }

    private static String getGoldShare(PlayerRow row, PlayerRow row1) {
        float totalPlayIn = (1 / Converters.removePer(row.getGoldShare()) * 100) * Converters.removeK(row.getGold());
        float totalMain = (1 / Converters.removePer(row1.getGoldShare()) * 100) * Converters.removeK(row1.getGold());
        return Converters.toOnePlace(((Converters.removeK(row.getGold()) + Converters.removeK(row1.getGold()) ) / ( totalPlayIn + totalMain) *100 ))+ "%";
    }

    private static String getKillShare(PlayerRow row, PlayerRow row1) {
        float kill1 = row.getKills() ;
        float kill2 = row1.getKills() ; 
        float totalPlayIn = (1 / Converters.removePer(row.getKillShare()) * 100) * kill1;
        float totalMain = (1 / Converters.removePer(row1.getKillShare()) * 100) * kill2;
        return Converters.toOnePlace((kill1 + kill2) / (totalPlayIn + totalMain) *100) + "%";
    }

    private static String getKillPar(PlayerRow row, PlayerRow row1) {
        float KA1 = (row.getKills() + row.getAssists());
        float KA2 = (row1.getKills() + row1.getAssists());
        float totalPlayIn = (1 / Converters.removePer(row.getKillPar()) * 100) * KA1;
        float totalMain = (1 / Converters.removePer(row1.getKillPar()) * 100) * KA2;
        return Converters.toOnePlace((KA1 + KA2) / (totalPlayIn + totalMain) * 100)+ "%";
    }

    private static String getGPM(PlayerRow row, PlayerRow row1) {
        float minutes1 = (Converters.removeK(row.getGold()) / (Converters.asFloat(row.getGoldpm())));
        float minutes2 = (Converters.removeK(row1.getGold()) / (Converters.asFloat(row1.getGoldpm())));
        return  "" + Converters.toOnePlace((Converters.removeK(row.getGold())  + Converters.removeK(row1.getGold())) / (minutes1 + minutes2));
    }

    private static String getGold(PlayerRow row, PlayerRow row1) {
        return (( (Converters.removeK(row.getGold()) + Converters.removeK(row1.getGold())) ) ) + "k";
    }

    private static float getCSPM(PlayerRow row, PlayerRow row1) {
        float minutes1 = row.getCs() / row.getCspm();
        float minutes2 = row1.getCs() / row1.getCspm();
        return  (row.getCs()  + row1.getCs())/ (minutes1 + minutes2);
    }

    private static float getKda(PlayerRow row, PlayerRow row1) {
       int kills = row.getKills() + row1.getKills();
       int deaths = row.getDeaths() + row1.getDeaths();
       int assists = row.getAssists() + row1.getAssists();
       return Converters.toOnePlace((float) (((kills + assists) + 0.0 ) / deaths));
    }
}
