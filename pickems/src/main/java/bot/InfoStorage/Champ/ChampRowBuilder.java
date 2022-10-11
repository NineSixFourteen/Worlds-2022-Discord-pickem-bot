package bot.InfoStorage.Champ;

public class ChampRowBuilder {

    private ChampRow row;

    public ChampRowBuilder(){
        row = new ChampRow();
    }

    public ChampRowBuilder addPresence(String presence){
        row.setPresence(presence);
        return this;
    }
    public ChampRowBuilder addGamesPlayed(int gamesplayed){
        row.setGamesPlayed(gamesplayed);
        return this;
    }
    public ChampRowBuilder addBans(int Bans){
        row.setBaned(Bans);
        return this;
    }
    public ChampRowBuilder addWins(int Wins){
        row.setWins(Wins);
        return this;
    }
    public ChampRowBuilder addLoses(int Loses){
        row.setLoses(Loses);
        return this;
    }

    public ChampRowBuilder addWinRate(String WinRate){
        row.setWinRate(WinRate);
        return this;
    }
    public ChampRowBuilder addKills(Float Kills){
        row.setAvgKills(Kills);
        return this;
    }
    public ChampRowBuilder addDeaths(Float Deaths){
        row.setAvgDeaths(Deaths);
        return this;
    }
    public ChampRowBuilder addAssists(Float Assits){
        row.setAvgAssists(Assits);
        return this;
    }
    public ChampRowBuilder addKda(Float Kda){
        row.setKda(Kda);
        return this;
    }
    public ChampRowBuilder addCS(Float CS){
        row.setAvgCs(CS);
        return this;
    }
    public ChampRowBuilder addCSPM(Float CSPM){
        row.setCspm(CSPM);
        return this;
    }
    public ChampRowBuilder addGold(String gold){
        row.setGold(gold);
        return this;
    }
    public ChampRowBuilder addGPM(String gpm){
        row.setGPM(gpm);
        return this;
    }
    public ChampRowBuilder addKpar(String KPar){
        row.setKPar(KPar);
        return this;
    }
    public ChampRowBuilder addKillShare(String KPar){
        row.setKShare(KPar);
        return this;
    }
    public ChampRowBuilder addGoldShare(String GShare){
        row.setGShare(GShare);
        return this;
    }
    public ChampRowBuilder setPositionPlayed(String posPlay){
        row.setPosPlayed(posPlay);
        return this;
    }

    public ChampRow build(){
        return row;
    }

    
}
