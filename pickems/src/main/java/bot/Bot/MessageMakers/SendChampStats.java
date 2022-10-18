package bot.Bot.MessageMakers;

import java.awt.Color;
import java.text.MessageFormat;
import java.util.ArrayList;

import bot.InfoStorage.Converters;
import bot.InfoStorage.DataBase;
import bot.InfoStorage.Champ.ChampRow;
import bot.InfoStorage.Champ.FilterChamp;
import bot.InfoStorage.Champ.SortChamps;
import bot.InfoStorage.Query.Comparisson;
import bot.Scraper.ChampScraper.MakeChamp;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;

public class SendChampStats {

    private static ArrayList<DataBase<ChampRow>> data = MakeChamp.makeChamps();

    public static void SendKills(MessageChannel channel){
        EmbedBuilder mes = new EmbedBuilder();
        ArrayList<String> order = SortChamps.SortToList(data.get(2), "tk");
        mes.setColor(Color.MAGENTA);
        mes.setTitle("Kills LeaderBoard");
        for(int i = 0; i < 12;i++){
            ChampRow champ = data.get(2).get(order.get(i));
            mes.addField((i+1) + ". " + order.get(i), getKillInfo(champ), true);
        }
        channel.sendMessageEmbeds(mes.build()).queue();
    }

    public static void SendDeaths(MessageChannel channel){
        EmbedBuilder mes = new EmbedBuilder();
        ArrayList<String> order = SortChamps.SortToList(data.get(2), "td");
        mes.setColor(Color.MAGENTA);
        mes.setTitle("Death LeaderBoard");
        for(int i = 0; i < 12;i++){
            ChampRow champ = data.get(2).get(order.get(i));
            mes.addField((i+1) + ". " + order.get(i), getDeathInfo(champ), true);
        }
        channel.sendMessageEmbeds(mes.build()).queue();
    }

    public static void SendPos(MessageChannel channel) {
        EmbedBuilder mes = new EmbedBuilder();
        ArrayList<String> order = SortChamps.SortToList(data.get(2), "pos");
        mes.setColor(Color.MAGENTA);
        mes.setTitle("Positions Played LeaderBoard");
        for(int i = 0; i < 12;i++){
            ChampRow champ = data.get(2).get(order.get(i));
            mes.addField((i+1) + ". " + order.get(i), getPosInfo(champ), true);
        }
        channel.sendMessageEmbeds(mes.build()).queue();
    }

    public static void SendWR(MessageChannel channel) {
        EmbedBuilder mes = new EmbedBuilder();
        ArrayList<String> order = FilterChamp.filterThenSort(
            "wr",
            data.get(2),
            5,
            Comparisson.GTEqualTo,
            "gp");
        mes.setColor(Color.MAGENTA);
        mes.setTitle("WR with >= 5 GP");
        for(int i = 0; i < 12;i++){
            ChampRow champ = data.get(2).get(order.get(i));
            mes.addField((i+1) + ". " + order.get(i), getWRInfo(champ), true);
        }
        channel.sendMessageEmbeds(mes.build()).queue();
    }

    public static void SendPicks(MessageChannel channel,String type) {
        EmbedBuilder mes = new EmbedBuilder();
        String field;
        if(type.equals("=picks")){
            field = "gp";
            mes.setTitle("Games Picked");
        } else {
            field = "b";
            mes.setTitle("Games Banned");
        }
        ArrayList<String> order = SortChamps.SortToList(data.get(2), field);
        mes.setColor(Color.MAGENTA);
        for(int i = 0; i < 12;i++){
            ChampRow champ = data.get(2).get(order.get(i));
            mes.addField((i+1) + ". " + order.get(i), getPickInfo(champ), true);
        }
        channel.sendMessageEmbeds(mes.build()).queue();
    }

    public static void SendTotal(MessageChannel channel) {
        EmbedBuilder em = new EmbedBuilder();
        em.setColor(Color.MAGENTA);
        em.setTitle("Number of Unique Champs - "
                    + data.get(2).getRows().size()
                    + "\nNumber of Unique Champs Played - "
                    + FilterChamp.filterChampsNumTL("gp", data.get(2), 0, Comparisson.GreaterThan).size());
        channel.sendMessageEmbeds(em.build()).queue();
    }

