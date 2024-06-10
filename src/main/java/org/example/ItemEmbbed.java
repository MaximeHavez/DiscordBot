package org.example;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ItemEmbbed {

    /**
     * This method generates a list of classes based on the role provided.
     * If the role is not found, it returns a list of all classes.
     *
     * @param role The role for which the classes are to be fetched.
     *             The role can be one of the following: "tank", "healer", "melee", "distant", "magie".
     *             If the role is not one of these, the method returns a list of all classes.
     * @return A list of Classe objects corresponding to the role.
     * If the role is not found, it returns a list of all classes.
     */
    private static List<Classe> classesList(String role) {
        // A map to store the role and corresponding classes
        Map<String, List<String>> roleClasses = new HashMap<>();
        roleClasses.put("tank", Arrays.asList("Paladin", "Guerrier", "Chevalier Noir", "Pistosabreur"));
        roleClasses.put("healer", Arrays.asList("Mage Blanc", "Erudit", "Astromancien", "Sage"));
        roleClasses.put("melee", Arrays.asList("Moine", "Chevalier Dragon", "Ninja", "Samouraï", "Faucheur"));
        roleClasses.put("distant", Arrays.asList("Barde", "Machiniste", "Danseur"));
        roleClasses.put("magie", Arrays.asList("Mage noir", "Invocateur", "Mage Rouge"));

        // A list to store the classes for the role
        List<Classe> classes = new ArrayList<>();
        if (roleClasses.containsKey(role)) {
            // If the role is found, add the corresponding classes to the list
            for (String className : roleClasses.get(role)) {
                classes.add(new Classe(className));
            }
        } else {
            // If the role is not found, add all classes to the list
            for (List<String> classNames : roleClasses.values()) {
                for (String className : classNames) {
                    classes.add(new Classe(className));
                }
            }
        }

        return classes;
    }

    /**
     * This method generates a MessageEmbed object for a raid creation.
     * It sets the title, color, and fields of the embed.
     * It also adds a field for each role ("tank", "healer", "melee", "distant", "magie") with the class names and their counts.
     * The method returns the built MessageEmbed object.
     *
     * @return A MessageEmbed object for a raid creation.
     */
    public static MessageEmbed TestEmbed() {
        // Create a new EmbedBuilder object
        EmbedBuilder embed = new EmbedBuilder();

        // Set the title, color, and fields of the embed
        embed.setTitle("Création de raid", null);
        embed.setColor(Color.GREEN);
        embed.addField("Date", "XX mois année", true);
        embed.addField("heure", "HH:MM", true);
        embed.addField("Nb participants", "XX", true);

        // Define the roles and their display names
        String[] roles = {"tank", "healer", "melee", "distant", "magie"};
        String[] roleNames = {"Tanks", "Soigneurs", "DPS Melée", "DPS Distant", "Mages"};

        // Add a field for each role with the class names and their counts
        for (int i = 0; i < roles.length; i++) {
            StringBuilder roleDisplay = new StringBuilder();
            for (Classe classe : classesList(roles[i])) {
                roleDisplay.append("**").append(CompteursClasses(classe.getNom())).append("** ").append(classe.getNom()).append("\n");
            }
            embed.addField(roleNames[i], roleDisplay.toString(), true);
        }

        // Add a blank field, set the author and footer of the embed
        embed.addBlankField(true);
        embed.setAuthor("Holy Dragon lair", null, null);
        embed.setFooter("text", null);

        // Build and return the MessageEmbed object
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
