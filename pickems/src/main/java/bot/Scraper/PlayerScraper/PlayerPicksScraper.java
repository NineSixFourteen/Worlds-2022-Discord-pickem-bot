package bot.Scraper.PlayerScraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PlayerPicksScraper {

    public static ArrayList<String> getGameData(int num) throws IOException {
        String[] URL = new String[]
        {
            "https://lol.fandom.com/wiki/2022_Season_World_Championship/Main_Event/Match_History",
            "https://lol.fandom.com/wiki/2022_Season_World_Championship/Play-In/Match_History"
        };
        Document doc = Jsoup.connect(URL[num]).get();
        Element result = doc.select(".wikitable ").first();
        ArrayList<String> ChampAndPlayer = new ArrayList<>();
        if(result == null){
            System.out.println("Failed To get Player Info");
            return new ArrayList<>();
        }
        for(int i = 3; i < result.childNode(0).childNodeSize();i++){
            String champs = "";
            boolean space = true;
            String players = "";
            for(Node node : result.childNode(0).childNode(i).childNode(7).childNodes()){
                champs += node.attributes().get("title") + ",";
            }
            for(Node node : result.childNode(0).childNode(i).childNode(8).childNodes()){
                champs += node.attributes().get("title") + ",";
            }
            for(Node node : result.childNode(0).childNode(i).childNode(9).childNodes()){
                if(space) {
                    players += beforeSpace(node.attributes().get("title").toLowerCase()) + ",";
                    space = false;
                } else {
                    space = true; 
                }
            }
            space = true;
            for(Node node : result.childNode(0).childNode(i).childNode(10).childNodes()){
                if(space) {
                    players += beforeSpace(node.attributes().get("title").toLowerCase()) + ",";
                    space = false;
                } else {
                    space = true; 
                }
            }
            String[] Players = players.split(",");
            String[] Champs = champs.split(",");
            for(int r = 0; r < 10;r++){
                ChampAndPlayer.add(Players[r] + ", " +Champs[r]);
            }
        }
        return ChampAndPlayer;
    } 

    public static HashMap<String,String> getPlayerPicks() throws IOException {
        HashMap<String,String> playerPicks = new HashMap<>();
        ArrayList<String> data = getGameData(1); // Champs and Players from play in
        data.addAll(getGameData(0));
        for(String info : data){
            String[] Info = info.split(",");
            if(playerPicks.containsKey(Info[0])){
                String cur = playerPicks.get(Info[0]);
                playerPicks.put(Info[0], cur + "," + Info[1]);
            } else {
                playerPicks.put(Info[0], Info[1]);
            }
        }
        makeUnique(playerPicks);
        return playerPicks;
    }

    //Helpers
    private static void makeUnique(HashMap<String, String> map) {
        for(String key : map.keySet()){
            String cur = map.get(key);
            String[] items = cur.split(",");
            Set<String> unique = new HashSet<>();
            for(String item : items){
                unique.add(item);
            }
            cur = "";
            for(String uniq : unique){
                cur += uniq + ",";
            }
            map.put(key, cur.substring(0,cur.length() -1)); // Re add element to map and remove the extra ',' from cur
        }
    }

    private static String beforeSpace(String s){
        int end = 0 ;
        while(end < s.length() && s.charAt(end) != ' '){
            end++;
        }
        return s.substring(0,end);
    }
    
}
