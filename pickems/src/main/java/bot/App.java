package bot;

import java.io.IOException;

import bot.Scraper.EventScraper.makeEvent;
import bot.Scraper.PlayerScraper.MakePlayer;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        /* 
        String look = "k";
        TextTable tt = new TextTable(
           new String[]{"player",look},
           SortChamps.SortTL(MakeChamp.makeChampDB() ,"k")
           );
           tt.printTable();                                                    
           */
        //System.out.println("Ming: " + MakePlayer.getPlayers().get(2).get("Ming").toString());
        System.out.println(makeEvent.getMatchData(3));
    }
}
