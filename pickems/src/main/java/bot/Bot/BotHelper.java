package bot.Bot;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class BotHelper {

    public static int contains(String[] keywords, String[] args){
        for(int i = 0; i < args.length;i++){
            for(String keyword : keywords){
                if(args[i].contains("-") && keyword.toLowerCase().equals(args[i].split("-")[0].toLowerCase())){
                    return i;
                }
            }
        }
        return -1;
    }

    public static void sendMessages(ArrayList<MessageEmbed> messages ,MessageChannel channel){
        for(MessageEmbed message : messages){
            if(message != null){
                channel.sendMessageEmbeds(message).queue();
            }
        } 
    }

    public static void sendMessagesDelay(ArrayList<MessageEmbed> messages, MessageChannel channel, int i) {
        for(MessageEmbed message : messages){
            if(message != null){
                channel.sendMessageEmbeds(message).queueAfter(30, TimeUnit.SECONDS);
            }
        } 
    }
    
}
