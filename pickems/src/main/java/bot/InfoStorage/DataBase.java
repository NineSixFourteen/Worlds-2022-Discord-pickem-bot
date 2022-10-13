package bot.InfoStorage;

import java.util.TreeMap;

import bot.InfoStorage.Player.PlayerRow;

public class DataBase<X> {

    private TreeMap<String,X> Rows;

    public DataBase(){
        Rows = new TreeMap<>();
    }

    public TreeMap<String,X> getRows() {
        return Rows;
    }

    public void addRow(String id, X row){
        Rows.put(id, row);
    }

    public void display(){
        for(String key : Rows.keySet()){
            System.out.println(key + Rows.get(key).toString());
        }
    }

    public X get(String key) {
        return this.Rows.get(key);
    }
    
}
