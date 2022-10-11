package bot.InfoStorage;

import java.util.ArrayList;

public class DataBase {

    private ArrayList<Row> Rows = new ArrayList<>();

    public ArrayList<Row> getRows() {
        return Rows;
    }

    public void addRow(Row row){
        Rows.add(row);
    }

    public void display(){
        for(Row row : Rows){
            row.display();
        }
    }
    
}
