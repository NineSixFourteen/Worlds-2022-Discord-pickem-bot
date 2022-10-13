package bot.Scraper.EventScraper;

import java.util.HashMap;

import org.apache.commons.lang.text.StrBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class EventScraper {

    private static Document doc1;
    private static Document doc2;
    private static String mainURL = "https://gol.gg/tournament/tournament-stats/World%20Championship%202022/";
    private static String playinURL = "https://gol.gg/tournament/tournament-stats/World%20Championship%20Play-In%202022/";

    public EventScraper(){
        try {
            doc1 = Jsoup.connect(mainURL).get();
            doc2 = Jsoup.connect(playinURL).get();
        } catch (Exception e){
            System.out.println("Error with retriving event data");
        }
    }

    public String getLongestTime(int num){
        Element result;
        if(num == 0 ){
            result = doc1.select(".p-4 ").first();
        } else {
            result = doc2.select(".p-4 ").first();
        }
        if(result != null){
            Node node = result.childNode(1).childNode(1)
            .childNode(1).childNode(1)
            .childNode(5).childNode(9).childNode(1);
            return node.childNode(0).toString() + node.childNode(1).childNode(0);
        }
        return "0:00:FUCK ALL"; 
    }

    public String getMostKills(int num){
        Element result;
        if(num == 0 ){
            result = doc1.select(".p-4 ").first();
        } else {
            result = doc2.select(".p-4 ").first();
        }
        if(result != null){
            String player = result.childNode(1).childNode(5)
            .childNode(3).childNode(1).childNode(5)
            .childNode(1).childNode(0).childNode(2).childNode(0).toString();
            String kills = result.childNode(1).childNode(5)
            .childNode(3).childNode(1).childNode(5)
            .childNode(1).childNode(2).childNode(0).toString();
            kills = kills.substring(0, kills.length() - 1);
            return player + ": " + kills;
        }
        return "0:00:FUCK ALL"; 
    }

    public HashMap<String,Integer> getDrake(int num){
        Element result;
        if(num == 0 ){
            result = doc1.select(".p-4 ").first();
        } else {
            result = doc2.select(".p-4 ").first();
        }
        if(result != null){
            Node node = result
                .childNode(1).childNode(3)
                .childNode(5).childNode(1)
                .childNode(5).childNode(1)
                .childNode(0).childNode(1)
                .childNode(0);
            return parseDrakeData(node.toString());
        }
        return new HashMap<>();
    }

    private static HashMap<String, Integer> parseDrakeData(String drakeData) {
        StrBuilder sb = new StrBuilder(drakeData);
        StrBuilder labels = new StrBuilder();
        StrBuilder drakeNums = new StrBuilder();
        while(!sb.startsWith("labels:")){
            sb.deleteCharAt(0);
        }
        for(int i =0;i<9;i++){
            sb.deleteCharAt(0);
        }
        while(sb.charAt(0) != ']'){
            labels.append(sb.charAt(0));
            sb.deleteCharAt(0);
        }
        while(!sb.startsWith("data:")){
            sb.deleteCharAt(0);
        }
        for(int i =0;i<6;i++){
            sb.deleteCharAt(0);
        }
        while(sb.charAt(0) != ']'){
            drakeNums.append(sb.charAt(0));
            sb.deleteCharAt(0);
        }
        String[] drakes = labels.toString().split(",");
        String[] drakesN = drakeNums.toString().split(",");
        HashMap<String,Integer> drakeInfo = new HashMap<>();
        for(int i = 0; i < drakes.length;i++){
            drakeInfo.put(drakes[i], Integer.parseInt(drakesN[i]));
        }
        return drakeInfo;
    }
    
}
