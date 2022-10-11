package bot.Bot.Listeners;

import javax.annotation.Nonnull;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event)
    {
        System.out.println(event.getMessage().getContentDisplay());
        if(event.getMessage().getContentDisplay().equals("Marco")){
            System.out.println("Heelo");
            event.getChannel().sendMessage("Pollo").queue();
        }
    }
}
    