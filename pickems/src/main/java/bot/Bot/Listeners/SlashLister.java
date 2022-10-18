package bot.Bot.Listeners;

import javax.annotation.Nonnull;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;

public class SlashLister extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@Nonnull SlashCommandInteractionEvent event) {

        TextInput size = TextInput.create("size", "Size", TextInputStyle.SHORT)
            .setRequired(true)
            .build();

        TextInput sort = TextInput.create("sort", "Sort", TextInputStyle.SHORT)
        .setRequired(false)
        .setMinLength(0)
        .build();

        TextInput fields = TextInput.create("show", "Show", TextInputStyle.SHORT)
        .setRequired(false)
        .setMinLength(0)
        .build();

        TextInput query = TextInput.create("query", "Filter", TextInputStyle.SHORT)
        .setRequired(false)
        .setMinLength(0)
        .build();

        TextInput get = TextInput.create("get", "Get", TextInputStyle.SHORT)
        .setRequired(false)
        .setMinLength(0)
        .build();

        Modal modal = Modal.create("bot-modal", "Champ Stats")
            .addActionRows(
                ActionRow.of(size),
                ActionRow.of(sort),
                ActionRow.of(fields),
                ActionRow.of(query),
                ActionRow.of(get)
            )
            .build();

        event.replyModal(modal).queue();

    }


    
}
