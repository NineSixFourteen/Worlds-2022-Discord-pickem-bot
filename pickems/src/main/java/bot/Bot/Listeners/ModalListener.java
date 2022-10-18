package bot.Bot.Listeners;

import javax.annotation.Nonnull;

import bot.Bot.MessageMakers.SendChamps;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ModalListener extends ListenerAdapter {

    @Override
    @SuppressWarnings("null")
    public void onModalInteraction(@Nonnull ModalInteractionEvent event) {
        if(event.getModalId().equals("bot-modal")){
            String size = event.getValue("size").getAsString();
            String sort = event.getValue("sort").getAsString();
            String show = event.getValue("show").getAsString();
            String query = event.getValue("query").getAsString();
            String get = event.getValue("get").getAsString();
            SendChamps.SendChampData(event.getMessageChannel(), new String[]{size,sort,show,get,query});
        }
        event.deferEdit().queue();
    }
    
}
