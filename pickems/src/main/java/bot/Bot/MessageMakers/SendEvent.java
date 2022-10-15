package bot.Bot.MessageMakers;

import java.awt.Color;
import java.util.ArrayList;

import bot.Bot.BotHelper;
import bot.InfoStorage.Event.EventData;
import bot.Scraper.EventScraper.MakeEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SendEvent {

    private static EventData ED = MakeEvent.getMatchData(-1);

    public static void SendEventData(MessageReceivedEvent event, String[] args){
        EmbedBuilder mes = new EmbedBuilder();
        mes.setColor(Color.MAGENTA);
        mes.setTitle("Event Information");
        for(int i = 0; i < 12;i++){
            mes.addField(ED.getTitle(i), ED.getValue(i),true);
        } 
        ArrayList<MessageEmbed> mess = new ArrayList<>();
        mess.add(mes.build());
        BotHelper.sendMessages(mess, event.getChannel());
    }

    public static void SendDrakes(MessageChannel channel){
        EmbedBuilder mes = new EmbedBuilder();
        mes.setColor(Color.MAGENTA);
        mes.setTitle("Drakes");
        for(String key : ED.getDrakes().keySet()){
            mes.addField(key.replace("'",""), ED.getDrakes().get(key) + "",false);
        } 
        ArrayList<MessageEmbed> mess = new ArrayList<>();
        mess.add(mes.build());
        BotHelper.sendMessages(mess, channel);
    }
    
}
