package bot.InfoStorage.Champ;

import java.util.ArrayList;
import java.util.Collections;

import bot.InfoStorage.DataBase;
import bot.InfoStorage.Pair;
import bot.InfoStorage.Query.Comparisson;

public class FilterChamp {

    public static DataBase<ChampRow> filterChampsNum(String row, DataBase<ChampRow> champs,float value,Comparisson com){
        DataBase<ChampRow> db = new DataBase<>();
        for(String key : champs.getRows().keySet()){
            boolean b = false;
            switch(com){
                case LessThan:    b = champs.getRows().get(key).lookup(row) <  value;break;
                case GreaterThan: b = champs.getRows().get(key).lookup(row) >  value;break;
                case LTEqualTo:   b = champs.getRows().get(key).lookup(row) <= value;break;
                case GTEqualTo:   b = champs.getRows().get(key).lookup(row) >= value;break;
                case EqualTo:     b = champs.getRows().get(key).lookup(row) == value;break;
                case NotEqualTo:  b = champs.getRows().get(key).lookup(row) != value;break;
            }
            if(b){
                db.addRow(key, champs.get(key));
            }
        }
        return db;
    }

    public static ArrayList<String> filterChampsNumTL(String row, DataBase<ChampRow> champs,float value,Comparisson com){
        ArrayList<String> name = new ArrayList<>();
        for(String key : champs.getRows().keySet()){
            boolean b = false;
            switch(com){
                case LessThan:    b = champs.getRows().get(key).lookup(row) <  value;break;
                case GreaterThan: b = champs.getRows().get(key).lookup(row) >  value;break;
                case LTEqualTo:   b = champs.getRows().get(key).lookup(row) <= value;break;
                case GTEqualTo:   b = champs.getRows().get(key).lookup(row) >= value;break;
                case EqualTo:     b = champs.getRows().get(key).lookup(row) == value;break;
                case NotEqualTo:  b = champs.getRows().get(key).lookup(row) != value;break;
            }
            if(b){
                name.add(key);
            }
        }
        return name;
    }

    public static ArrayList<String> filterThenSort(String sortRow, DataBase<ChampRow> champs,float value,Comparisson com,String row){
        ArrayList<String> filtered = filterChampsNumTL(row, champs, value, com);
        System.out.println(filtered);
        ArrayList<Pair> pairs = new ArrayList<>();
        for(String key : champs.getRows().keySet()){
            if(filtered.contains(key)){
                pairs.add(
                    new Pair(
                        key, 
                        champs.getRows().get(key).lookup(sortRow)
                    ));
            }
        }
        Collections.sort(pairs);
        ArrayList<String> names = new ArrayList<>();
        for(Pair pair : pairs){
            names.add(pair.getID());
        }
        return names ;
    }
}




    
