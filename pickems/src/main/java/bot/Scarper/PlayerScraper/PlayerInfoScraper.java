package bot.Scarper.PlayerScraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.io.InputStreamReader;

import org.json.JSONObject;

public class PlayerInfoScraper {

    public static HashMap<String, PlayerInfo> getPlayerInfo() throws IOException {
        //URL is data dump used by stat website /
        InputStream is = new URL("https://www.rotowire.com/esports/tables/stats-lol.php?league=297&series=5065").openStream();
        try {
          //Read in JSON Data from site
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
          String jsonText = readAll(rd);
          jsonText = jsonText.substring(1, jsonText.length() -1 ); // Remove outer {}
          String[] players = jsonText.split("},"); // Split into an array of all players data
          HashMap<String, PlayerInfo> pInfo = new HashMap<>();
          for(String player : players){
            JSONObject json = new JSONObject(player + "}"); // Have to add the } that was removed during the split("},") 
            pInfo.put(
                json.get("gamertag").toString().toLowerCase(),
                new PlayerInfo(
                    Integer.parseInt(json.get("fb").toString()),
                    json.get("player").toString(),
                    json.get("team").toString(),
                    json.get("role").toString())
                );
          }
          //Adding players not in the datadump TODO add names
          //Players Missing in the database that can change
          pInfo.put("kati",new PlayerInfo(0,"", "GAM","MID"));
          pInfo.put("shunn",new PlayerInfo(0, "","CTBC Flying Oyster","ADC"));
          pInfo.put("kaori",new PlayerInfo(0, "","Evil Geniuses","ADC"));
          pInfo.put("sty1e",new PlayerInfo(0, "","GAM","ADC"));
          pInfo.put("bie", new PlayerInfo(0, "","GAM","SUP"));
          //Players Missing in the database that can't change
          pInfo.put("hasmed",new PlayerInfo(0, "","Saigon Buffalo","TOP"));
          pInfo.put("beanj",new PlayerInfo(0, "","Saigon Buffalo","JNG"));
          pInfo.put("shogun", new PlayerInfo(0, "","Saigon Buffalo","ADC"));
          pInfo.put("taki", new PlayerInfo(0, "","Saigon Buffalo","SUP"));
          pInfo.put("froggy",new PlayerInfo(0, "","Saigon Buffalo","MID"));
          pInfo.put("brance",new PlayerInfo(0, "","Loud","ADC"));
          pInfo.put("ceos",new PlayerInfo(0, "","Loud","SUP"));
          pInfo.put("croc",new PlayerInfo(0, "","Loud","CROC"));
          pInfo.put("gavotto",new PlayerInfo(0, "","Isurus Gaming","BOT"));
          pInfo.put("grell",new PlayerInfo(0, "","Isurus Gaming","JNG"));
          pInfo.put("likai",new PlayerInfo(0, "","Beyond Gaming","TOP"));
          pInfo.put("minji",new PlayerInfo(0, "","Beyond Gaming","MID"));
          pInfo.put("rhuckz",new PlayerInfo(0, "","Fnatic","SUP"));
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
