package bot.Scarper.EventScraper;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang.text.StrBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class EventScraper {

    private static Document doc1;
    private static Document doc2;

    public EventScraper(){
        String mainURL = "https://gol.gg/tournament/tournament-stats/World%20Championship%202022/";
        String playinURL = "https://gol.gg/tournament/tournament-stats/World%20Championship%20Play-In%202022/";
        try {
            doc1 = Jsoup.connect(mainURL).get();
            doc2 = Jsoup.connect(playinURL).get();
        } catch (Exception e){
            System.out.println("Error with retriving event data");
        }
    }

    public static void main(String[] args) throws IOException {
        doc1 = Jsoup.connect("https://gol.gg/tournament/tournament-stats/World%20Championship%202022/").get();
        System.out.println(getLongestTime());
    }

    public static String getLongestTime(){
        Element result = doc1.select(".p-4 ").first();
        if(result != null){
            Node node = result.childNode(1).childNode(1)
            .childNode(1).childNode(1)
            .childNode(5).childNode(9).childNode(1);
            return node.childNode(0).toString() + node.childNode(1).childNode(0);
        }
        return "0:00:FUCK ALL"; 
    }

    public static HashMap<String,Integer> getDrake(){
        Element result = doc1.select(".p-4 ").first();
        Node node = result
            .childNode(1).childNode(3)
            .childNode(5).childNode(1)
            .childNode(5).childNode(1)
            .childNode(0).childNode(1)
            .childNode(0);
        return parseDrakeData(node.toString());
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
