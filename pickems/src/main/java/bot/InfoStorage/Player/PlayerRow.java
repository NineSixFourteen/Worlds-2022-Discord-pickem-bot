package bot.InfoStorage.Player;

public class PlayerRow {

    private int GamesPlayed;
    private int Wins;
    private int Loses;
    private String WinRate;
    private int Kills;
    private int deaths; 
    private int Assists;
    private float kda;
    private int cs; 
    private float cspm;
    private String gold;
    private String goldpm;
    private String killPar;
    private String killShare;
    private String goldShare;
    private String champsPlayed;
    private String Pos; 
    private int firstBlood;
    private String team;
    
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public int getFirstBlood() {
        return firstBlood;
    }
    public void setFirstBlood(int firstBlood) {
        this.firstBlood = firstBlood;
    }
    public int getAssists() {
        return Assists;
    }
    public void setAssists(int assists) {
        Assists = assists;
    }
    public String getPos() {
        return Pos;
    }
    public void setPos(String pos) {
        Pos = pos;
    }
    public int getGamesPlayed() {
        return GamesPlayed;
    }
    public void setGamesPlayed(int gamesPlayed) {
        GamesPlayed = gamesPlayed;
    }
    public int getWins() {
        return Wins;
    }
    public void setWins(int wins) {
        Wins = wins;
    }
    public int getLoses() {
        return Loses;
    }
    public void setLoses(int loses) {
        Loses = loses;
    }
    public String getWinRate() {
        return WinRate;
    }
    public void setWinRate(String winRate) {
        WinRate = winRate;
    }
    public int getKills() {
        return Kills;
    }
    public void setKills(int kills) {
        Kills = kills;
    }
    public int getDeaths() {
        return deaths;
    }
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
    public float getKda() {
        return kda;
    }
    public void setKda(float kda) {
        this.kda = kda;
    }
    public int getCs() {
        return cs;
    }
    public void setCs(int cs) {
        this.cs = cs;
    }
    public float getCspm() {
        return cspm;
    }
    public void setCspm(float cspm) {
        this.cspm = cspm;
    }
    public String getGold() {
        return gold;
    }
    public void setGold(String gold) {
        this.gold = gold;
    }
    public String getGoldpm() {
        return goldpm;
    }
    public void setGoldpm(String goldpm) {
        this.goldpm = goldpm;
    }
    public String getKillPar() {
        return killPar;
    }
    public void setKillPar(String killPar) {
        this.killPar = killPar;
    }
    public String getKillShare() {
        return killShare;
    }
    public void setKillShare(String killShare) {
        this.killShare = killShare;
    }
    public String getGoldShare() {
        return goldShare;
    }
    public void setGoldShare(String goldShare) {
        this.goldShare = goldShare;
    }
    public String getChampsPlayed() {
        return champsPlayed;
    }
    public void setChampsPlayed(String champsPlayed) {
        this.champsPlayed = champsPlayed;
    }
    @Override
    public String toString() {
        return " | " + this.GamesPlayed + " | " + this.Wins + " | "  + this.champsPlayed + " | " + this.firstBlood + " | " + this.team ;
    }
    public float lookupID(int id){
        return new PStatsLookup(this).lookupID(id);
    }




    
}
