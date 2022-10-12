package bot.Scarper.PlayerScraper;

public class playerInfo {
    
    private int fb; 
    private String team;

    public playerInfo(int firstbloods, String Team){
        fb = firstbloods; 
        team = Team;
    }

    public String getTeam() {
        return team;
    }
    
    public int getFb() {
        return fb;
    }
}
