package bot.Bot.Listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

import bot.Bot.MessageMakers.SendChamps;
import bot.Bot.MessageMakers.SendEvent;
import bot.Bot.MessageMakers.SendPlayerStats;
import bot.Bot.MessageMakers.SendChampStats;

public class MessageListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        for(String arg : args){
            System.out.println(arg);
        }
        args[0] = args[0].toLowerCase();
        // Command List
        if (args[0].equals("=help")) {
            String s = "";
            if(args.length == 1){
                s = """
                    ```Commands:
                        =help champs : Displays help for using =champs
                        =champs - Displays a list of all champs and their stats
                        =stats - Displays stats for champions
                        =pstats - Displays stats for players
                        =event - Displays stats for Worlds 2022
                        =kills - Displays a list of highest kills
                        =deaths - Displays a list of highest deaths
                        =wr - Displays a list of highest winrates with > 5 games 
                        =picks - Displays a list of most picked
                        =bans - Displays a list of most banned
                        =pos - Displays a list of postions played 
                        =pkills - Displays player kills
                        =pdeaths - Displays player deaths
                        =pkda - Displays player kda
                        =pgp  - Displays games played
                        =pchamp - Displays champs players played
                        =fb - Displays players with fb
                        =pentas - Displays players with pentas
                    ```
                """;
            } else if(args[1].equals("champs")){
                s = 
                """
                ```
                Champ Commands:
                    Sort: Takes a fields and sorts them by 
                    Example: Sort-Wins, Sort-GP 
                    Size: Takes a number and returns that many elements 
                    Example: Size-12 returns 12 results
                    Show: Takes a list of numbers and only shows those fields
                    Example: Show-1,2,3,4 
                    Filter: Takes a Field, An Operation i.e > or == and a number 
                    Example: Filter-GP,>,4
                    These can can be used at the same time so you can write  
                    Example: =champs size-5 sort-wr show-1,3,2,7,8 filter-gp,>,10
                    r: Resets the database undoing all filters and updating data
                    SortL: Shows list of fields and there usable names 
                    ShowL: Shows list of fields and there corresponding numb```
                """;
            }
            event.getChannel().sendMessage(s).queue();
        }

        // Champ picks
        if (args[0].equals(("=champs"))) { 
            SendChamps.SendChampsData(event,args);
        }
        if(args[0].equals("=stats")){
            SendChampStats.SendTop(event.getChannel());
        }

        if(args[0].equals(("=event"))){
            SendEvent.SendEventData(event, args);
        }

        if(args[0].equals(("=kills"))) {
            SendChampStats.SendKills(event.getChannel());
        }
        if(args[0].equals(("=deaths"))) {
            SendChampStats.SendDeaths(event.getChannel());
        }
        if(args[0].equals(("=pos"))) {
            SendChampStats.SendPos(event.getChannel());
        }
        if(args[0].equals("=wr")){
            SendChampStats.SendWR(event.getChannel());
        }
        if(args[0].equals("=picks") || args[0].equals("=bans")){
            SendChampStats.SendPicks(event.getChannel(),args[0]);
        }
        if(args[0].equals("=drakes")){
            SendEvent.SendDrakes(event.getChannel());
        }
        if(args[0].equals("=pkills")){
            SendPlayerStats.sendKills(event.getChannel());
        }
        if(args[0].equals("=pdeaths")){
            SendPlayerStats.sendDeaths(event.getChannel());
        }
        if(args[0].equals("=pkda")){
            SendPlayerStats.SendKDA(event.getChannel());
        }
        if(args[0].equals("=fb")){
            SendPlayerStats.sendFirstBloods(event.getChannel());
        };
        if(args[0].equals("=pchamps")){
            SendPlayerStats.sendChamps(event.getChannel());
        }   
        if(args[0].equals("=pgp")){
            SendPlayerStats.SendGP(event.getChannel());
        }
        if(args[0].equals("=pentas")){
            SendPlayerStats.sendPentas(event.getChannel());
        }
        if(args[0].equals("=pstats")){
            SendPlayerStats.sendStats(event.getChannel());
        }

    }
}