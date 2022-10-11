package bot.InfoStorage;

public class PlayerRowBuilder {

    private static PlayerRow row;

    public PlayerRowBuilder(){
        row = new PlayerRow();
    }

    public PlayerRowBuilder addName(String name){
        row.setName(name);
        return this;
    }

    public PlayerRowBuilder addGamesPlayed(int gp){
        row.setGamesPlayed(gp);
        return this;
    }

    public PlayerRowBuilder addKills(float kpg){
        //Scraped info is average kills I want total kills
        row.setKills(Math.round(kpg * row.getGamesPlayed()));
        return this;
    }

    public PlayerRowBuilder addDeaths(float dpg){
        //Scraped info is average deaths I want total deaths
        row.setDeaths(Math.round(dpg * row.getGamesPlayed()));
        return this;
    }

    public PlayerRowBuilder addAssits(float apg){
        //Scraped info is average assists I want total assists
        row.setAssists(Math.round(apg * row.getGamesPlayed()));
        return this;
    }

    public PlayerRowBuilder addKda(float Kda){
        row.setKda(Kda);
        return this;
    }

    public PlayerRowBuilder addWins(int Wins){
        row.setWins(Wins);
        return this;
    }

    public PlayerRowBuilder addLoses(int Loses){
        row.setLoses(Loses);
        return this;
    }

    public PlayerRowBuilder addWinrate(String wr){
        row.setWinRate(wr);
        return this;
    }

    public PlayerRowBuilder addTotalCs(float Cs){
        //Scraped info is average CS I want total CS
        row.setCs(Math.round(Cs * row.getGamesPlayed()));
        return this;
    }

    public PlayerRowBuilder addCSPM(float cspm){
        row.setCspm(cspm);
        return this;
    }

    public PlayerRowBuilder addGold(String gold){
        row.setGold(gold);
        return this;
    }

    public PlayerRowBuilder addGPM(String gpm){
        row.setGoldpm(gpm);
        return this;
    }

    public PlayerRowBuilder addKillPar(String killPar){
        row.setKillPar(killPar);
        return this;
    }

    public PlayerRowBuilder addKillShare(String KS){
        row.setKillShare(KS);
        return this;
    }

    public PlayerRowBuilder addGoldShare(String GS){
        row.setGoldShare(GS);
        return this;
    }

    public PlayerRowBuilder addChampsPlayed(int ChampsPlayed){
        row.setChampsPlayed(ChampsPlayed);
        return this;
    }

    public PlayerRowBuilder setPos(String Pos){
        row.setPos(Pos);
        return this;
    }

    public PlayerRow build(){
        return row;
    }
    



    

    
}
