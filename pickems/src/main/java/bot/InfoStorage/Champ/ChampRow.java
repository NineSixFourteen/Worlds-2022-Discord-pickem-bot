package bot.InfoStorage.Champ;

public class ChampRow{

    //Fields
    private String Presence;
    private int Baned;
    private int GamesPlayed;
    private int wins; 
    private int loses;
    private String WinRate;
    private Float AvgKills;
    private Float AvgDeaths;
    private Float AvgAssists;
    private Float Kda;
    private Float AvgCs;
    private Float Cspm;
    private String Gold;
    private String GPM;
    private String KPar;
    private String KShare;
    private String GShare;
    private String PosPlayed;

    //Getter and Setters 
    public String getPresence() {
        return Presence;
    }
    public void setPresence(String presence) {
        Presence = presence;
    }
    public int getBaned() {
        return Baned;
    }
    public void setBaned(int baned) {
        Baned = baned;
    }
    public int getGamesPlayed() {
        return GamesPlayed;
    }
    public void setGamesPlayed(int gamesPlayed) {
        GamesPlayed = gamesPlayed;
    }
    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
    public int getLoses() {
        return loses;
    }
    public void setLoses(int loses) {
        this.loses = loses;
    }
    public String getWinRate() {
        return WinRate;
    }
    public void setWinRate(String winRate) {
        WinRate = winRate;
    }
    public Float getAvgKills() {
        return AvgKills;
    }
    public void setAvgKills(Float avgKills) {
        AvgKills = avgKills;
    }
    public Float getAvgDeaths() {
        return AvgDeaths;
    }
    public void setAvgDeaths(Float avgDeaths) {
        AvgDeaths = avgDeaths;
    }
    public Float getAvgAssists() {
        return AvgAssists;
    }
    public void setAvgAssists(Float avgAssists) {
        AvgAssists = avgAssists;
    }
    public Float getKda() {
        return Kda;
    }
    public void setKda(Float kda) {
        Kda = kda;
    }
    public Float getAvgCs() {
        return AvgCs;
    }
    public void setAvgCs(Float avgCs) {
        AvgCs = avgCs;
    }
    public Float getCspm() {
        return Cspm;
    }
    public void setCspm(Float cspm) {
        Cspm = cspm;
    }
    public String getGold() {
        return Gold;
    }
    public void setGold(String gold) {
        Gold = gold;
    }
    public String getGPM() {
        return GPM;
    }
    public void setGPM(String gPM) {
        GPM = gPM;
    }
    public String getKPar() {
        return KPar;
    }
    public void setKPar(String kPar) {
        KPar = kPar;
    }
    public String getKShare() {
        return KShare;
    }
    public void setKShare(String kShare) {
        KShare = kShare;
    }
    public String getGShare() {
        return GShare;
    }
    public void setGShare(String gShare) {
        GShare = gShare;
    }
    public String getPosPlayed() {
        return PosPlayed;
    }
    public void setPosPlayed(String posPlayed) {
        PosPlayed = posPlayed;
    }

    //Other Functions :)
    @Override
    public String toString() {
        return " has " + this.Presence + " Presence " + " Played : " + this.GamesPlayed + " Banned : " + this.Baned + " Pos Played :" + this.PosPlayed ;
    }
    public float lookup(String Field){
        return new CStatsLookup(this).lookup(Field);
    }
    public float lookupID(int ID){
        return new CStatsLookup(this).lookupID(ID);
    }

}
