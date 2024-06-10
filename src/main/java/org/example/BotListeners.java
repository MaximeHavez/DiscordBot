package org.example;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotListeners extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().equals("ping")) {

            event.reply("pong").queue();

        }

        if (event.getName().equals("raid")) {
            event.deferReply().queue();
            MessageEmbed eb = ItemEmbbed.TestEmbed();
            event.getHook().sendMessageEmbeds(eb).queue();
        }

    }

}
