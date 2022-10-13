package bot.InfoStorage.Event;

public class EventData {
    
    private String WorldsWinner = "Unknown";
    private String GroupAWinner = "Fnatic";
    private String GroupBWinner = "DRX";
    private String MostKills ;
    private String TeamWithMostUniqueChamps; 
    private String LessThan2Seeds = "Gam Esports | CFO";
    private int HowManyReverse = 0;
    private int HowManyPentas = 2;
    private String LongestGameTime;
    private int HowManyBaronSteals = 5;   
    private String MostDrakes; 

    public EventData(String team, String drakes,String gametime, String kills){
        this.TeamWithMostUniqueChamps = team;
        this.MostDrakes = drakes;
        this.MostKills = kills;
        this.LongestGameTime = gametime;
    }

    public String getWorldsWinner() {
        return WorldsWinner;
    }

    public String getGroupAWinner() {
        return GroupAWinner;
    }

    public String getGroupBWinner() {
        return GroupBWinner;
    }

    public String getMostKills() {
        return MostKills;
    }

    public String getTeamWithMostUniqueChamps() {
        return TeamWithMostUniqueChamps;
    }

    public String getLessThan2Seeds() {
        return LessThan2Seeds;
    }

    public int getHowManyReverse() {
        return HowManyReverse;
    }

    public int getHowManyPentas() {
        return HowManyPentas;
    }

    public String getLongestGameTime() {
        return LongestGameTime;
    }

    public int getHowManyBaronSteals() {
        return HowManyBaronSteals;
    }

    public String getMostDrakes() {
        return MostDrakes;
    }

    @Override
    public String toString() {
        return "EventData [WorldsWinner=" + WorldsWinner + ", GroupAWinner=" + GroupAWinner + ", GroupBWinner="
                + GroupBWinner + ", MostKills=" + MostKills + ", TeamWithMostUniqueChamps=" + TeamWithMostUniqueChamps
                + ", LessThan2Seeds=" + LessThan2Seeds + ", HowManyReverse=" + HowManyReverse + ", HowManyPentas="
                + HowManyPentas + ", LongestGameTime=" + LongestGameTime + ", HowManyBaronSteals=" + HowManyBaronSteals
                + ", MostDrakes=" + MostDrakes + "]";
    }

    
}
