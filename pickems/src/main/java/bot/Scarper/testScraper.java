package bot.Scarper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONObject;

class dob {
    public String a;
    public String b;

    public dob(String A, String B){
        a = A;
        b = B;
    }

    @Override
    public String toString(){
        return "a: " + a + " b: " + b; 
    }
}

public class testScraper {

    public static void main(String[] args) throws IOException {
        InputStream is = new URL("https://www.rotowire.com/esports/tables/stats-lol.php?league=297&series=5065").openStream();
        try {
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
          String jsonText = readAll(rd);
          jsonText = jsonText.substring(1, jsonText.length() -1 );
          String[] players = jsonText.split("},");
          ArrayList<dob> list = new ArrayList<>();
          for(String player : players){
            JSONObject json = new JSONObject(player + "}");
            list.add(new dob(json.get("gamertag").toString(), json.get("fb").toString()));
          }
          System.out.println(list.get(105));
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
