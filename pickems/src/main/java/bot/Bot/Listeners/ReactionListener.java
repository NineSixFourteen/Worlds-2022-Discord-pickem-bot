package bot.Bot.Listeners;

import java.util.HashMap;

import javax.annotation.Nonnull;

import bot.Bot.MessageMakers.SendButtons;
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
            System.out.println(event.getReaction().toString());
            switch(event.getReaction().toString()){
                    case "MR:(M:(1032015791840841848) / RE:U+2b05U+fe0f)":
                    case "MR:(M:(1032013711621234761) / RE:U+2b05U+fe0f)": 
                        if(i > 0){
                            i--;
                        }
                        break;
                    case "MR:(M:(1032015791840841848) / RE:U+27a1U+fe0f)":
                    case "MR:(M:(1032013711621234761) / RE:U+27a1U+fe0f)": 
                        if(i < 2){
                            i++;
                        }
                }
                message.editMessage(messages[i]).queue();
                is.put(event.getMessageId(), i);
            }
        });
    }

    
}
