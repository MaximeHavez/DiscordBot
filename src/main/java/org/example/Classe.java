package org.example;

public class Classe {

    private String nom;
    private int effectif;

    public Classe(String nom) {
        this.nom = nom;
        this.effectif = 0;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEffectif() {
        return effectif;
    }

    public void incrementerEffectif() {
        effectif++;
    }

    public void decrementerEffectif() {
        effectif--;
    }
}
