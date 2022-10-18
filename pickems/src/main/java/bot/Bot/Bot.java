package bot.Bot;

import javax.security.auth.login.LoginException;

import bot.Token;
import bot.Bot.Listeners.ButtonListeners;
import bot.Bot.Listeners.MessageListener;
import bot.Bot.Listeners.ModalListener;
import bot.Bot.Listeners.ReactionListener;
import bot.Bot.Listeners.SlashLister;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot {

    public static void main(String[] args) throws LoginException, InterruptedException {
        JDA jda = JDABuilder.createDefault(Token.getToken())
         .addEventListeners(new MessageListener())
         .addEventListeners(new SlashLister())
         .addEventListeners(new ModalListener())
         .addEventListeners(new ButtonListeners())
         .addEventListeners(new ReactionListener())
         .enableIntents(GatewayIntent.GUILD_MESSAGES)
         .build().awaitReady();

        Guild guild = jda.getGuildById("1028773480583401482");
        Guild guild2 = jda.getGuildById("248550973541187584");
        if(guild != null && guild2 != null){
            guild.upsertCommand("champs","type").queue();
            guild2.upsertCommand("champs","type").queue();
        } else {
            System.out.println("NOPE");
        }
         
    }    
}
