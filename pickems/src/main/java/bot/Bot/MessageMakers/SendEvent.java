package bot.Bot.MessageMakers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import bot.Bot.BotHelper;
import bot.InfoStorage.Pair;
import bot.InfoStorage.Event.EventData;
import bot.Scraper.EventScraper.MakeEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class SendEvent {

    private static EventData ED = MakeEvent.getMatchData(-1);

    public static void SendEventData(MessageChannel channel){
        EmbedBuilder mes = new EmbedBuilder();
        mes.setColor(Color.MAGENTA);
        mes.setTitle("Event Information");
        for(int i = 0; i < 12;i++){
            mes.addField(ED.getTitle(i), ED.getValue(i),true);
        } 
        ArrayList<MessageEmbed> mess = new ArrayList<>();
        mess.add(mes.build());
        BotHelper.sendMessages(mess, channel);
    }

    public static void SendDrakes(MessageChannel channel){
        EmbedBuilder mes = new EmbedBuilder();
        mes.setColor(Color.MAGENTA);
        mes.setTitle("Drakes");
        HashMap<String,Integer> drakes = ED.getDrakes();
        ArrayList<Pair> pairs = new ArrayList<>();
        for(String key: drakes.keySet()){
            pairs.add(new Pair(key, drakes.get(key)));
        }
        Collections.sort(pairs);
        for(Pair pair : pairs){
            mes.addField(pair.getID().replace("'",""),Math.round(pair.getValue()) + "",false);
        } 
        ArrayList<MessageEmbed> mess = new ArrayList<>();
        mess.add(mes.build());
        BotHelper.sendMessages(mess, channel);
    }

    public static void reset(){
        ED = MakeEvent.getMatchData(-1);
    }
    
}
