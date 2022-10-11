package bot.InfoStorage.Champ;

import bot.InfoStorage.Converters;

public class CStatsLookup {

    private ChampRow champ;

    public CStatsLookup(ChampRow row){
        champ = row;
    }

    public float lookup(String field){
        int id = 0;
        switch(field.toLowerCase().trim()){
            case "precedence": case "p":
                id = 1;break; 
            case "bans": case "b":
                id = 2;break; 
            case "games played": case "gp":
                id = 3;break; 
            case "wins": case "w":
                id = 4;break; 
            case "loses": case "l":
                id = 5;break; 
            case "winrate": case "wr":
                id = 6;break; 
            case "kills": case "k":
                id = 7;break; 
            case "deaths": case "d":
                id = 8;break; 
            case "assists": case "a":
                id = 9;break; 
            case "kda": 
                id = 10;break; 
            case "avg cs": case "cs":
                id = 11;break; 
            case "cspm": case "cs per minute":
                id = 12;break; 
            case "gold": case "g":
                id = 13;break; 
            case "gpm": case "gold per minute":
                id = 14;break; 
            case "kp": case "kill participation":
                id = 15;break; 
            case "ks": case "kill share":
                id = 16;break; 
            case "gs": case "gold share":
                id = 17;break; 
            case "pos": case "positions played":
                id = 18;break; 
        }
        return lookupID(id);
    }

    public float lookupID( int num) {
        switch(num){
            case 1://Precedence
                return Converters.removePer(champ.getPresence());
            case 2://Bans
                return champ.getBaned();
            case 3://Games Played
                return champ.getGamesPlayed();
            case 4://Wins
                return champ.getWins();
            case 5://Loses
                return champ.getLoses();
            case 6://WinRate
                return Converters.removePer(champ.getWinRate());
            case 7://Kills 
                return champ.getAvgKills();
            case 8://Deaths
                return champ.getAvgDeaths();
            case 9://Assists
                return champ.getAvgAssists();
            case 10://Kda
                return champ.getKda();
            case 11://Avg CS
                return champ.getAvgCs();
            case 12://CS Per Minute
                return champ.getAvgCs();
            case 13://Avg Gold
                return Converters.removeK(champ.getGold());
            case 14://Gold Per Minute
                return Converters.asFloat(champ.getGPM());
            case 15://Kill Particpation
                return Converters.removePer(champ.getKPar());
            case 16://Kill Share
                return Converters.removePer(champ.getKPar());
            case 17://Gold Share
                return Converters.removePer(champ.getKPar());
            case 18://Positsions played 
                String[] pos = champ.getPosPlayed().split(", ");
                return pos.length;
            default:
                throw new Error("Unknown stat numebr");
        }
    }

    
    
}
