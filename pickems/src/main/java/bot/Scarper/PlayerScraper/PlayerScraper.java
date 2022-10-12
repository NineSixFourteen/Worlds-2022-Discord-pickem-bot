package bot.Scarper.PlayerScraper;

import org.apache.commons.lang.text.StrBuilder;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;


public class PlayerScraper {

    public static HashMap<String,String> getPlayerPicks() throws IOException {
        Document doc = Jsoup.connect("https://lol.fandom.com/wiki/2022_Season_World_Championship/Main_Event/Player_Statistics").get();
        Element result = doc.select(".wikitable ").first();
        HashMap<String,String> playerPicks = new HashMap<>();
        if(result != null){
            Node res = result.childNode(0);
            for(int i = 4; i < res.childNodeSize();i++){
                String champs = "";
                for(Node node : res.childNode(i).childNode(18).childNodes()){
                    if(node.childNode(0).attributes().get("title") != null){
                        champs += node.childNode(0).attributes().get("title") + ",";
                    } else {
                        System.out.println("CRINGE NO TITLE");
                    }
                }
                champs = champs.substring(0, champs.length() - 1);
                playerPicks.put(
                    res.childNode(i).childNode(1).childNode(0).childNode(0).outerHtml() , 
                    champs
                );
            }
        } else {
            System.out.println("Faild to get Player champion picks as result was null");
        }
        return playerPicks;
    }

    public static HashMap<String,String> getPlayerPicksPL() throws IOException {
        Document doc = Jsoup.connect("https://lol.fandom.com/wiki/2022_Season_World_Championship/Play-In/Player_Statistics").get();
        Element result = doc.select(".wikitable ").first();
        HashMap<String,String> playerPicks = new HashMap<>();
        if(result != null){
            Node res = result.childNode(0);
            for(int i = 4; i < res.childNodeSize();i++){
                String champs = "";
                for(Node node : res.childNode(i).childNode(18).childNodes()){
                    if(node.childNode(0).attributes().get("title") != null){
                        champs += node.childNode(0).attributes().get("title") + ",";
                    } else {
                        System.out.println("CRINGE NO TITLE");
                    }
                }
                champs = champs.substring(0, champs.length() - 1);
                playerPicks.put(
                    res.childNode(i).childNode(1).childNode(0).childNode(0).outerHtml() , 
                    champs
                );
            }
        } else {
            System.out.println("Faild to get Player champion picks as result was null");
        }
        return playerPicks;
    }

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

    public static HashMap<String, playerInfo> getPlayerInfo() throws IOException {
        InputStream is = new URL("https://www.rotowire.com/esports/tables/stats-lol.php?league=297&series=5065").openStream();
        try {
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
          String jsonText = readAll(rd);
          jsonText = jsonText.substring(1, jsonText.length() -1 );
          String[] players = jsonText.split("},");
          HashMap<String, playerInfo> pInfo = new HashMap<>();
          for(String player : players){
            JSONObject json = new JSONObject(player + "}");
            pInfo.put(
                json.get("gamertag").toString().toLowerCase(),
                new playerInfo(
                    Integer.parseInt(json.get("fb").toString()),
                    json.get("team").toString())
                );
          }
          //Players Missing in the database that can change
          pInfo.put("kati",new playerInfo(0, "GAM"));
          pInfo.put("shunn",new playerInfo(0, "CTBC Flying Oyster"));
          pInfo.put("kaori",new playerInfo(0, "Evil Geniuses"));
          pInfo.put("sty1e",new playerInfo(0, "GAM"));
          pInfo.put("bie", new playerInfo(0, "GAM"));
          //Players Missing in the database that can't change
          pInfo.put("hasmed",new playerInfo(0, "Saigon Buffalo"));
          pInfo.put("beanj",new playerInfo(0, "Saigon Buffalo"));
          pInfo.put("shogun", new playerInfo(0, "Saigon Buffalo"));
          pInfo.put("taki", new playerInfo(0, "Saigon Buffalo"));
          pInfo.put("froggy",new playerInfo(0, "Saigon Buffalo"));
          pInfo.put("brance",new playerInfo(0, "Loud"));
          pInfo.put("ceos",new playerInfo(0, "Loud"));
          pInfo.put("croc",new playerInfo(0, "Loud"));
          pInfo.put("gavotto",new playerInfo(0, "Isurus Gaming"));
          pInfo.put("grell",new playerInfo(0, "Isurus Gaming"));
          pInfo.put("gavotto",new playerInfo(0, "Isurus Gaming"));
          pInfo.put("likai",new playerInfo(0, "Beyond Gaming"));
          pInfo.put("minji",new playerInfo(0, "Beyond Gaming"));
          pInfo.put("rhuckz",new playerInfo(0, "Fnatic"));
          return pInfo;
        } finally {
          is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
      }
}
