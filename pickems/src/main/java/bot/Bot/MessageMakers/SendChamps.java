package bot.Bot.MessageMakers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import bot.Bot.BotHelper;
import bot.InfoStorage.Converters;
import bot.InfoStorage.DataBase;
import bot.InfoStorage.Champ.ChampRow;
import bot.InfoStorage.Champ.FilterChamp;
import bot.InfoStorage.Champ.SortChamps;
import bot.InfoStorage.Query.QueryHelper;
import bot.Scraper.ChampScraper.MakeChamp;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SendChamps {

    private static DataBase<ChampRow> db = MakeChamp.makeChampDB();

    public static void SendChampsData(MessageReceivedEvent event, String[] args){
        if(args.length == 2 && args[1].equals("r")){
            db = MakeChamp.makeChampDB();
        } else {
            int size = BotHelper.contains(new String[]{"Size"}, args);
            int sort = BotHelper.contains(new String[]{"Sort"}, args);
            int show = BotHelper.contains(new String[]{"Show","Fields","Headings"}, args); 
            int query = BotHelper.contains(new String[]{"Filter","Query"}, args); 
            int get = BotHelper.contains(new String[]{"Get", "Fetch"}, args);
            ArrayList<String> order = new ArrayList<>(); // no list means default order which is alphabetical 
            int max = 42; // default size 
            int[] nums = new int[]{7,8,10,6,3,2,1,18}; // default fields
            if(size != -1){
                String mes = args[size].split("-")[1];// Get the message after the = i.e. sort=k would return k
                max = mes.equals("F&L") ? 200 : Integer.parseInt(mes); 
            } 
            if(sort != -1){
                order = SortChamps.SortToList(db, args[sort].split("-")[1]); 
            }
            if(show != -1){
                String list = args[show].split("-")[1]; 
                String[] items = list.split(",");
                nums = new int[items.length];
                int i = 0; 
                for(String item: items){
                    nums[i++] = Integer.parseInt(item.replace(" ", ""));
                }
            }
            if(get != -1){
                String name = args[get].split("-")[1]; 
                String Cname = name.toUpperCase();
                name = Cname.charAt(0) + name.toLowerCase().substring(1);
                System.out.println(name);
                ChampRow row = db.get(name);
                String message = displayRow(row, nums);
                EmbedBuilder em = new EmbedBuilder();
                em.setColor(Color.MAGENTA);
                em.addField(name,message,false);
                if(message != null){
                    event.getChannel().sendMessageEmbeds(em.build()).queue();
                }
                return;
            }
            if(query != -1){
                String list = args[query].split("-")[1]; 
                String[] items = list.split(",");
                db = FilterChamp.filterChampsNum(
                    items[0], db, 
                    Integer.parseInt(items[2]),
                    QueryHelper.get(items[1])
                );
                if(size != -1 || sort != -1 || show != -1){
                    args[query] = "";
                    String message = String.join(" ", args);
                    if(message != null ){
                        event.getChannel().sendMessage(message).queueAfter(2, TimeUnit.SECONDS);
                    }
    
                }
            } else {
                BotHelper.sendMessages(
                    getChamps(
                        order,
                        max,
                        nums,db
                ),event.getChannel());
            }
        }
    }
    private static ArrayList<MessageEmbed> getChamps(ArrayList<String> champOrder, int max,int[] show,DataBase<ChampRow> data) {
        //If no list given use default order
        if(champOrder.size() == 0){
            for(String key : db.getRows().keySet()){
                champOrder.add(key);
            }
        }
        //Initilise Messages
        EmbedBuilder em1 = new EmbedBuilder();
        em1.setTitle("Champion Stats");
        em1.setColor(Color.MAGENTA);
        EmbedBuilder em2 = new EmbedBuilder();
        em2.setTitle("Champion Stats - 2");
        em2.setColor(Color.MAGENTA);
        EmbedBuilder em3 = new EmbedBuilder();
        em3.setTitle("Champion Stats - 3");
        em3.setColor(Color.MAGENTA);
        EmbedBuilder em4 = new EmbedBuilder();
        em4.setTitle("Champion Stats - 4");
        em4.setColor(Color.MAGENTA);
        EmbedBuilder em5 = new EmbedBuilder();
        em5.setTitle("Champion Stats - 5");
        em5.setColor(Color.MAGENTA);
        //Counter to track what row we are on
        int i = 0 ;
        for(String key : champOrder){
            if(i >= max ){ 
                break;
            }else if( i < 20){
                em1.addField((i+1) + "." + key,displayRow(data.get(key),show),true);
                i++;
            } else if(i < 41){
                em2.addField((i+1) + "." + key,displayRow(data.get(key),show),true);
                i++;
            }else if(i < 62){
                em3.addField((i+1) + "." + key,displayRow(data.get(key),show),true);
                i++;
            }else if(i < 83){
                em4.addField((i+1) + "." + key,displayRow(data.get(key),show),true);
                i++; 
            } else if (i < 104){
                em5.addField((i+1) + "." + key,displayRow(data.get(key),show),true);
                i++;
            }
        }
        ArrayList<MessageEmbed> messages = new ArrayList<>();
        messages.add(em1.build());
        //Add message if it has any rows added
        if(i > 20 && max !=200) messages.add(em2.build()); 
        if(i > 41 && max !=200) messages.add(em3.build()); 
        if(i > 62 && max !=200) messages.add(em4.build());
        if(i > 83) messages.add(em5.build());
        return messages;
    }

    private static String displayRow(ChampRow cr,int[] show){
        String ret = "";
        for(int field : show){
            ret += getField(cr,field); 
        }
        return ret;
    }

    private static String getField(ChampRow cr, int field) {
        switch(field){
            case 1:  return "> Presence        - " + cr.getPresence()     + "\n";
            case 2:  return "> Bans            - " + cr.getBaned()        + "\n";
            case 3:  return "> Games Played    - " + cr.getGamesPlayed()  + "\n";
            case 4:  return "> Games Won       - " + cr.getWins()         + "\n";
            case 5:  return "> Games Lost      - " + cr.getLoses()        + "\n";
            case 6:  return "> Win Rate        - " + cr.getWinRate()      + "\n";
            case 7:  return "> Average Kills   - " + Converters.toOnePlace(cr.getAvgKills())  + "\n"; 
            case 8:  return "> Average Deaths  - " + Converters.toOnePlace(cr.getAvgDeaths()) + "\n";
            case 9:  return "> Average Assists - " + Converters.toOnePlace(cr.getAvgAssists()) + "\n";
            case 10: return "> KDA             - " + Converters.toOnePlace(cr.getKda())       + "\n";
            case 11: return "> Average CS      - " + cr.getAvgCs()        + "\n";
            case 12: return "> CS per minute   - " + cr.getCspm()         + "\n";
            case 13: return "> Gold            - " + cr.getGold()         + "\n";
            case 14: return "> Gold per minute - " + cr.getGPM()          + "\n";
            case 15: return "> Kill Partic..   - " + cr.getKPar()         + "\n";
            case 16: return "> Kill Share      - " + cr.getKPar()         + "\n";
            case 17: return "> Gold Share      - " + cr.getKShare()       + "\n";
            case 18: return "> Postions Played - " + cr.getPosPlayed().split(", ").length + "\n";
            default: return ">Not a feild  - " + "there is 18 fields";
        }
    }
    
}
