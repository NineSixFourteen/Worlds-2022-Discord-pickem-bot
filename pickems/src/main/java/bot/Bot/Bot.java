package bot.Bot;

import javax.security.auth.login.LoginException;

import bot.Token;
import bot.Bot.Listeners.MessageListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;



public class Bot {

    public static void main(String[] args) throws LoginException {
        JDABuilder.createDefault(Token.getToken())
         .addEventListeners(new MessageListener())
         .enableIntents(GatewayIntent.GUILD_MESSAGES)
         .build();
    }


    
}
