package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class DiscordBot extends ListenerAdapter {
    public static void main(String[] args) throws InterruptedException {

        JDA jda = JDABuilder.createDefault("MTI0NzEzNjU4MDM4OTMwNjQxMA.GxtlLy.HIYB9tY52ewiTsubvLZPzBrlpE_JAmFMZcnf98")
                .addEventListeners(new BotListeners()).build().awaitReady();

        Guild guild = jda.getGuildById("713953931000283157");

        if(guild != null){
            guild.upsertCommand("ping", "Ping pong").queue();
            guild.upsertCommand("raid", "Création de raid").queue();
        }

    }

}