    public static void SendTop(MessageChannel channel) {
        EmbedBuilder mes = new EmbedBuilder();
        mes.setTitle("Champion Stats");
        mes.setColor(Color.MAGENTA);
        ArrayList<String> temp = SortChamps.SortToList(data.get(2), "tk");
        mes.addField("Top Kills: ","> " +   temp.get(0) + " - " + 
            Math.round(data.get(2).get(temp.get(0)).getGamesPlayed() * 
             data.get(2).get(temp.get(0)).getAvgKills()     )  + " kills",false);
        temp = SortChamps.SortToList(data.get(2), "td");
        mes.addField("Top Deaths: ", "> " +  temp.get(0) + " - " + 
            Math.round(data.get(2).get(temp.get(0)).getGamesPlayed() * 
            data.get(2).get(temp.get(0)).getAvgDeaths()     )  + " deaths",false);
        temp =  FilterChamp.filterThenSort(
            "wr",
            data.get(2),
            5,
            Comparisson.GTEqualTo,
            "gp");
        mes.addField("Top WR > 5: ","> " +  temp.get(0) + " - " + data.get(2).get(temp.get(0)).getWinRate() ,false);
        temp = SortChamps.SortToList(data.get(2), "p");
        mes.addField("Top Picked: ", "> " + temp.get(0) + " - " + 
        (data.get(2).get(temp.get(0)).getGamesPlayed()) + " Games",false);
        temp = SortChamps.SortToList(data.get(2), "b");
        mes.addField("Top Banned: ","> " +  temp.get(0) + " - " + 
        (data.get(2).get(temp.get(0)).getBaned()) + " Games",false);
        temp = SortChamps.SortToList(data.get(2), "pos");
        mes.addField("Most Positions: ","> " +  temp.get(0) + " - " + 
        (data.get(2).get(temp.get(0)).getPosPlayed().split(",").length) + " Postions Played",false);
        channel.sendMessageEmbeds(mes.build()).queue();
    }


    private static String getPickInfo(ChampRow champ) {
        MessageFormat fmt = new MessageFormat(
            """
             > Games Picked     - {0}
             > Games Banned     - {1} 
             > Presence         -  {2}    
            """
        );
        Object[] args = {champ.getGamesPlayed(),  //0
                         champ.getBaned(),  //1
                         champ.getPresence()}; //2
        return fmt.format(args);
    }

    //Formatters 
    private static String getWRInfo(ChampRow champ) {
        MessageFormat fmt = new MessageFormat(
            """
             > Win Rate      - {0}
             > Games Won     - {1} 
             > Games Loss - {2}    
            """
        );
        Object[] args = {champ.getWinRate(),  //0
                         champ.getWins(),  //1
                         champ.getLoses()}; //2
        return fmt.format(args);
    }

    private static String getPosInfo(ChampRow champ) {
        MessageFormat fmt = new MessageFormat(
            """
             > No. of Positions    - {0}
             > Postions Played -
             >    {1} 
             > Games Played        - {2}    
            """
        );
        String posPlayedNice = "";
        for(String pos : champ.getPosPlayed().split(",")){
            posPlayedNice += pos.replace(",", "").split(" ")[1] + ", ";
        }
        posPlayedNice = posPlayedNice.substring(0,posPlayedNice.length()-2);
        Object[] args = {champ.getPosPlayed().split(",").length,  //0
                         posPlayedNice, //1
                         champ.getGamesPlayed()}; //2
        return fmt.format(args);
    }

    private static String getDeathInfo(ChampRow champ) {
        MessageFormat fmt = new MessageFormat(
            """
             > Total Deaths   - {0}
             > Average Deaths - {1} 
             > Games Played  - {2}    
            """
        );
        Object[] args = {champ.getAvgDeaths() * champ.getGamesPlayed(),  //0
                         Converters.toOnePlace(champ.getAvgDeaths()),  //1
                         champ.getGamesPlayed()}; //2
        return fmt.format(args);
    }

    public static String getKillInfo(ChampRow champ){
        MessageFormat fmt = new MessageFormat(
            """
             > Total Kills   - {0}
             > Average Kills - {1} 
             > Games Played  - {2}    
            """
        );
        Object[] args = {champ.getAvgKills() * champ.getGamesPlayed(),  //0
                         Converters.toOnePlace(champ.getAvgKills()),  //1
                         champ.getGamesPlayed()}; //2
        return fmt.format(args);
    }

    public static void reset() {
        data = MakeChamp.makeChamps();
    }



}
