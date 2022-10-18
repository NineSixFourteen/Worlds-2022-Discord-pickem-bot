package bot.Bot.Listeners;

import javax.annotation.Nonnull;

import bot.Bot.MessageMakers.SendChampStats;
import bot.Bot.MessageMakers.SendEvent;
import bot.Bot.MessageMakers.SendPlayerStats;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ButtonListeners extends ListenerAdapter {

    @Override
    @SuppressWarnings("null")
    public void onButtonInteraction(@Nonnull ButtonInteractionEvent event) {
        MessageChannel x = event.getMessageChannel();
        switch(event.getButton().getId()){
            case "stats": SendChampStats.SendTop(x); break;
            case "pstas": SendPlayerStats.sendStats(x); break;
            case "event": SendEvent.SendEventData(x); break;
            case "kills": SendChampStats.SendKills(x); break;
            case "deaths": SendChampStats.SendDeaths(x); break;
            case "wr": SendChampStats.SendWR(x); break;
            case "picks": SendChampStats.SendPicks(x, "=picks"); break;
            case "bans": SendChampStats.SendPicks(x, "=bans"); break;
            case "pos": SendChampStats.SendPos(x); break;
            case "pkills": SendPlayerStats.sendKills(x); break;
            case "pdeaths": SendPlayerStats.sendDeaths(x); break;
            case "pkda": SendPlayerStats.SendKDA(x); break;
            case "pgp": SendPlayerStats.SendGP(x); break;
            case "pchamp": SendPlayerStats.sendChamps(x); break;
            case "fb":  SendPlayerStats.sendFirstBloods(x);break;
            case "penta": SendPlayerStats.sendPentas(x); break;
            case "unique": SendChampStats.SendTotal(x);; break;
            default: 
        }
        event.deferEdit().queue();

    }
    
}
