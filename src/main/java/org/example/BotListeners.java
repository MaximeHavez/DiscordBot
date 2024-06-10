package org.example;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.List;

public class BotListeners extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().equals("ping")) {

            event.reply("pong").queue();

        }

        if (event.getName().equals("raid")) {
            event.deferReply().queue();
            MessageEmbed eb = RaidBotEmbed.RaidEmbed();
            event.getHook().sendMessageEmbeds(eb)
                    .addActionRow(
                            Button.secondary("tank", Emoji.fromUnicode("\uD83D\uDEE1")), // Button with only a label
                            Button.secondary("soigneur", Emoji.fromUnicode("\uD83D\uDC89")),// Button with only an emoji
                            Button.secondary("melee", Emoji.fromUnicode("⚔")),// Button with a label and an emoji
                            Button.secondary("distant", Emoji.fromUnicode("\uD83C\uDFF9")),
                            Button.secondary("magie", Emoji.fromUnicode("\uD83D\uDD2E")))
                    .queue();

        };
    }


}



