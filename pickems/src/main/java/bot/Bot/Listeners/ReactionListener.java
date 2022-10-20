package bot.Bot.Listeners;

import java.util.HashMap;

import javax.annotation.Nonnull;

import bot.Bot.MessageMakers.SendButtons;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter{


    private static Message[] messages = 
        new Message[]{
            SendButtons.getMessage1(),
            SendButtons.getMessage2(),
            SendButtons.getMessage3()
    };

    private static HashMap<String,Integer> is = new HashMap<>();

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {
        event.getChannel().retrieveMessageById(event.getMessageId())
        .queue((message) -> {
            if(message.getButtons().size() > 0 ){
            int i = 0;
            if(is.containsKey(event.getMessageId())){
                i = is.get(event.getMessageId());
            } 
            System.out.println(event.getReaction().toString().substring(32));
            switch(event.getReaction().toString().substring(32)){
                    case ":U+2b05U+fe0f)": 
                        System.out.println(i - 1);
                        if(i > 0){
                            i--;
                        }
                        break;
                    case ":U+27a1U+fe0f)":
                        System.out.println(i + 1);
                        if(i < 2){
                            System.out.println(i);
                            i++;
                            System.out.println(i + 1);
                        }
                }
                message.editMessage(messages[i]).queue();
                is.put(event.getMessageId(), i);
            }
        });
    }

    
}
