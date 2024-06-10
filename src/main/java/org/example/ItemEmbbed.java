package org.example;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ItemEmbbed {

    private static List<Classe> classesList(){
        List<Classe> classes = new ArrayList<>();

        //Ajout des classes de FF
        classes.add(new Classe("Paladin"));
        classes.add(new Classe("Guerrier"));
        classes.add(new Classe("Chevalier Noir"));
        classes.add(new Classe("Pistosabreur"));

        classes.add(new Classe("Mage Blanc"));
        classes.add(new Classe("Erudit"));
        classes.add(new Classe("Astromancien"));
        classes.add(new Classe("Sage"));

        classes.add(new Classe("Moine"));
        classes.add(new Classe("Chevalier Dragon"));
        classes.add(new Classe("Ninja"));
        classes.add(new Classe("Samouraï"));
        classes.add(new Classe("Faucheur"));

        classes.add(new Classe("Barde"));
        classes.add(new Classe("Machiniste"));
        classes.add(new Classe("Danseur"));

        classes.add(new Classe("Mage noir"));
        classes.add(new Classe("Invocateur"));
        classes.add(new Classe("Mage Rouge"));

        return classes;
    }

    public static MessageEmbed TestEmbed(){
        EmbedBuilder embed = new EmbedBuilder();

        // Titre
        embed.setTitle("Mon premier bot de raid", null);

        // Couleur
        embed.setColor(Color.GREEN);

        // Calendrier
        embed.addField("Date", "XX mois année", true);
        embed.addField("heure", "HH:MM", true);
        embed.addField("Nb participants","XX", true);

        // Ajout d'un espace
        embed.addBlankField(false);

        // Classes
        StringBuilder affichageClasseTank = new StringBuilder();
        StringBuilder affichageClasseHealer = new StringBuilder();
        StringBuilder affichageClasseMelee = new StringBuilder();
        StringBuilder affichageClasseDistant = new StringBuilder();
        StringBuilder affichageClasseMagie = new StringBuilder();
        for(Classe classe : classesList()){
            if (classesList().indexOf(classe.getNom()) <= 3){
                String champs = CompteursClasses(classe.getNom()) + " " +
                        classe.getNom() + "\n";

                affichageClasseTank.append(champs);
            } else if (3 < classesList().indexOf(classe.getNom()) && classesList().indexOf(classe.getNom()) <= 7) {
                String champs = CompteursClasses(classe.getNom()) + " " +
                        classe.getNom() + "\n";

                affichageClasseHealer.append(champs);
            }else if (7 < classesList().indexOf(classe.getNom()) && classesList().indexOf(classe.getNom()) <= 12) {
                String champs = CompteursClasses(classe.getNom()) + " " +
                        classe.getNom() + "\n";

                affichageClasseMelee.append(champs);
            }else if (12 < classesList().indexOf(classe.getNom()) && classesList().indexOf(classe.getNom()) <= 15) {
                String champs = CompteursClasses(classe.getNom()) + " " +
                        classe.getNom() + "\n";

                affichageClasseDistant.append(champs);
            }else if (15 < classesList().indexOf(classe.getNom()) && classesList().indexOf(classe.getNom()) <= 18) {
                String champs = CompteursClasses(classe.getNom()) + " " +
                        classe.getNom() + "\n";

                affichageClasseMagie.append(champs);
            }



        }

        embed.addField(affichageClasseTank.toString(), "", true);
        embed.addField(affichageClasseHealer.toString(), "", true);
        embed.addField(affichageClasseMelee.toString(), "", true);
        embed.addField(affichageClasseDistant.toString(), "", true);
        embed.addField(affichageClasseMagie.toString(), "", true);

//        embed.addField("Tanks", "XX mois année", true);
//        embed.addField("Soigneurs", "HH:MM", true);
//        embed.addField("Nb participants","XX", true);

        // Ajout de l'auteur
        embed.setAuthor("Maxime", null, null);

        // paramétrage du footer
        embed.setFooter("text", null);

        // image
        //embed.setImage("https://st4.depositphotos.com/1112664/28107/i/450/depositphotos_281074218-stock-photo-test-answer-sheet-with-pencil.jpg");

        return embed.build();

    }

    // Inscription joueur
    public void inscrireJoueur(String nomClasse){

        Classe classe = classesList().stream()
                .filter(c -> c.getNom().equalsIgnoreCase(nomClasse))
                .findFirst()
                .orElse(null);

        if(classe != null) {
            classe.incrementerEffectif();
        }
    }

    // Desinscrire joueur
    public void desinscrireJoueur(String nomClasse){
        Classe classe = classesList().stream()
                .filter(c -> c.getNom().equalsIgnoreCase(nomClasse))
                .findFirst()
                .orElse(null);

        if(classe != null) {
            classe.decrementerEffectif();
        }
    }

    public static String CompteursClasses(String nomClasse) {
        Classe classe = classesList().stream()
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
