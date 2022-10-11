package bot.InfoStorage.Player;

import bot.InfoStorage.Converters;

public class PStatsLookup {

    private PlayerRow player;

    public PStatsLookup(PlayerRow row){
        player = row;
    }

    public float lookUp(String mes){
        int id = 0;
        switch(mes){
            case "gp": case "games played":
                id = 1;
            case "wins": case "w":
                id = 2;
            case "loses": case "l":
                id = 3;
            case "win rate": case "wr":
                id = 4;
            case "kills": case "k":
                id = 5;
            case "deaths": case "d":
                id = 6;
            case "assists": case "a":
                id = 7;
            case "kda":
                id = 8;
            case "cs" : 
                id = 9;
            case "cspm":
                id = 10;
            case "gold": case "g":
                id = 11;
            case "gpm": case "gold per minute":
                id = 12;
            case "kp": case "kill particpate":
                id = 13;
            case "gs": case "gold share":
                id = 14;
            case "champs" : case "c":
                id = 15;
            case "pos" : case "posistion":
                id = 16;
        }
        return lookupID(id);
    }

    public float lookupID(int id){
        switch(id){
            case 1: 
                return player.getGamesPlayed();
            case 2: 
                return player.getWins();
            case 3: 
                return player.getLoses();
            case 4: 
                return Converters.removePer(player.getWinRate());
            case 5: 
                return player.getKills();
            case 6: 
                return player.getDeaths();
            case 7: 
                return player.getAssists();
            case 8: 
                return player.getKda();
            case 9: 
                return player.getCs();
            case 10: 
                return player.getCspm();
            case 11: 
                return Converters.asFloat(player.getGold()) * 1000;
            case 12: 
                return Converters.asFloat(player.getGoldpm());
            case 13: 
                return Converters.removePer(player.getKillPar());
            case 14: 
                return Converters.removePer(player.getGoldShare());
            case 15: 
                return player.getChampsPlayed();
            case 16:
                switch(player.getPos()){
                    case "Top": return 1; 
                    case "Jungle": return 2;
                    case "Mid": return 3; 
                    case "Bot": return 4;
                    case "Support": return 5;
                    default : return 0;
                }
            default:
                throw new Error("ID bad for players"); 
        }
    }

    
}
