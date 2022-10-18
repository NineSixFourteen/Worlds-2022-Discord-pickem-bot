package bot.Bot.MessageMakers;

import java.util.ArrayList;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class SendButtons {

    private static Button[] buttons = 
        new Button[]{
            Button.primary("stats", "Stats"),
            Button.primary("pstats", "Player Stats"),
            Button.primary("event", "Event"),
            Button.primary("kills", "Kills"),
            Button.primary("deaths", "Deaths"),
            Button.primary("wr", "WR"),
            Button.primary("picks", "Picks"),
            Button.primary("bans", "Bans"),
            Button.primary("pos", "Pos"),
            Button.primary("pkills", "Player Kills"),
            Button.primary("pdeaths", "Player Deaths"),
            Button.primary("pkda", "Player KDA"),
            Button.primary("pgp", "Player Games Played"),
            Button.primary("pchamp", "Player's champs"),
            Button.primary("fb", "First Bloods"),
            Button.primary("penta", "Penta Kills"),
            Button.primary("unique", "Unique Champs"),
        };
    

    public static Message getMessage1(){
        MessageBuilder mb = new MessageBuilder();
        mb.append("Commands 1")
         .setActionRows(
            ActionRow.of(buttons[0]),
            ActionRow.of(buttons[1]),
            ActionRow.of(buttons[2]),
            ActionRow.of(buttons[3]),
            ActionRow.of(buttons[4])
        );
        return mb.build();
    }

    public static Message getMessage2(){
        MessageBuilder mb = new MessageBuilder();
        mb.append("Commands 2")
         .setActionRows(
            ActionRow.of(buttons[5]),
            ActionRow.of(buttons[6]),
            ActionRow.of(buttons[7]),
            ActionRow.of(buttons[8]),
            ActionRow.of(buttons[9])
        );
        return mb.build();
    }
    public static Message getMessage3(){
        MessageBuilder mb = new MessageBuilder();
        mb.append("Commands 3")
         .setActionRows(
            ActionRow.of(buttons[10]),
            ActionRow.of(buttons[11]),
            ActionRow.of(buttons[12]),
            ActionRow.of(buttons[13]),
            ActionRow.of(buttons[14])
        );
        return mb.build();
    }

}
