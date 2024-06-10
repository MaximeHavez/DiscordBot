package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class DiscordBot extends ListenerAdapter {
    public static void main(String[] args) throws InterruptedException {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY");
        String guildId = dotenv.get("GUILD_ID");
        JDA jda = JDABuilder.createDefault(apiKey)
                .addEventListeners(new BotListeners()).build().awaitReady();

        Guild guild = jda.getGuildById(guildId);

        if(guild != null){
            guild.upsertCommand("ping", "Ping pong").queue();
            guild.upsertCommand("raid", "Création de raid").queue();
        }

    }

}