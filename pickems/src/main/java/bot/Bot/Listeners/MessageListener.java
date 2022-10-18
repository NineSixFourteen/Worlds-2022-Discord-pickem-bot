package bot.Bot.Listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

import bot.Bot.MessageMakers.SendChamps;
import bot.Bot.MessageMakers.SendEvent;
import bot.Bot.MessageMakers.SendPlayerStats;
import bot.Bot.MessageMakers.SendButtons;
import bot.Bot.MessageMakers.SendChampStats;

public class MessageListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if(event.getChannel().getName().equals("bot-cmds")){
            if(args.length == 0 || args[0].length() == 0 ||  args[0].charAt(0) != '='){
                return;
            }
            args[0] = args[0].toLowerCase();
            // Command List
            switch(args[0].toLowerCase()){
                case "=help":
                    String s = "";
                    if(args.length == 1){
                        s = """
                            ```Commands:
                                =commands : Displays buttons for commands
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
                                =unique - Displays number of unique champs
                            ```
                        """;
                    }else if(args[1].equals("champs")){
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
                    break;

                case "=champs":
                    SendChamps.SendChampsData(event,args);break;
                case "=commands":
                    event.getChannel().sendMessage(SendButtons.getMessage1()).queue();break;
                case "=stats":
                    SendChampStats.SendTop(event.getChannel());break;
                case "=event":
                    SendEvent.SendEventData(event.getChannel());break;
                case "=kills":
                    SendChampStats.SendKills(event.getChannel());break;
                case "=deaths":
                    SendChampStats.SendDeaths(event.getChannel());break;
                case "=pos":
                    SendChampStats.SendPos(event.getChannel());break;
                case "=wr":
                    SendChampStats.SendWR(event.getChannel());break;
                case "=picks":case "=bans":
                    SendChampStats.SendPicks(event.getChannel(),args[0]);break;
                case "=drakes":
                    SendEvent.SendDrakes(event.getChannel());break;
                case "=pkills":
                    SendPlayerStats.sendKills(event.getChannel());break;
                case "=pdeaths":
                    SendPlayerStats.sendDeaths(event.getChannel());break;
                case "=pkda":
                    SendPlayerStats.SendKDA(event.getChannel());break;
                case "=fb":
                    SendPlayerStats.sendFirstBloods(event.getChannel());break;
                case "=pchamps":
                    SendPlayerStats.sendChamps(event.getChannel());break;
                case "=pgp":
                    SendPlayerStats.SendGP(event.getChannel());break;
                case "=pentas":
                    SendPlayerStats.sendPentas(event.getChannel());break;
                case "=pstats":
                    SendPlayerStats.sendStats(event.getChannel());break;
                case "=unique":
                    SendChampStats.SendTotal(event.getChannel());break;
                case "=r":
                    SendChampStats.reset();
                    SendPlayerStats.reset();
                    SendEvent.reset();break;
                default:
                    event.getChannel().sendMessage("That is not a command btw").queue();;
            }
        }
    }
}