
package com.nante.app.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
@Entity
public class Ouvrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private int taux_horaire;
    private LocalDateTime date_embauche;
    @ManyToOne
    @JoinColumn(name="idProfil")
    private Profil profil;
    @Transient
    private int taux_horaire_actuel;

    public Ouvrier(String description, int taux_horaire, LocalDateTime date_embauche, Profil profil) {
        this.description = description;
        this.taux_horaire = taux_horaire;
        this.date_embauche = date_embauche;
        this.profil = profil;
    }
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
    public LocalDateTime getDate_embauche() {
        return date_embauche;
    }
    public void setDate_embauche(LocalDateTime date_embauche) {
        this.date_embauche = date_embauche;
    }
    public int getTaux_horaire_actuel() {
        return taux_horaire_actuel;
    }
    public void setTaux_horaire_actuel(int taux_horaire_actuel) {
        this.taux_horaire_actuel = taux_horaire_actuel;
    }
    public Profil getProfil() {
        return profil;
    }
    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    

}
