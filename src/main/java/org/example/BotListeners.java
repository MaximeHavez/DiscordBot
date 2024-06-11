package org.example;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

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
                            StringSelectMenu.create("select")
                                    .addOption("Tank", "tank")
                                    .addOption("Healer", "healer")
                                    .addOption("Melee", "melee")
                                    .addOption("Distant", "distant")
                                    .addOption("Magie", "magie")
                                    .setPlaceholder("Selectionnez un rôle")
                                    .build()
                    ).queue();

        };
    }


}



