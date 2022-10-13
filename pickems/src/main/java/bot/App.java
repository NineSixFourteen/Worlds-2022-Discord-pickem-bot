package bot;

import java.io.IOException;



import bot.InfoStorage.QuerySystem.SortChamps;
import bot.InfoStorage.QuerySystem.SortPlayer;
import bot.Scarper.ChampScraper.MakeChamp;
import bot.Scarper.PlayerScraper.MakePlayer;
import bot.Scarper.PlayerScraper.PlayerScraper;
import dnl.utils.text.table.TextTable;

/**
 * Hello world!
 *
 */
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
        System.out.println("Ming: " + PlayerScraper.getPlayers().get(2).get("Ming").toString());
    }
}
