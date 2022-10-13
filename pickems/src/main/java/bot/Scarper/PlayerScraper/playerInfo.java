package bot.Scarper.PlayerScraper;

public class PlayerInfo {
    
    private int fb; 
    private String name; 
    private String team;
    private String position; 

    public String getPosition() {
        return position;
    }
    public String getName() {
        return name;
    }
    public PlayerInfo(int firstbloods,String name, String Team,String Pos){
        fb = firstbloods; 
        this.position = Pos;
        team = Team;
        this.name = name;
    }
    public String getTeam() {
        return team;
    }
    public int getFb() {
        return fb;
    }
    @Override
    public String toString() {
        return "playerInfo [fb=" + fb + ", name=" + name + ", team=" + team + ", position=" + position + "]";
    }
    
}
