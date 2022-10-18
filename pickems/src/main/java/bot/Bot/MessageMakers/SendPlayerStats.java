package bot.Bot.MessageMakers;

import java.awt.Color;
import java.text.MessageFormat;
import java.util.ArrayList;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.Player.PlayerRow;
import bot.InfoStorage.Player.SortPlayer;
import bot.Scraper.PlayerScraper.MakePlayer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;

public class SendPlayerStats {

    private static DataBase<PlayerRow> players = MakePlayer.getPlayers().get(2);

    public static void sendKills(MessageChannel channel){
        EmbedBuilder mes = new EmbedBuilder();
        ArrayList<String> order = SortPlayer.SortToList(players, 5);
        mes.setColor(Color.MAGENTA);
        mes.setTitle("Kills LeaderBoard");
        for(int i = 0; i < 12;i++){
            PlayerRow player = players.get(order.get(i));
            mes.addField((i+1) + ". " + order.get(i), getKillInfo(player), true);
        }
        channel.sendMessageEmbeds(mes.build()).queue();
    }

    public static void sendDeaths(MessageChannel channel){
        EmbedBuilder mes = new EmbedBuilder();
        ArrayList<String> order = SortPlayer.SortToList(players, 6);
        mes.setColor(Color.MAGENTA);
        mes.setTitle("Death LeaderBoard");
        for(int i = 0; i < 12;i++){
            PlayerRow player = players.get(order.get(i));
            mes.addField((i+1) + ". " + order.get(i), getDeathInfo(player), true);
        }
        channel.sendMessageEmbeds(mes.build()).queue();
    }

    public static void sendChamps(MessageChannel channel){
        EmbedBuilder mes = new EmbedBuilder();
        ArrayList<String> order = SortPlayer.SortToList(players, 15);
        mes.setColor(Color.MAGENTA);
        mes.setTitle("Champs Played LeaderBoard");
        for(int i = 0; i < 12;i++){
            PlayerRow player = players.get(order.get(i));
            mes.addField((i+1) + ". " + order.get(i), getChampsInfo(player), true);
        }
        channel.sendMessageEmbeds(mes.build()).queue();
    }

    public static void sendFirstBloods(MessageChannel channel){
        EmbedBuilder mes = new EmbedBuilder();
        ArrayList<String> order = SortPlayer.SortToList(players, 17);
        mes.setColor(Color.MAGENTA);
        mes.setTitle("First Blood LeaderBoard");
        for(int i = 0; i < 12;i++){
            PlayerRow player = players.get(order.get(i));
            mes.addField((i+1) + ". " + order.get(i), getFirstBloodInfo(player), true);
        }
        channel.sendMessageEmbeds(mes.build()).queue();
    }

    public static void SendKDA(MessageChannel channel){
        EmbedBuilder mes = new EmbedBuilder();
        ArrayList<String> order = SortPlayer.SortToList(players, 8);
        mes.setColor(Color.MAGENTA);
        mes.setTitle("KDA LeaderBoard");
        for(int i = 0; i < 12;i++){
            PlayerRow player = players.get(order.get(i));
            mes.addField((i+1) + ". " + order.get(i), getKDAInfo(player), true);
        }
        channel.sendMessageEmbeds(mes.build()).queue();
    }

    public static void SendGP(MessageChannel channel){
        EmbedBuilder mes = new EmbedBuilder();
        ArrayList<String> order = SortPlayer.SortToList(players, 1);
        mes.setColor(Color.MAGENTA);
        mes.setTitle("Games Played LeaderBoard");
        for(int i = 0; i < 12;i++){
            PlayerRow player = players.get(order.get(i));
            mes.addField((i+1) + ". " + order.get(i), getKillInfo(player), true);
        }
        channel.sendMessageEmbeds(mes.build()).queue();
    }

    public static void sendPentas(MessageChannel channel){
        EmbedBuilder mes = new EmbedBuilder();
        mes.setColor(Color.MAGENTA);
        mes.setTitle("Penta LeaderBoard");
        mes.addField("Upset", "1", false);
        mes.addField("Shogun", "1", false);
        channel.sendMessageEmbeds(mes.build()).queue();
    }

    public static void sendStats(MessageChannel channel){
        EmbedBuilder mes = new EmbedBuilder();
        mes.setColor(Color.MAGENTA);
        mes.setTitle("Players Stats");
        ArrayList<String> temp = SortPlayer.SortToList(players, 8);
        mes.addField("KDA ", "> " + temp.get(0) + " - " + players.get(temp.get(0)).getKda(), false);
        temp = SortPlayer.SortToList(players, 15);
        mes.addField("Champs Played ","> " + temp.get(0) + " - " + players.get(temp.get(0)).getChampsPlayed().split(",").length, false);
        mes.addField("Pentas ", "> Upset\n> Shogun", false);
        temp = SortPlayer.SortToList(players, 17);
        mes.addField("First Bloods ", "> " + temp.get(0) + " - " + players.get(temp.get(0)).getFirstBlood(), false);
        mes.addField("Most Kill ", "> Shogun - 16", false);
        channel.sendMessageEmbeds(mes.build()).queue();
    }

    //Formaters
    private static String getKDAInfo(PlayerRow player) {
        MessageFormat fmt = new MessageFormat(
            """
             > KDA          - {0}
             > Games Played - {1} 
             > Total Deaths -  {2}    
            """
        );
        Object[] args = {player.getKda(), //0
                         player.getGamesPlayed(),  //1
                         player.getDeaths()}; //2
        return fmt.format(args);
    }

    private static String getFirstBloodInfo(PlayerRow player) {
        MessageFormat fmt = new MessageFormat(
            """
             > First Bloods  - {0}
             > Games Played - {1} 
             > Total Kills  -  {2}    
            """
        );
        Object[] args = {player.getFirstBlood(), //0
                         player.getGamesPlayed(),  //1
                         player.getKills()}; //2
        return fmt.format(args);
    }

    private static String getChampsInfo(PlayerRow player) {
        MessageFormat fmt = new MessageFormat(
            """
             > Champions Played  - {0}
             > Games Played      - {1} 
             > Champs 
             > \t {2}    
            """
        );
        Object[] args = {player.getChampsPlayed().split(",").length, //0
                         player.getGamesPlayed(),  //1
                         player.getChampsPlayed()}; //2
        return fmt.format(args);
    }

    private static String getDeathInfo(PlayerRow player) {
        MessageFormat fmt = new MessageFormat(
            """
             > Total Deaths  - {0}
             > Games Played  - {1} 
             > Win Rate      - {2}    
            """
        );
        Object[] args = {player.getDeaths(),  //0
                         player.getGamesPlayed(),  //1
                         player.getWinRate()}; //2
        return fmt.format(args);
    }

    private static String getKillInfo(PlayerRow player) {
        MessageFormat fmt = new MessageFormat(
            """
             > Total Kills   - {0}
             > Games Played  - {1} 
             > Win Rate      - {2}    
            """
        );
        Object[] args = {player.getKills(),  //0
                         player.getGamesPlayed(),  //1
                         player.getWinRate()}; //2
        return fmt.format(args);
    }

    public static void reset() {
        players = MakePlayer.getPlayers().get(2);
    }
    
}
