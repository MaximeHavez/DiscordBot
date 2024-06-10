package org.example;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ItemEmbbed {

    private static List<Classe> classesList(String role) {
        Map<String, List<String>> roleClasses = new HashMap<>();
        roleClasses.put("tank", Arrays.asList("Paladin", "Guerrier", "Chevalier Noir", "Pistosabreur"));
        roleClasses.put("healer", Arrays.asList("Mage Blanc", "Erudit", "Astromancien", "Sage"));
        roleClasses.put("melee", Arrays.asList("Moine", "Chevalier Dragon", "Ninja", "Samouraï", "Faucheur"));
        roleClasses.put("distant", Arrays.asList("Barde", "Machiniste", "Danseur"));
        roleClasses.put("magie", Arrays.asList("Mage noir", "Invocateur", "Mage Rouge"));

        List<Classe> classes = new ArrayList<>();
        if (roleClasses.containsKey(role)) {
            for (String className : roleClasses.get(role)) {
                classes.add(new Classe(className));
            }
        } else {
            for (List<String> classNames : roleClasses.values()) {
                for (String className : classNames) {
                    classes.add(new Classe(className));
                }
            }
        }

        return classes;
    }

    public static MessageEmbed TestEmbed() {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Création de raid", null);
        embed.setColor(Color.GREEN);
        embed.addField("Date", "XX mois année", true);
        embed.addField("heure", "HH:MM", true);
        embed.addField("Nb participants", "XX", true);

        String[] roles = {"tank", "healer", "melee", "distant", "magie"};
        String[] roleNames = {"Tanks", "Soigneurs", "DPS Melée", "DPS Distant", "Mages"};

        for (int i = 0; i < roles.length; i++) {
            StringBuilder roleDisplay = new StringBuilder();
            for (Classe classe : classesList(roles[i])) {
                roleDisplay.append("**").append(CompteursClasses(classe.getNom())).append("** ").append(classe.getNom()).append("\n");
            }
            embed.addField(roleNames[i], roleDisplay.toString(), true);
        }

        embed.addBlankField(true);
        embed.setAuthor("Holy Dragon lair", null, null);
        embed.setFooter("text", null);

        return embed.build();
    }

    // Inscription joueur
    public void inscrireJoueur(String nomClasse) {

        classesList("").stream()
                .filter(c -> c.getNom().equalsIgnoreCase(nomClasse))
                .findFirst().ifPresent(Classe::incrementerEffectif);

    }

    // Desinscrire joueur
    public void desinscrireJoueur(String nomClasse) {
        classesList("").stream()
                .filter(c -> c.getNom().equalsIgnoreCase(nomClasse))
                .findFirst().ifPresent(Classe::decrementerEffectif);

    }

    public static String CompteursClasses(String nomClasse) {
        Classe classe = classesList("").stream()
                .filter(c -> c.getNom().equalsIgnoreCase(nomClasse))
                .findFirst()
                .orElse(null);
        if (classe != null) {
            return String.valueOf(classe.getEffectif());
        } else {
            return "0";
        }
    }


}
