
package com.nante.app.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
@Entity
public class Ouvrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private int taux_horaire;
    public Ouvrier(int id) {
        this.id = id;
    }
    public Ouvrier(String description, int taux_horaire) {
        this.description = description;
        this.taux_horaire = taux_horaire;
    }
    public Ouvrier() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getTaux_horaire() {
        return taux_horaire;
    }
    public void setTaux_horaire(int taux_horaire) {
        this.taux_horaire = taux_horaire;
    }

    

}
