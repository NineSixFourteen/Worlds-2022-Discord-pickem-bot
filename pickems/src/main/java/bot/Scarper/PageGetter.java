package bot.Scarper;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang.text.StrBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PageGetter {

    public static ArrayList<String> getPlayerData(String URL) throws IOException{
        Document doc = Jsoup.connect(URL).get();
        Element result = doc.select(".wikitable ").first();
        String content ="";
        if(result != null){
            content = result.text();
        }
        StrBuilder build = new StrBuilder(content);
        while((int) build.charAt(0) != 8288){
            build.deleteCharAt(0);
        }
        build.delete(0, 3);
        ArrayList<String> rows = getRows(content);
        return rows;
    }

    private static ArrayList<String> getRows(String substring) {
        ArrayList<String> rows = new ArrayList<>();
        StrBuilder build = new StrBuilder(substring);
        while(!build.isEmpty()){
            int ind = 0; 
            while((int) build.charAt(ind) != 8288){
                ind++;
                if(ind >= build.size()){
                    rows.add(build.substring(0,ind));
                    return rows;
                }
            }
            rows.add(build.substring(0,ind));
            build.delete(0, ind + 2);
        }
        return rows;
    }
}
