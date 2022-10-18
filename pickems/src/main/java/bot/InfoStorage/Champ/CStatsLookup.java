package bot.InfoStorage.Champ;

import bot.InfoStorage.Converters;

public class CStatsLookup {

    private ChampRow champ;

    public CStatsLookup(ChampRow row){
        champ = row;
    }

    //Float 
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
            case "total kills": case "tk":
                id = 19;break;
            case "total deaths": case "td":
                id = 20;
        }
        return lookupID(id);
    }

    public float lookupID( int num) {
        switch(num){
            case 1:
                return Converters.removePer(champ.getPresence());
            case 2:
                return champ.getBaned();
            case 3:
                return champ.getGamesPlayed();
            case 4:
                return champ.getWins();
            case 5:
                return champ.getLoses();
            case 6:
                return Converters.removePer(champ.getWinRate());
            case 7:
                return champ.getAvgKills();
            case 8:
                return champ.getAvgDeaths();
            case 9:
                return champ.getAvgAssists();
            case 10:
                return champ.getKda();
            case 11:
                return champ.getAvgCs();
            case 12:
                return champ.getAvgCs();
            case 13:
                return Converters.removeK(champ.getGold());
            case 14:
                return Converters.asFloat(champ.getGPM());
            case 15:
                return Converters.removePer(champ.getKPar());
            case 16:
                return Converters.removePer(champ.getKPar());
            case 17:
                return Converters.removePer(champ.getKPar());
            case 18:
                String[] pos = champ.getPosPlayed().split(", ");
                return pos.length;
            case 19: 
                return champ.getTotalKills();
            case 20:
                return champ.getTotalDeath();
            default:
                throw new Error("Unknown stat numebr");
        }
    }

      
}
