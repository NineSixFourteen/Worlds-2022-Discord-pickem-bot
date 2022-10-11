package bot.Scarper;

import java.io.IOException;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.Row;

public class testScraper {

    public static void main(String[] args) throws IOException {
        //DataBase db = makeChamp.makeChampDB();
        DataBase db = makePlayer.makePlayerDB();
        for(Row row : db.getRows()){
            row.display();
        }
    }
    
}
