package bot.InfoStorage.Event;

import java.util.HashMap;

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
    HashMap<String, Integer> drakes;   
    private String MostDrakes;
    private int NumberOfElders; 

    public String getTitle(int num){
        switch(num){
            case 0: return "Worlds Winner";
            case 1: return "Group A Winner";
            case 2: return "Group B Winner";
            case 3: return "Most Kills";
            case 4: return "Team With Most Champs";
            case 5: return "Less Than 2 Seeds";
            case 6: return "Reverse Sweeps";
            case 7: return "Number of Pentas"; 
            case 8: return "Longest Game";
            case 9: return "How Many Baron Steals";
            case 10: return "Most Drakes";
            case 11: return "Elder Drakes";
            default: return "not a field";
        }
    } 
    
    public String getValue(int num){
        switch(num){
            case 0: return this.WorldsWinner;
            case 1: return this.GroupAWinner;
            case 2: return this.getGroupBWinner();
            case 3: return this.MostKills;
            case 4: return this.TeamWithMostUniqueChamps;
            case 5: return this.LessThan2Seeds;
            case 6: return this.HowManyReverse + "";
            case 7: return this.HowManyPentas  +""; 
            case 8: return this.LongestGameTime;
            case 9: return this.HowManyBaronSteals + "";
            case 10: return this.MostDrakes;
            case 11: return this.NumberOfElders + "";
            default: return "not a field";
        }
    }

    public EventData(String team, String drakes,String gametime, String kills,int elder, HashMap<String, Integer> drks){
        this.TeamWithMostUniqueChamps = team;
        this.MostDrakes = drakes;
        this.MostKills = kills;
        this.drakes = drks;
        this.LongestGameTime = gametime;
        this.NumberOfElders = elder;
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

    public HashMap<String, Integer> getDrakes() {
        return drakes;
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
