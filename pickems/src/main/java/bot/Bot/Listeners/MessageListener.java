package bot.Bot.Listeners;

import java.io.IOException;

import javax.annotation.Nonnull;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.Champ.ChampRow;
import bot.InfoStorage.QuerySystem.SortChamps;
import bot.Scraper.ChampScraper.AddChampRows;
import bot.Scraper.ChampScraper.MakeChamp;
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
                playin = MakeChamp.makeChampPlayInDB();
                DataBase<ChampRow> main = MakeChamp.makeChampMainDB();
                DataBase<ChampRow> add = AddChampRows.add(playin, main); 
                //add.display();
                String message = SortChamps.SortTM(add, "WR");
                event.getChannel().sendMessage(message).queue();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
    