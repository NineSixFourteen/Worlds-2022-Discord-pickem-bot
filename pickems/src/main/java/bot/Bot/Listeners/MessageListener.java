package bot.Bot.Listeners;

import java.io.IOException;

import javax.annotation.Nonnull;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.Champ.ChampRow;
import bot.InfoStorage.QuerySystem.SortChamps;
import bot.Scarper.ChampScraper.addChampRows;
import bot.Scarper.ChampScraper.makeChamp;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event)
    {
        System.out.println(event.getMessage().getContentDisplay());
        if(event.getMessage().getContentDisplay().equals("!D")){
            DataBase<ChampRow> playin;
            try {
                playin = makeChamp.makeChampPlayInDB();
                DataBase<ChampRow> main = makeChamp.makeChampMainDB();
                DataBase<ChampRow> add = addChampRows.add(playin, main); 
                //add.display();
                String message = SortChamps.SortTM(add, "WR");
                event.getChannel().sendMessage(message).queue();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
    