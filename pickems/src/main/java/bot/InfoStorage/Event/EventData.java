package bot.InfoStorage.Event;

public class EventData {
    
    private String WorldsWinner = "Unknown";
    private String GroupAWinner = "Fnatic";
    private String GroupBWinner = "DRX";
    private String TeamWithMostUniqueChamps; 
    private String LessThan2Seeds = "Gam Esports";
    private int HowManyReverse = 0;
    private int HowManyPentas = 0;
    private float LongestGameTime;
    private int HowManyBaronSteals = 0;   
    private String MostDrakes; 

    public EventData(String team, String drakes){
        this.TeamWithMostUniqueChamps = team;
        this.MostDrakes = drakes;
    }

    
}
