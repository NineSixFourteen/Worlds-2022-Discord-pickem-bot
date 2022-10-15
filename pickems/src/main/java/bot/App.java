package bot;

import bot.InfoStorage.Champ.FilterChamp;
import bot.InfoStorage.Query.Comparisson;
import bot.Scraper.ChampScraper.MakeChamp;

public class App {

    public static void main(String[] args) {
        FilterChamp.filterChampsNum(
            "gp", MakeChamp.makeChampDB(), 
            5,
            Comparisson.GreaterThan
        ).display();
    }
}
