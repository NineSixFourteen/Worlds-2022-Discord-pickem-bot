package bot.InfoStorage;

public class Converters {
    
    //Converters 

    public static float asFloat(String mes){
        mes  = mes.equals("-") ? "0" : mes;
        return Float.parseFloat(mes);
    }

    public static float removePer(String mes){
        mes  = mes.equals("-") ? "0%" : mes;
        mes = mes.substring(0, mes.length() - 1);
        return Float.parseFloat(mes);
    }

    public static float removeK(String mes){
        mes  = mes.equals("-") ? "0" : mes;
        if(mes.endsWith("k")){
            float num = Float.parseFloat(mes.substring(0, mes.length() -1));
            return num * 1000;
        } else {
            return Float.parseFloat(mes);
        } 
    }
}
