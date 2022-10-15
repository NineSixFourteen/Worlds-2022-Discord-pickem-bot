package bot.Scraper.ChampScraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.Champ.AddChampRows;
import bot.InfoStorage.Champ.ChampRow;
import bot.InfoStorage.Champ.ChampRowBuilder;

public class MakeChamp {

    public static ArrayList<DataBase<ChampRow>> makeChamps(){
        try{
            ArrayList<DataBase<ChampRow>> dbs = new ArrayList<>();
            DataBase<ChampRow> playin = makeChampPlayInDB();
            DataBase<ChampRow> main = makeChampMainDB();
            dbs.add(playin);
            dbs.add(main);
            dbs.add(AddChampRows.add(playin, main));
            return dbs;
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    public static DataBase<ChampRow> makeChampDB(){
        try{ 
            return AddChampRows.add(makeChampMainDB(), makeChampPlayInDB());
        } catch(Exception e){
            return new DataBase<>();
        }
    }

    public static DataBase<ChampRow> makeChampMainDB() throws IOException{
        DataBase<ChampRow> db = new DataBase<>();
        String URL = "https://lol.fandom.com/wiki/2022_Season_World_Championship/Main_Event/Champion_Statistics";
        Document doc = Jsoup.connect(URL).get();
        Element result = doc.select(".wikitable ").first();
        ArrayList<ArrayList<String>> champsStats = new ArrayList<>(); 
        if(result != null){
            for(int i = 4; i < result.childNode(0).childNodeSize();i++){
                champsStats.add(getChamp(result, i));
            }
        }
        for(ArrayList<String> champ : champsStats){
            String name = champ.remove(0);
            db.addRow(name, makeRow(champ));
        }
        return db;
    }

    public static DataBase<ChampRow> makeChampPlayInDB() throws IOException{
        DataBase<ChampRow> db = new DataBase<>();
        String URL = "https://lol.fandom.com/wiki/2022_Season_World_Championship/Play-In/Champion_Statistics";
        Document doc = Jsoup.connect(URL).get();
        Element result = doc.select(".wikitable ").first();
        ArrayList<ArrayList<String>> champsStats = new ArrayList<>(); 
        if(result != null){
            for(int i = 4; i < result.childNode(0).childNodeSize();i++){
                champsStats.add(getChamp(result, i));
            }
        }
        for(ArrayList<String> champ : champsStats){
            String name = champ.remove(0);
            db.addRow(name, makeRow(champ));
        }
        return db;
    }

    public static ArrayList<String> getChamp(Element result, int champID){
        ArrayList<String> info = new ArrayList<>();
        List<Node> nodes = result.childNode(0).childNode(champID).childNodes();
        info.add(result.childNode(0).childNode(champID).childNode(0).childNode(0).childNode(0).attributes().get("title"));
        addElem(info, 1,4, nodes);
        try{
            info.add(nodes.get(4).childNode(0).childNode(0).toString());
        } catch(Exception e){
            info.add("-");
        }
        try{
            info.add(nodes.get(5).childNode(0).childNode(0).toString());
        } catch(Exception e){
            info.add("-");
        }
        addElem(info, 6, 15, nodes);
        try{
            info.add(nodes.get(15).childNode(0).childNode(0).toString() + "k");
        } catch(Exception e){
            info.add("-");
        }
        try{
            info.add(nodes.get(16).childNode(0).toString());
        } catch(Exception e){
            info.add("-");
        }
        addElem(info, 17, 20, nodes);
        String pos = " "; 
        for(Node node : nodes.get(20).childNodes()){
            try{
                pos += node.childNode(0).attributes().get("title") + ", ";
            } catch(Exception e){}
        }
        info.add(pos.substring(0, pos.length() - 1));
        return info;
    }
    
    public static void addElem(ArrayList<String> info, int start, int end, List<Node> nodes){       
        while(start < end){
            info.add(nodes.get(start++).childNode(0).toString());
        } 
    } 

    private static ChampRow makeRow(ArrayList<String> info) {
        int i = 1;
        ChampRowBuilder builder = new ChampRowBuilder();
         builder
         .addPresence( info.get(i++) )
         .addBans( parseInt(info.get(i++)) )
         .addGamesPlayed( parseInt(info.get(i++)) );i++;
        builder
         .addWins( parseInt(info.get(i++)) ) 
         .addLoses( parseInt(info.get(i++)) )
         .addWinRate( info.get(i++) )
         .addKills( parseFloat(info.get(i++)) )
         .addDeaths( parseFloat(info.get(i++)) )
         .addAssists( parseFloat(info.get(i++)) )
         .addKda( parseFloat(info.get(i++)) )
         .addCS( parseFloat(info.get(i++)) )
         .addCSPM( parseFloat(info.get(i++)) )
         .addGold(info.get(i++) ) 
         .addGPM(info.get(i++) ) 
         .addKpar( info.get(i++) ) 
         .addKillShare( info.get(i++) ) 
         .addGoldShare( info.get(i++) ) 
         .setPositionPlayed( info.get(i++) ) ;
        return builder.build();
    }

    public static int parseInt(String message){
        if(message.equals("-")){
            return 0;
        }
        return Integer.parseInt(message);
    }

    public static float parseFloat(String message){
        if(message.equals("-")){
            return 0;
        }
        return Float.parseFloat(message);
    }
    
}
