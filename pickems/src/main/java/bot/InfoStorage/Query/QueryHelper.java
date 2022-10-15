package bot.InfoStorage.Query;

public class QueryHelper {

    public static Comparisson get(String msg){
        switch(msg){
            case "<" : return Comparisson.LessThan;
            case ">" : return Comparisson.GreaterThan;
            case "<=": return Comparisson.LTEqualTo;
            case ">=": return Comparisson.GTEqualTo;
            case "==": return Comparisson.EqualTo;
            case "!=": return Comparisson.NotEqualTo;
            default:return Comparisson.LessThan;
        }
    }
    
}
